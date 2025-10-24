# LoginSession Project Implementation Documentation

## Overview
This document details the implementation of the `LoginSession` Maven Servlet project, fulfilling the requirements for Lab 08: Session Tracking API with login, logout, registration, protected downloads, and custom 404 handling. The project uses standard Servlet API and JSP, adhering to principles of simplicity, security, and consistency as per project rules.

## Architecture
The application follows a basic MVC (Model-View-Controller) architecture:
- **Model:** Represents data structures (e.g., `UserEntry` for user information).
- **View:** JSP pages for user interfaces (e.g., login, registration, download pages).
- **Controller:** Servlets handle HTTP requests and business logic (e.g., `Login`, `Register`, `Download`).
- **Filters:** `AuthFilter` enforces access control for protected resources.
- **Utilities:** `Bootstrap` listener initializes default data; static assets (CSS, images) enhance UI.

Key interactions:
- Servlets process requests, interact with the servlet context for shared data (e.g., user list), and manage sessions.
- JSPs render responses using request/session attributes.
- Filters intercept requests to protected paths, redirecting unauthenticated users.
- Error pages and static resources are served via servlet container mappings.

This structure ensures separation of concerns, reusability, and ease of maintenance, aligning with security best practices (e.g., no sensitive data exposure).

## What Was Added
### 1. Core Java Components
- **UserEntry Model (`src/main/java/cs3220/model/UserEntry.java`):** Defines user data structure with fields for ID, name, email, password, and creation timestamp. Provides getters/setters for encapsulation.
- **Servlets:**
  - `Login.java`: Handles authentication, validates credentials against stored users, sets session on success.
  - `Register.java`: Processes registration, checks for duplicates, adds new users to context.
  - `Logout.java`: Invalidates session and redirects to login.
  - `Download.java`: Serves files (images) if authenticated, with proper MIME handling and error responses.
- **AuthFilter (`src/main/java/cs3220/filter/AuthFilter.java`):** Protects `/download.jsp` and `/download`, checks session for user attribute.
- **Bootstrap Listener (`src/main/java/cs3220/Bootstrap.java`):** Initializes a default test user and user ID sequence on context startup.

### 2. Web Components
- **JSP Pages:**
  - `index.jsp`: Login form with error handling and Bootstrap styling.
  - `register.jsp`: Registration form with validation messages.
  - `download.jsp`: Protected page displaying downloadable images in a card layout.
- **Static Assets:**
  - `css/styles.css`: Custom CSS for gradient backgrounds, card layouts, and responsive design per UI screenshots.
  - `404.html`: Custom error page for not-found responses.
  - `images/sample1.jpg`, `sample2.jpg`, `sample3.jpg`: Placeholder images for download functionality.
- **Configuration:**
  - `pom.xml`: Maven setup with Servlet API dependencies for Tomcat 9/10 compatibility.
  - `web.xml`: Servlet/filter mappings, welcome file, and error page configuration.

## Why Each Component Was Added
- **UserEntry:** Essential for representing user data consistently across the app, ensuring schema integrity (e.g., exact field matching per user rules).
- **Servlets:** Implement core features (login/logout/registration/downloads) as required, using session tracking for state management. Keeps logic modular and secure (e.g., no direct DB access, uses context for simplicity).
- **AuthFilter:** Enforces protection for sensitive pages, aligning with security rules by redirecting unauthenticated users without exposing data.
- **JSPs:** Provide user-friendly interfaces matching provided screenshots, using Bootstrap for responsiveness and external CSS for custom styling (no inline styles).
- **Bootstrap Listener:** Seeds a test user for easy testing, as suggested in requirements, without hardcoding in servlets.
- **Static Assets:** Enhance UX per screenshots (gradient, cards), handle errors, and enable downloads. Placeholders ensure immediate functionality.
- **Configurations:** Enable Maven build and servlet deployment, ensuring compatibility and no overengineering.

These additions directly address requirements (e.g., 100% feature coverage) while prioritizing simplicity (e.g., no Spring Boot) and security (e.g., session-based auth).

## How It Works: Step-by-Step Functionality
### 1. Project Setup and Initialization
- **Build:** Run `mvn clean package` to create the WAR file.
- **Deploy:** Deploy WAR to Tomcat (9 or 10). Access at `http://localhost:8080/LoginSession/`.
- **Bootstrap:** On startup, `Bootstrap` listener adds a default user (`test@example.com` / `pass`) and initializes user storage in servlet context.

### 2. User Registration Flow
- User visits `/register` (redirected from login page).
- Fills form in `register.jsp`; submits to `Register` servlet.
- Servlet validates fields; checks for email duplicates in context-stored list.
- If valid, creates `UserEntry`, assigns ID, adds to list, sets success message, forwards to login.
- If invalid, sets error, forwards back to form.

### 3. User Login Flow
- User visits `/` (serves `index.jsp` login form).
- Enters credentials; submits to `Login` servlet.
- Servlet retrieves user list from context, searches for matching email/password.
- If not found, sets "User not found" error, forwards to login.
- If password mismatch, sets "Incorrect password" error.
- On success, creates session with user attribute, redirects to `/download.jsp`.

### 4. Protected Download Flow
- Authenticated user accesses `/download.jsp` (protected by `AuthFilter`).
- Filter checks session for user; if absent, redirects to login.
- Page displays user name and image cards with download links.
- Clicking link calls `Download` servlet with file param.
- Servlet verifies session/auth, streams file from `/images/`, sets attachment headers.
- If file missing, returns 404; else, prompts download.

### 5. Logout Flow
- User clicks logout button in `download.jsp`, submits to `Logout` servlet.
- Servlet invalidates session, redirects to login page.

### 6. Error Handling
- Invalid paths trigger `404.html` via `web.xml` mapping.
- Servlet errors (e.g., missing file) return 404 or forward to JSP with error attributes.

## Security and Best Practices
- **Session Management:** Uses HttpSession for auth state; invalidated on logout.
- **Input Validation:** Basic checks in servlets (e.g., non-empty fields, email uniqueness).
- **No Sensitive Exposure:** Passwords stored in memory (context), not logged; no hardcoding.
- **Consistency:** Data structures match from model to UI (e.g., UserEntry fields).
- **Simplicity:** Minimal dependencies, no overengineering; follows user rules for readability and maintainability.

## Deployment and Running the Application
### Prerequisites
- **Java:** JDK 1.8+ (e.g., OpenJDK 11 via Homebrew: `brew install openjdk@11`).
- **Tomcat:** 9.0.100 (located at `/Users/chen/Projects/F25_CS3220/apache-tomcat-9.0.100/` in this setup).
- **Maven:** For building the WAR.

### Step-by-Step Deployment
1. **Build the Project:** In the project directory, run `mvn clean package` to generate `target/LoginSession.war`.
2. **Set JAVA_HOME:** `export JAVA_HOME=/opt/homebrew/opt/openjdk@11` (or equivalent for your JDK).
3. **Copy WAR to Tomcat:** `cp target/LoginSession.war /path/to/tomcat/webapps/`.
4. **Start Tomcat:** `/path/to/tomcat/bin/startup.sh`.
5. **Access Application:** Visit `http://localhost:8080/LoginSession/`.
6. **Stop Tomcat (When Done):** `/path/to/tomcat/bin/shutdown.sh`.

### Troubleshooting
- **Java Not Found:** Ensure JDK is installed and JAVA_HOME is set correctly.
- **Port Conflicts:** If 8080 is in use, update Tomcat's `conf/server.xml`.
- **Logs:** Check `/path/to/tomcat/logs/catalina.out` for errors.

## Testing and Usage
- **Default User:** Log in with `test@example.com` / `pass` (seeded by Bootstrap on deployment).
- **Register New User:** Create account via `/register`, log in, access downloads.
- **Verify Features:** Test login/logout, registration errors, protected access (e.g., `/download.jsp`), file downloads, 404 page.
- **Edge Cases:** Empty fields, duplicate emails, invalid logins, session timeouts.
- **Post-Testing:** Use the deployment steps to stop Tomcat and clean up.

This implementation ensures a functional, secure, and user-friendly app per requirements. For full setup, refer to the Deployment section.
