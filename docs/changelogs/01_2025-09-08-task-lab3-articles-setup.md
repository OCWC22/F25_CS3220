# Changelog: 01_2025-09-08 - Lab 3 Articles Page Setup (Task LAB3)

**Task:** [LAB3] Build Lab 3 `articles.html` with CSS and root link
**Status:** Done

### Files Updated:
- **CREATED:** `lab3/articles.html` – Div-based table page using only allowed tags and linked stylesheet
- **CREATED:** `lab3/lab3.css` – Navigation styles (uppercase, link states), gradient content area, div-table layout, hover highlight, sticky footer
- **CREATED:** `index.html` – Root index linking to `lab3/articles.html`

### Description:
Set up a new `lab3/` folder with an `articles.html` and `lab3.css` implementing the assignment constraints and visuals. Added a project root `index.html` linking to the Lab 3 page.

### Reasoning:
Kept to the strict tag whitelist by avoiding `<table>` and instead using `<div>` rows/cells. CSS implements required pseudo-classes, gradient background, row borders, and sticky footer to match screenshots and rubric.

### Key Decisions & Trade‑off:
- Used CSS Grid to lay out the div-based table for simplicity and readability.
- Applied `flex` layout on `body` to ensure the footer stays at the bottom on any viewport height.
- Chose `gap` for nav spacing for clarity; simple and robust spacing while keeping right alignment.

### Notes:
No existing `docs/changelogs` directory was found; created per project standard.
