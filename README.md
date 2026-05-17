# Automata Lab Portfolio Hub

This repository is the shared submission hub for ACSAD Automata lab acts.

Each group submits its work inside its own folder under `groups/`. The site reads that folder, builds the group tabs automatically, shows screenshots, displays the source code, and runs the selected algorithm in the browser.

The current shell already includes:

- a collapsible sidebar for group navigation
- a source-code viewer with syntax highlighting
- an output panel for running algorithms
- support for both browser-side JavaScript and Java via Piston

For new submissions, JavaScript is the preferred runtime because it runs directly in the browser and avoids depending on the Java runner when not needed.

## What Each Group Must Submit

Each group should only work inside its own folder:

```text
groups/groupX/
```

Expected contents:

```text
groups/
└── groupX/
    ├── info.json
    ├── project.zip
    ├── optional custom.css
    ├── Algorithm1.js or Algorithm1.java
    ├── Algorithm2.js or Algorithm2.java
    └── screenshots/
        ├── Algorithm1.png
        └── Algorithm2.png
```

Do not edit these shared app files unless the whole class agreed to change the main app shell:

- `index.html`
- `src/app.js`
- `src/style.css`

## Current Group 1 Pattern

Group 1 is now the reference structure for the current submission format.

Its main tabs are:

- Division Algorithm
- Euclidean
- Collatz Sequence
- Fibonacci
- Lucas
- Tribonacci

These are all top-level tabs. Do not put `Fibonacci`, `Lucas`, and `Tribonacci` under a nested `Recursion` tab unless you were specifically told to do that.

## Recommended Submission Format

Use JavaScript when possible.

Why:

- it runs directly in the browser
- it avoids extra Java execution overhead
- validation messages are easier to control
- it matches the current Group 1 implementation

If your algorithm is submitted as JavaScript:

- the filename in `info.json` must point to the `.js` file exactly
- the file must define a `main(inputs)` function
- `inputs` is an array of strings from the rendered input fields
- return a string for the program output
- throw `new Error("message")` for validation failures

Minimal JavaScript example:

```js
function main(inputs) {
  const raw = String(inputs[0] ?? "").trim();

  if (raw === "") {
    throw new Error("Please enter a value before calculating.");
  }

  const value = Number.parseInt(raw, 10);

  if (Number.isNaN(value)) {
    throw new Error("Invalid input.");
  }

  return `Output: ${value}`;
}
```

## JavaScript Output Rules

Use this pattern for browser-run JavaScript files:

1. Read values from `inputs[index]`
2. Validate them inside the same file
3. Return the final output as a string
4. If invalid, throw an `Error`

Example:

```js
function main(inputs) {
  const first = String(inputs[0] ?? "").trim();
  const second = String(inputs[1] ?? "").trim();

  if (!first || !second) {
    throw new Error("Please complete all required inputs.");
  }

  return `First: ${first}\nSecond: ${second}`;
}
```

## Java Support

Java is still supported, but it is now the fallback path, not the preferred one.

If your algorithm is submitted as Java:

- the filename in `info.json` must point to the `.java` file exactly
- the class name must exactly match the filename
- the file must be self-contained
- read input using `Scanner(System.in)`
- keep the input order exactly the same as the `inputs` array in `info.json`

Minimal Java example:

```java
import java.util.Scanner;

public class SampleAlgo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String value = scanner.hasNextLine() ? scanner.nextLine() : "";

        if (value.trim().isEmpty()) {
            System.out.println("Invalid input.");
            return;
        }

        System.out.println("Output: " + value);
    }
}
```

## info.json Rules

Each group folder must have a valid `info.json`.

Example:

```json
{
  "group": "Group 2",
  "members": [
    "Member One",
    "Member Two",
    "Member Three"
  ],
  "download": "G2_AUTOMATA-ALGO.zip",
  "labacts": [
    {
      "name": "Division Algorithm",
      "file": "DivAlgo.js",
      "screenshot": "screenshots/DivAlgo.png",
      "description": "Computes the quotient and remainder for two integers using the division algorithm.",
      "inputs": [
        { "label": "Dividend", "placeholder": "integer, e.g. 10" },
        { "label": "Divisor", "placeholder": "non-zero integer, e.g. 3" }
      ]
    }
  ]
}
```

Field reference:

| Field | Type | Notes |
| --- | --- | --- |
| `group` | string | Display name, such as `Group 2` |
| `members` | string[] | Group member names |
| `download` | string | Zip filename shown in the download button |
| `labacts` | array | Top-level tabs shown in the app |
| `labacts[].name` | string | Tab label |
| `labacts[].file` | string | Source filename, usually `.js` or `.java` |
| `labacts[].screenshot` | string | Relative image path |
| `labacts[].description` | string | Description shown above the preview |
| `labacts[].inputs` | array | Input field definitions |
| `inputs[].label` | string | Input label |
| `inputs[].placeholder` | string | Input placeholder |

Important:

- the `file` path must match the real filename exactly
- the `screenshot` path must match the real image exactly
- the `download` field must match the zip filename exactly

## Screenshot Rules

For every lab act:

- add a matching screenshot under `screenshots/`
- use the exact filename written in `info.json`
- keep screenshots readable and cropped properly

Example:

```text
groups/group2/screenshots/DivAlgo.png
```

## Optional custom.css

Each group may optionally include:

```text
groups/groupX/custom.css
```

This file is loaded only when that group is active.

Rules:

- scope your selectors under `.group-content`
- use it only for group-specific visual identity
- do not break the shared runner layout

Do not restyle these protected app areas:

- `.sidebar`
- `.code-block`
- `.try-it-panel`

Example:

```css
.group-content {
  background: #f7fbff;
}

.group-content .hero-card,
.group-content .lab-card {
  border-color: #b7d6ff;
}
```

## Submission Checklist For Other Groups

Before submitting, verify all of these:

1. Your work is inside `groups/groupX/` only.
2. `info.json` is valid JSON.
3. All `file`, `screenshot`, and `download` values match the real filenames exactly.
4. Your tabs are top-level lab acts unless instructed otherwise.
5. Your JavaScript files define `main(inputs)` and return output strings.
6. Your validation messages are clear and handled inside the same file.
7. Your screenshots exist and load.
8. Your zip file exists and downloads correctly.

## Local Preview

Install dependencies:

```bash
npm install
```

Run the project locally:

```bash
npm run dev
```

Build the project:

```bash
npm run build
```

## Troubleshooting

| Problem | Fix |
| --- | --- |
| Group does not load | Check that `groups/groupX/info.json` exists and is valid JSON |
| Screenshot does not appear | Check the exact path in `info.json` |
| Source code does not show | Check that the `file` field matches the real filename |
| JavaScript lab does not run | Make sure the file defines `main(inputs)` |
| Validation does not show | Throw `new Error("message")` in your JS file |
| Java lab does not run | Make sure the class name matches the filename and the file is self-contained |
| Download button does nothing | Check that the zip filename matches the `download` field |

## Final Note For Blockmates

Follow the Group 1 structure as your reference.

If you are only submitting your group’s work, do not rename shared root files and do not redesign the main shell. Your main responsibility is:

- correct `info.json`
- correct source files
- correct screenshots
- correct downloadable zip
