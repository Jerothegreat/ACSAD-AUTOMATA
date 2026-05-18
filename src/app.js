const GROUP_COUNT = 11;
const PISTON_ENDPOINT = "https://emkc.org/api/v2/piston/execute";

const state = {
  currentGroupNumber: 1,
  currentGroup: null,
  currentLabIndex: 0,
  currentSubLabIndex: 0,
  currentLab: null,
  currentCode: "",
  sidebarOpen: true,
};

const elements = {
  sidebar: document.getElementById("sidebar"),
  sidebarOverlay: document.getElementById("sidebarOverlay"),
  sidebarToggleBtn: document.getElementById("sidebarToggleBtn"),
  sidebarToggleText: document.getElementById("sidebarToggleText"),
  sidebarRailToggleBtn: document.getElementById("sidebarRailToggleBtn"),
  groupContent: document.getElementById("groupContent"),
  groupList: document.getElementById("groupList"),
  groupName: document.getElementById("groupName"),
  memberNames: document.getElementById("memberNames"),
  downloadBtn: document.getElementById("downloadBtn"),
  tabRow: document.getElementById("tabRow"),
  subTabRow: document.getElementById("subTabRow"),
  emptyState: document.getElementById("emptyState"),
  labPanel: document.getElementById("labPanel"),
  labTitle: document.getElementById("labTitle"),
  labDescription: document.getElementById("labDescription"),
  screenshotImage: document.getElementById("screenshotImage"),
  previewFallback: document.getElementById("previewFallback"),
  sourceFile: document.getElementById("sourceFile"),
  codeBlock: document.getElementById("codeBlock"),
  inputFields: document.getElementById("inputFields"),
  runBtn: document.getElementById("runBtn"),
  outputLabel: document.getElementById("outputLabel"),
  outputBox: document.getElementById("outputBox"),
};

const sidebarViewport = window.matchMedia("(max-width: 960px)");

// Used for SYNTAX HIGHLIGHTING only — follows displayFile if present
function getDisplayLanguage(lab = {}) {
  const fileName = lab.displayFile || lab.file || "";
  const extension = fileName.split(".").pop()?.toLowerCase();
  if (extension === "js") return "javascript";
  if (extension === "java") return "java";
  return "";
}

// Used for RUNTIME EXECUTION — always follows the actual runnable file
function getRunLanguage(lab = {}) {
  const fileName = lab.file || "";
  const extension = fileName.split(".").pop()?.toLowerCase();
  if (extension === "js") return "javascript";
  if (extension === "java") return "java";
  return "";
}

function setCodeBlockLanguage(language) {
  elements.codeBlock.className = language ? `language-${language}` : "";
}

function updateSidebarToggleUi() {
  const label = state.sidebarOpen
    ? (sidebarViewport.matches ? "Close menu" : "Collapse sidebar")
    : (sidebarViewport.matches ? "Open menu" : "Expand sidebar");

  elements.sidebarToggleBtn?.setAttribute("aria-expanded", String(state.sidebarOpen));
  elements.sidebarToggleBtn?.setAttribute("aria-label", label);
  elements.sidebarRailToggleBtn?.setAttribute("aria-expanded", String(state.sidebarOpen));
  elements.sidebarRailToggleBtn?.setAttribute("aria-label", label);

  if (elements.sidebarToggleText) {
    elements.sidebarToggleText.textContent = label;
  }
}

function setSidebarOpen(isOpen) {
  state.sidebarOpen = isOpen;
  document.body.classList.toggle("sidebar-open", isOpen);
  updateSidebarToggleUi();
}

function toggleSidebar() {
  setSidebarOpen(!state.sidebarOpen);
}

function syncSidebarForViewport() {
  setSidebarOpen(!sidebarViewport.matches);
}

function groupPath(groupNumber) {
  return `groups/group${groupNumber}`;
}

function removeGroupCustomCss() {
  document.getElementById("group-custom-css")?.remove();
}

function ensureProtectedBaseStyles() {
  document.getElementById("app-protected-css")?.remove();

  const style = document.createElement("style");
  style.id = "app-protected-css";
  style.textContent = `
    .sidebar {
      display: block !important;
      visibility: visible !important;
    }

    .code-block pre {
      display: block !important;
      overflow: auto !important;
      white-space: pre !important;
    }

    .code-block code {
      font-family: Consolas, "Courier New", monospace !important;
    }

    .try-it-panel {
      display: grid !important;
      visibility: visible !important;
    }

    .try-it-panel input,
    .try-it-panel button,
    .try-it-panel pre {
      pointer-events: auto !important;
    }
  `;
  document.head.appendChild(style);
}

async function loadGroupCustomCss(groupNumber) {
  removeGroupCustomCss();
  ensureProtectedBaseStyles();

  const cssPath = `${groupPath(groupNumber)}/custom.css`;

  try {
    const response = await fetch(cssPath, { method: "HEAD", cache: "no-store" });
    if (state.currentGroupNumber !== groupNumber) return;
    if (!response.ok) return;

    const link = document.createElement("link");
    link.id = "group-custom-css";
    link.rel = "stylesheet";
    link.href = cssPath;
    document.head.appendChild(link);
    ensureProtectedBaseStyles();
  } catch (error) {
    // custom.css is optional, so missing or blocked checks should not stop the app.
  }
}

function setGroupContentScope(groupNumber) {
  elements.groupContent.className = `main-content group-content group-content-group${groupNumber}`;
}

function renderGroupButtons() {
  elements.groupList.innerHTML = "";

  for (let groupNumber = 1; groupNumber <= GROUP_COUNT; groupNumber += 1) {
    const button = document.createElement("button");
    button.type = "button";
    button.className = "group-btn";
    button.dataset.short = `G${groupNumber}`;
    button.dataset.group = String(groupNumber);
    button.addEventListener("click", () => {
      loadGroup(groupNumber);
      if (sidebarViewport.matches) {
        setSidebarOpen(false);
      }
    });

    const label = document.createElement("span");
    label.className = "group-btn-text";
    label.textContent = `Group ${groupNumber}`;

    button.appendChild(label);
    elements.groupList.appendChild(button);
  }
}

function setActiveGroupButton(groupNumber) {
  document.querySelectorAll(".group-btn").forEach((button) => {
    button.classList.toggle("active", Number(button.dataset.group) === groupNumber);
  });
}

async function fetchText(path) {
  const response = await fetch(path, { cache: "no-store" });
  if (!response.ok) {
    throw new Error(`Unable to load ${path}`);
  }
  return response.text();
}

async function fetchJson(path) {
  const response = await fetch(path, { cache: "no-store" });
  if (!response.ok) {
    throw new Error(`Unable to load ${path}`);
  }
  return response.json();
}

async function loadGroup(groupNumber) {
  state.currentGroupNumber = groupNumber;
  state.currentLabIndex = 0;
  state.currentSubLabIndex = 0;
  state.currentLab = null;
  state.currentCode = "";
  setActiveGroupButton(groupNumber);
  setGroupContentScope(groupNumber);
  setLoading(`Loading Group ${groupNumber}...`);
  loadGroupCustomCss(groupNumber);

  try {
    const info = await fetchJson(`${groupPath(groupNumber)}/info.json`);
    state.currentGroup = info;
    renderGroup(info, groupNumber);
  } catch (error) {
    state.currentGroup = null;
    renderUnavailableGroup(groupNumber, error.message);
  }
}

function setLoading(message) {
  elements.groupName.textContent = message;
  elements.memberNames.textContent = "";
  elements.downloadBtn.setAttribute("aria-disabled", "true");
  elements.downloadBtn.href = "#";
  elements.tabRow.innerHTML = "";
  elements.subTabRow.innerHTML = "";
  elements.subTabRow.hidden = true;
  setOutput("Program output will appear here.");
  elements.labPanel.hidden = true;
  elements.emptyState.hidden = false;
  elements.emptyState.textContent = message;
}

function renderUnavailableGroup(groupNumber, reason) {
  elements.groupName.textContent = `Group ${groupNumber}`;
  elements.memberNames.textContent = "No info.json found yet for this group.";
  elements.downloadBtn.setAttribute("aria-disabled", "true");
  elements.downloadBtn.href = "#";
  elements.tabRow.innerHTML = "";
  elements.subTabRow.innerHTML = "";
  elements.subTabRow.hidden = true;
  elements.labPanel.hidden = true;
  elements.emptyState.hidden = false;
  elements.emptyState.textContent = `${reason}. Add groups/group${groupNumber}/info.json to enable this section.`;
}

function renderGroup(info, groupNumber) {
  elements.groupName.textContent = info.group || `Group ${groupNumber}`;
  elements.memberNames.textContent = Array.isArray(info.members) && info.members.length
    ? `Members: ${info.members.join(", ")}`
    : "Members: Not listed";

  if (info.download) {
    elements.downloadBtn.href = `${groupPath(groupNumber)}/${info.download}`;
    elements.downloadBtn.removeAttribute("aria-disabled");
  } else {
    elements.downloadBtn.href = "#";
    elements.downloadBtn.setAttribute("aria-disabled", "true");
  }

  renderTabs(info.labacts || []);

  if (info.labacts && info.labacts.length > 0) {
    elements.emptyState.hidden = true;
    elements.labPanel.hidden = false;
    selectLab(0);
  } else {
    elements.labPanel.hidden = true;
    elements.emptyState.hidden = false;
    elements.emptyState.textContent = "This group has no lab acts listed yet.";
  }
}

function renderTabs(labacts) {
  elements.tabRow.innerHTML = "";
  labacts.forEach((lab, index) => {
    const button = document.createElement("button");
    button.type = "button";
    button.className = "tab-btn";
    button.role = "tab";
    button.textContent = lab.name || `Lab Act ${index + 1}`;
    button.dataset.index = String(index);
    button.addEventListener("click", () => selectLab(index));
    elements.tabRow.appendChild(button);
  });
}

function selectLab(index) {
  const lab = state.currentGroup?.labacts?.[index];
  if (!lab) return;

  state.currentLabIndex = index;
  state.currentSubLabIndex = 0;
  document.querySelectorAll(".tab-btn").forEach((button) => {
    const isActive = Number(button.dataset.index) === index;
    button.classList.toggle("active", isActive);
    button.setAttribute("aria-selected", String(isActive));
  });

  if (lab.type === "group" && Array.isArray(lab.sublabacts) && lab.sublabacts.length > 0) {
    renderSubTabs(lab.sublabacts);
    selectSubLab(0);
    return;
  }

  elements.subTabRow.innerHTML = "";
  elements.subTabRow.hidden = true;
  loadLab(lab);
}

function renderSubTabs(sublabacts) {
  elements.subTabRow.innerHTML = "";
  elements.subTabRow.hidden = false;

  sublabacts.forEach((lab, index) => {
    const button = document.createElement("button");
    button.type = "button";
    button.className = "sub-tab-btn";
    button.role = "tab";
    button.textContent = lab.name || `Sub Lab ${index + 1}`;
    button.dataset.index = String(index);
    button.addEventListener("click", () => selectSubLab(index));
    elements.subTabRow.appendChild(button);
  });
}

function selectSubLab(index) {
  const parentLab = state.currentGroup?.labacts?.[state.currentLabIndex];
  const lab = parentLab?.sublabacts?.[index];
  if (!lab) return;

  state.currentSubLabIndex = index;
  document.querySelectorAll(".sub-tab-btn").forEach((button) => {
    const isActive = Number(button.dataset.index) === index;
    button.classList.toggle("active", isActive);
    button.setAttribute("aria-selected", String(isActive));
  });
  loadLab(lab);
}

async function loadLab(lab) {
  state.currentLab = lab;
  state.currentCode = "";
  const labLanguage = getDisplayLanguage(lab);
  elements.labTitle.textContent = lab.name || "Untitled Lab Act";
  elements.labDescription.textContent = lab.description || "No description provided.";
  elements.sourceFile.textContent = lab.displayFile || lab.file || "Source file not listed";
  renderInputs(lab.inputs || []);
  setOutput("Program output will appear here.");
  renderScreenshot(lab);

  setCodeBlockLanguage(getDisplayLanguage(lab));
  elements.codeBlock.removeAttribute("data-highlighted");
  elements.codeBlock.textContent = "Loading source code...";
  hljs.highlightElement(elements.codeBlock);

  try {
    const displayCode = await fetchText(`${groupPath(state.currentGroupNumber)}/${lab.displayFile || lab.file}`);
    state.currentCode = await fetchText(`${groupPath(state.currentGroupNumber)}/${lab.file}`);
    elements.codeBlock.textContent = displayCode;
    elements.codeBlock.removeAttribute("data-highlighted");
    hljs.highlightElement(elements.codeBlock);
  } catch (error) {
    state.currentCode = "";
    elements.codeBlock.removeAttribute("data-highlighted");
    elements.codeBlock.textContent = `// ${error.message}`;
    hljs.highlightElement(elements.codeBlock);
  }
}
function renderInputs(inputs) {
  elements.inputFields.innerHTML = "";

  if (!inputs.length) {
    const empty = document.createElement("p");
    empty.className = "input-help";
    empty.textContent = "This lab act does not require input.";
    elements.inputFields.appendChild(empty);
    return;
  }

  inputs.forEach((input, index) => {
    const fieldId = `labInput${index}`;
    const field = document.createElement("div");
    field.className = "input-field";

    const label = document.createElement("label");
    label.setAttribute("for", fieldId);
    label.textContent = input.label || `Input ${index + 1}`;

    const inputElement = document.createElement("input");
    inputElement.id = fieldId;
    inputElement.type = "text";
    inputElement.placeholder = input.placeholder || "";
    inputElement.dataset.stdin = "true";

    field.append(label, inputElement);
    elements.inputFields.appendChild(field);
  });
}

function collectInputValues() {
  return Array.from(elements.inputFields.querySelectorAll("[data-stdin='true']"))
    .map((input) => input.value);
}

function collectStdin() {
  return collectInputValues().join("\n");
}

function renderScreenshot(lab) {
  if (!lab.screenshot) {
    showScreenshotFallback();
    return;
  }

  elements.screenshotImage.hidden = false;
  elements.previewFallback.hidden = true;
  elements.screenshotImage.src = `${groupPath(state.currentGroupNumber)}/${lab.screenshot}`;
  elements.screenshotImage.onerror = showScreenshotFallback;
}

function showScreenshotFallback() {
  elements.screenshotImage.hidden = true;
  elements.screenshotImage.removeAttribute("src");
  elements.previewFallback.hidden = false;
}

function setOutput(message, isError = false) {
  elements.outputLabel.textContent = isError ? "Error" : "Output";
  elements.outputBox.textContent = message;
  elements.outputBox.classList.toggle("error", isError);
}

function setRunLoading(isLoading) {
  elements.runBtn.disabled = isLoading;
  elements.runBtn.textContent = isLoading ? "Running..." : "▶ Run";
  elements.runBtn.classList.toggle("loading", isLoading);
}

function formatJsConsoleValue(value) {
  if (typeof value === "string") return value;
  if (typeof value === "number" || typeof value === "boolean" || value == null) {
    return String(value);
  }

  try {
    return JSON.stringify(value);
  } catch (error) {
    return String(value);
  }
}

async function runCurrentJavascriptLab() {
  const inputValues = collectInputValues();
  const logs = [];
  const consoleProxy = {
    log: (...args) => {
      logs.push(args.map(formatJsConsoleValue).join(" "));
    },
  };

  const executeLab = new Function(
    "inputs",
    "console",
    `
${state.currentCode}

if (typeof main !== "function") {
  throw new Error("JavaScript lab must define a main(inputs) function.");
}

return main(inputs);
`,
  );

  const result = executeLab(inputValues, consoleProxy);
  const outputParts = [];

  if (logs.length > 0) {
    outputParts.push(logs.join("\n"));
  }

  if (typeof result === "string" && result.trim()) {
    outputParts.push(result);
  } else if (result !== undefined && result !== null && result !== "") {
    outputParts.push(String(result));
  }

  setOutput(outputParts.join("\n").trim() || "Program finished with no output.");
}

async function runCurrentLab() {
  if (!state.currentCode || !state.currentLab?.file) {
    setOutput("No source code is loaded for this lab act.", true);
    return;
  }

  const labLanguage = getRunLanguage(state.currentLab);
  setRunLoading(true);
  setOutput(
    labLanguage === "javascript"
      ? "Running JavaScript in the browser..."
      : "Running Java code with Piston...",
  );

  try {
    if (labLanguage === "javascript") {
      await runCurrentJavascriptLab();
      return;
    }

    const response = await fetch(PISTON_ENDPOINT, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        language: "java",
        version: "15.0.2",
        files: [
          {
            name: state.currentLab.file,
            content: state.currentCode,
          },
        ],
        stdin: collectStdin(),
      }),
    });

    if (!response.ok) {
      throw new Error(`Piston request failed with status ${response.status}`);
    }

    const result = await response.json();
    const run = result.run || {};
    const compile = result.compile || {};
    const stderr = [compile.stderr, run.stderr].filter(Boolean).join("\n").trim();

    if (stderr) {
      setOutput(stderr, true);
      return;
    }

    const output = [compile.output, run.output]
      .filter(Boolean)
      .join("\n")
      .trim();

    setOutput(output || "Program finished with no output.");
  } catch (error) {
    setOutput(`Error: ${error.message}`, true);
  } finally {
    setRunLoading(false);
  }
}

elements.runBtn.addEventListener("click", runCurrentLab);
elements.sidebarToggleBtn?.addEventListener("click", toggleSidebar);
elements.sidebarRailToggleBtn?.addEventListener("click", toggleSidebar);
elements.sidebarOverlay?.addEventListener("click", () => setSidebarOpen(false));
sidebarViewport.addEventListener("change", syncSidebarForViewport);
document.addEventListener("keydown", (event) => {
  if (event.key === "Escape" && sidebarViewport.matches && state.sidebarOpen) {
    setSidebarOpen(false);
  }
});

syncSidebarForViewport();
renderGroupButtons();
loadGroup(1);


