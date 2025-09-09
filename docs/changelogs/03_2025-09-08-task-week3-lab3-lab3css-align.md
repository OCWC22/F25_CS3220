# Changelog: 03_2025-09-08 - Week 3 Lab3 aligned to lab3.css

**Task:** [W3-LAB3] Make week_3/lab3 use `lab3.css` and match assignment behaviors
**Status:** Done

### Files Updated:
- **UPDATED:** `week_3/lab3/articles.html` – Switch stylesheet to `lab3.css`, moved title into gradient container, HOME now links to `articles.html`
- **UPDATED:** `week_3/lab3/addarticle.html` – Switch stylesheet to `lab3.css`
- **UPDATED:** `week_3/lab3/lab3.css` – Add nav pseudo-classes, set gradient to `120deg, #2980b9 → #8e44ad`, make article links yellowgreen for all states, add hover highlight for body rows, position title top-left, remove empty ruleset

### Description:
Brought the week_3/lab3 variant in line with the assignment’s stylesheet and interaction requirements without changing its existing HTML structure.

### Reasoning:
- Single external CSS file per lab variant simplifies grading and satisfies rubric.
- Pseudo-classes and gradient angle/color match spec.
- Title moved to top-left and hover only on data rows matches screenshots and notes.

### Key Decisions & Trade‑offs:
- Kept the existing `<table>` markup in `week_3/lab3/articles.html` to minimize scope. If strict "no <table>" is required for this variant, we can refactor to a div-based grid.

### Verification:
Open `http://localhost:8001/week_3/lab3/articles.html` and verify nav states, gradient, bold header, row borders, yellowgreen links, and hover highlight. Footer remains pinned.
