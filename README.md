# ACSAD Automata Lab Acts

ACSAD Automata Lab Acts is a static web app for presenting university automata laboratory activities by group. It loads each group's metadata, Java source files, screenshots, and downloadable project zip from the `groups/` folder.

The app is built with plain HTML, CSS, and JavaScript only. It uses highlight.js for Java syntax highlighting and the Piston API to run sample console-based Java programs in the browser.

## Live Site

Placeholder link: https://your-username.github.io/acsad-automata

## Enable GitHub Pages

1. Open the repository on GitHub.
2. Go to `Settings`.
3. Open `Pages` from the left sidebar.
4. Under `Build and deployment`, set the source to `Deploy from a branch`.
5. Select the `main` branch.
6. Select `/root` as the folder.
7. Save the settings.
8. Wait for GitHub Pages to publish the site.

## Folder Structure

```text
acsad-automata/
├── index.html
├── README.md
├── src/
│   ├── app.js
│   └── style.css
└── groups/
    ├── group1/
    │   ├── info.json
    │   ├── DivAlgo.java
    │   ├── Palindrome.java
    │   ├── Euclidean.java
    │   ├── Collatz.java
    │   ├── Fibonacci.java
    │   ├── Lucas.java
    │   ├── Tribonacci.java
    │   ├── project.zip
    │   └── screenshots/
    │       ├── DivAlgo.png
    │       ├── Palindrome.png
    │       ├── Euclidean.png
    │       ├── Collatz.png
    │       ├── Fibonacci.png
    │       ├── Lucas.png
    │       └── Tribonacci.png
    ├── group2/
    └── group10/
```

## Steps For Blockmates

1. Clone the repo.

```bash
git clone https://github.com/your-username/acsad-automata.git
cd acsad-automata
```

2. Go to your assigned group folder, for example:

```bash
cd groups/groupX
```

3. Add your self-contained Java files.

Each Java file must have a class name that exactly matches the filename. Example: `DivAlgo.java` must contain `public class DivAlgo`.

4. Add screenshots to the `screenshots/` folder.

The screenshot filename must exactly match the path written in `info.json`.

5. Fill out `info.json`, including the `inputs` array for every runnable lab act.

6. Add `project.zip`.

This zip should contain your full Java project with GUI files, not just the console files used by the browser runner.

7. Push directly to the `main` branch.

No pull request is needed. Do not touch other groups' folders or root files.

## info.json Field Reference

| Field | Type | Description |
| --- | --- | --- |
| `group` | string | Display name of the group, such as `Group 1`. |
| `members` | string array | Names of the group members. |
| `download` | string | Downloadable zip filename. Use `project.zip`. |
| `labacts` | array | Top-level lab acts shown as tabs. |
| `name` | string | Display name of a lab act or grouped tab. |
| `file` | string | Java source filename for a runnable lab act. The class name must match this filename. |
| `screenshot` | string | Relative screenshot path, usually `screenshots/FileName.png`. |
| `description` | string | Short explanation shown above the preview and code. |
| `inputs` | array | Input fields rendered in the Try It panel. Values are joined with newlines and sent to Java stdin. |
| `inputs[].label` | string | Label shown above an input field. |
| `inputs[].placeholder` | string | Example input shown inside the input field. |
| `type` | string | Use `group` when a tab contains sub-tabs instead of a direct Java file. |
| `sublabacts` | array | Child lab acts for grouped tabs, such as Recursion. |

## Example Lab Act

```json
{
  "name": "Division Algorithm",
  "file": "DivAlgo.java",
  "screenshot": "screenshots/DivAlgo.png",
  "description": "Computes the quotient and remainder for two integers using the division algorithm.",
  "inputs": [
    { "label": "Dividend", "placeholder": "integer, e.g. 10" },
    { "label": "Divisor", "placeholder": "non-zero integer, e.g. 3" }
  ]
}
```

## Example Grouped Lab Act

```json
{
  "name": "Recursion",
  "type": "group",
  "sublabacts": [
    {
      "name": "Fibonacci",
      "file": "Fibonacci.java",
      "screenshot": "screenshots/Fibonacci.png",
      "description": "Fibonacci sequence recursively.",
      "inputs": [
        { "label": "Number of terms", "placeholder": "positive integer, e.g. 7" }
      ]
    }
  ]
}
```

## Input Requirements

| Lab Act | Input Fields | Requirement |
| --- | --- | --- |
| Division Algorithm | Dividend, Divisor | Dividend must be an integer. Divisor must be a non-zero integer. |
| Palindrome | String | Any non-empty string. |
| Euclidean | First positive integer, Second positive integer | Both values must be positive integers. |
| Collatz Sequence | Starting number | Must be a positive integer greater than 0. |
| Fibonacci | Number of terms | Must be a positive integer. |
| Lucas | Number of terms | Must be a positive integer. |
| Tribonacci | Number of terms | Must be a positive integer. |

## Optional Group Styling

Each group folder can optionally include a `custom.css` file.

When a group is selected, the app checks for `groups/groupX/custom.css`. If the file exists, it is loaded only for that active group. When another group is selected, the previous custom stylesheet is removed first.

Group custom CSS should be scoped to `.group-content` so it does not affect other parts of the app. The main content area also receives a group-specific class such as `.group-content-group1`, `.group-content-group2`, and so on.

Do not restyle these protected app areas:

- `.code-block`
- `.try-it-panel`
- `.sidebar`

Example `groups/group1/custom.css`:

```css
.group-content {
  background: #f7fbff;
  font-family: "Trebuchet MS", sans-serif;
}

.group-content .hero-card,
.group-content .lab-card {
  background: #ffffff;
  border-color: #9cc7ff;
}

.group-content .tab-btn.active {
  background: #d9ecff;
}
```

## Java File Requirements

- Each `.java` file must be self-contained.
- Do not depend on external classes or project-only files.
- Use `Scanner(System.in)` only for browser-run input.
- Do not use `JOptionPane` or GUI dialogs in the runnable source files.
- The class name must exactly match the filename.
- Include validation as a static helper method inside the same file.
- Include a `public static void main(String[] args)` method.
- Keep input order the same as the `inputs` array in `info.json`.

## Troubleshooting

| Problem | Fix |
| --- | --- |
| Code not running | Check that the class name matches the filename exactly. |
| Wrong output | Check that the input order matches the `info.json` inputs array. |
| Screenshot not showing | The filename must match the `screenshot` field exactly. |
| Zip not downloading | The file must be named `project.zip`. |
| Group not loading | Make sure the group folder has a valid `info.json`. |
| Browser blocks file loading | Run the project through a local static server instead of opening `index.html` directly. |

## Local Preview

Because the app loads JSON and Java files with `fetch()`, use a local static server.

```bash
npx serve .
```

Then open the local URL shown in the terminal.
