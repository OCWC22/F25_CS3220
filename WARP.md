# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

This is a CS3220 Web Programming course repository containing HTML/CSS labs and a Java servlet project. The repository has two main components:
1. Static HTML/CSS labs (in `CS3220/` directory)
2. Java Maven web application (in `workspace/webtest/`)

## Repository Structure

```
F25_CS3220/
├── CS3220/           # Static HTML/CSS labs
│   ├── lab1/        # Basic HTML article page
│   ├── lab2/        # Articles with basic CSS
│   ├── lab3/        # Advanced CSS with div-based tables
│   └── lab4/        # Bootstrap integration
├── workspace/       # Java web application
│   └── webtest/    # Maven-based servlet project
├── week_*/         # Weekly assignments and resources
├── docs/           # Changelogs and documentation
└── index.html      # Main landing page
```

## Development Commands

### For Static HTML/CSS Labs
```bash
# Open the main index in browser (macOS)
open index.html

# Open specific lab
open CS3220/lab3/articles.html

# Search for CSS files
find CS3220 -name "*.css"

# Search for specific CSS properties
grep -r "background" CS3220/*.css
```

### For Java Web Application
```bash
# Navigate to Java project
cd workspace/webtest

# Build the project
mvn clean compile

# Package as WAR file
mvn clean package

# Run tests (if any)
mvn test

# Clean build artifacts
mvn clean
```

## Architecture & Key Components

### Static Labs Architecture
- Each lab is self-contained in `CS3220/labN/` directories
- Labs progressively build complexity: basic HTML → CSS → advanced CSS → Bootstrap
- Central `index.html` provides navigation to all labs
- CSS files follow naming convention: `labN.css` for each lab

### Java Web Application Architecture
- Standard Maven web project structure
- Uses Jakarta Servlet API 4.0.4
- Configured for Java 17
- Servlet mappings use `@WebServlet` annotations
- WAR packaging for deployment to servlet containers

### Lab Requirements & Constraints
- Lab 3 specifically requires:
  - Single external CSS file named `lab3.css`
  - Limited HTML tags (no `<table>` elements)
  - Specific CSS features: gradients, pseudo-classes, sticky footer
  - Div-based table implementation using CSS Grid

## CSS & Styling Patterns

The project uses progressive enhancement across labs:
- Lab 2: Basic CSS with `styles.css`
- Lab 3: Advanced CSS with gradients, grid layouts, pseudo-classes
- Lab 4: Bootstrap integration with custom `lab4.css` overrides

Common CSS patterns include:
- Flexbox for page layouts
- CSS Grid for div-based tables
- Sticky footers using flexbox
- Navigation with hover/active states
- Gradient backgrounds

## Development Workflow

1. For HTML/CSS changes:
   - Edit files directly in `CS3220/labN/`
   - Test by opening in browser
   - Verify responsive behavior

2. For Java servlet development:
   - Edit Java files in `workspace/webtest/src/main/java/`
   - Run `mvn compile` to check for errors
   - Package with `mvn package` to create deployable WAR

## Testing & Validation

### HTML/CSS Validation
```bash
# Check for broken links in HTML files
grep -r "href=" CS3220/*.html | grep -v "http" | grep -v "#"

# Verify CSS file references
grep -r "link.*css" CS3220/*.html
```

### Java Build Verification
```bash
cd workspace/webtest && mvn clean compile
```

## Notes

- The `.claude` directory contains AI model settings using a custom API endpoint
- Documentation of changes is maintained in `docs/changelogs/`
- The project appears to be for academic purposes (CS3220 course)