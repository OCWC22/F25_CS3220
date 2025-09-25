# Changelog: 01_2025-09-23 - Fix CSS Paths (Task assets-css-path)

**Task:** [assets-css-path] Update labs to use new `assets/css/` directory  
**Status:** Done

### Files Updated:
- **UPDATED:** `CS3220/lab2/articles.html` – point stylesheet link to `assets/css/lab2.css`
- **UPDATED:** `CS3220/lab2/addarticle.html` – point stylesheet link to `assets/css/lab2.css`
- **UPDATED:** `CS3220/lab3/articles.html` – point stylesheet link to `assets/css/lab3.css`
- **UPDATED:** `CS3220/lab3/addarticle.html` – point stylesheet link to `assets/css/lab3.css`
- **UPDATED:** `CS3220/lab4/articles.html` – point stylesheet link to `assets/css/lab4.css`
- **UPDATED:** `CS3220/lab4/login.html` – point stylesheet link to `assets/css/lab4.css`

### Description:
Updated all Lab 2–4 HTML pages to reference the relocated CSS files under `assets/css/`, ensuring styles load correctly after the folder reorganization.

### Reasoning:
Aligning the HTML references with the new directory structure prevents 404 errors for stylesheets and keeps all assets centralized within `assets/css/` as intended.

### Key Decisions & Trade-off:
- Redirected stylesheet links rather than duplicating CSS files, avoiding maintenance overhead.
- Attempted to validate the `GuestBook` Maven project; Maven is not installed locally (`mvn` command missing). Further setup is required before builds can run.

### Follow-up Actions:
- Install Maven locally (e.g., via Homebrew: `brew install maven`) or use an existing IDE build to compile the `GuestBook` project.
