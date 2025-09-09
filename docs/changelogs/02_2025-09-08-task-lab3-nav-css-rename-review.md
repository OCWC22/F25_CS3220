# Changelog: 02_2025-09-08 - Lab 3 Review: Nav Fix + CSS Name Confirmation

**Task:** [LAB3] Review implementation as Prof. Lai; ensure CSS named `lab3.css` and fix nav links
**Status:** Done

### Files Updated:
- **UPDATED:** `lab3/articles.html` – Fixed navigation links to use relative `../index.html` for Home and self-link `articles.html` for Article
- **CONFIRMED:** `lab3/lab3.css` – Single external stylesheet for the lab; all styles consolidated here

### Description:
Performed a code review against Lab 3 requirements. Confirmed only allowed tags are used in `lab3/articles.html` and that styles live in a single external CSS file `lab3.css`. Adjusted nav links to be relative and self-referential to satisfy visited/hover/active pseudo-class behavior.

### Reasoning:
- Relative links prevent dependency on absolute root and allow `:visited` to work as intended for the Article and Home links.
- Keeping all styles in `lab3.css` adheres to the single-stylesheet requirement and grading criteria.

### Notes:
- Verified: nav uppercase, link pseudo-classes, gradient background, bold header row, div-based table, row hover (excluding header), and sticky centered footer.
