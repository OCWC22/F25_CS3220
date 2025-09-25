# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a CS3220 Web Programming course repository containing two main components:
1. **Static HTML/CSS Labs** - Progressive web development labs in `CS3220/` directory
2. **Java Servlet Projects** - Maven-based web applications in `workspace/` directory

## Development Commands

### Static HTML/CSS Development
```bash
# Open main labs index
open CS3220/index.html

# Open specific lab
open CS3220/lab3/articles.html

# Find CSS files (organized in assets/css/)
find CS3220 -name "*.css"

# Validate HTML structure
grep -r "link.*css" CS3220/*.html
```

### Java Maven Projects
```bash
# Build GuestBook project
cd workspace/GuestBook && mvn clean compile

# Package as WAR
cd workspace/GuestBook && mvn clean package

# Build webtest project
cd workspace/webtest && mvn clean compile

# Run tests (if any)
mvn test

# Clean build artifacts
mvn clean
```

## Architecture & Key Components

### Static Labs Structure
```
CS3220/
├── lab1/           # Basic HTML article page
├── lab2/           # Articles with basic CSS
├── lab3/           # Advanced CSS with div-based tables
├── lab4/           # Bootstrap integration
└── assets/css/     # Centralized CSS files (lab2.css, lab3.css, lab4.css)
```

### Java Projects Structure
```
workspace/
├── GuestBook/      # GuestBook servlet application (javax.servlet)
└── webtest/        # Test servlet application (jakarta.servlet)
```

### Key Technical Details

**GuestBook Project** (`workspace/GuestBook/`):
- Uses `javax.servlet` API 4.0.1 (Java 8)
- WAR packaging for Tomcat 9 deployment
- Model-View-Controller pattern with:
  - `GuestBookEntry.java` - Data model with timestamp
  - `GuestBook.java` - Main servlet displaying entries
  - `AddComment.java`, `DeleteEntry.java`, `EditEntry.java` - CRUD operations
- Data stored in ServletContext (in-memory storage)

**webtest Project** (`workspace/webtest/`):
- Uses `jakarta.servlet` API 4.0.4 (Java 17)
- Modern Jakarta EE configuration
- Compatible with Tomcat 10+

### CSS Architecture Patterns
- **Lab 2**: Basic CSS styling with `assets/css/lab2.css`
- **Lab 3**: Advanced CSS features including:
  - CSS Grid for div-based tables (no `<table>` elements)
  - Gradient backgrounds
  - Pseudo-classes for navigation states
  - Sticky footer implementation
- **Lab 4**: Bootstrap integration with custom overrides

### Lab Requirements & Constraints
- Lab 3 specifically requires single external CSS file named `lab3.css`
- Limited HTML tags (no `<table>` elements in Lab 3)
- Specific CSS features: gradients, pseudo-classes, sticky footer
- Div-based table implementation using CSS Grid

## Development Workflow

### HTML/CSS Changes
1. Edit files directly in `CS3220/labN/`
2. Update CSS in `CS3220/assets/css/labN.css`
3. Test by opening in browser
4. Verify responsive behavior

### Java Servlet Development
1. Edit Java files in `workspace/*/src/main/java/`
2. Run `mvn compile` to check for errors
3. Package with `mvn package` for deployment
4. Deploy WAR file to Tomcat server

## Important Notes

- The repository contains both javax.servlet (GuestBook) and jakarta.servlet (webtest) projects
- CSS files are centralized in `CS3220/assets/css/` with proper HTML references
- GuestBook uses ServletContext for in-memory data storage
- All servlets use `@WebServlet` annotations for URL mapping
- Maven is required for Java project builds (not installed locally)
- Tomcat server configuration files are in `apache-tomcat-9.0.100/`

## Testing & Validation

### HTML/CSS Validation
```bash
# Check CSS file references
grep -r "link.*css" CS3220/*.html

# Verify asset paths
find CS3220 -name "*.html" -exec grep -l "assets/css" {} \;
```

### Java Build Verification
```bash
cd workspace/GuestBook && mvn clean compile
cd workspace/webtest && mvn clean compile
```