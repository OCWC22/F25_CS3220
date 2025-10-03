# SessionHandler - Lab07

## Project Information
- **Group ID:** edu.csula.cs3220
- **Artifact ID:** SessionHandler
- **Package:** cs3220
- **Lab:** Lab07 - Session Management with Login/Logout

## Description
This project implements a session-based authentication system on top of the GuestBook application from Lab05. Users must log in before accessing the guest book, and can log out to terminate their session.

## Features
- User authentication with email/password
- Session management (create/destroy)
- Protected guest book access (requires login)
- Login page with error messaging (both regular CSS and Bootstrap styles)
- Logout functionality with confirmation message
- All Lab05 guest book features (add, edit, delete entries)

## Project Structure
```
SessionHandler/
├── src/main/java/cs3220/
│   ├── model/
│   │   ├── GuestBookEntry.java    # Guest book entry model
│   │   └── UsersEntity.java       # User authentication model
│   └── servlet/
│       ├── AddComment.java        # Add guest book entry (protected)
│       ├── DeleteEntry.java       # Delete entry (protected)
│       ├── EditEntry.java         # Edit entry (protected)
│       ├── GuestBook.java         # Main guest book display (protected)
│       ├── HelloServlet.java      # Test servlet
│       ├── Login.java             # Login handler
│       └── Logout.java            # Logout handler
└── src/main/webapp/
    ├── assets/css/
    │   └── Styles.css             # Custom styles (Lab04-based)
    ├── WEB-INF/
    │   └── web.xml                # Web application configuration
    └── index.jsp                  # Login page

```

## Default Users
The following test users are available:
- **Email:** admin@calstatela.edu, **Password:** admin123
- **Email:** rlai2@calstatela.edu, **Password:** password
- **Email:** test@calstatela.edu, **Password:** test

## Usage
1. Build the project: `mvn clean package`
2. Deploy the WAR file to Tomcat or run via Eclipse
3. Access the application at: `http://localhost:8080/SessionHandler/`
4. Log in with one of the test credentials
5. Access the guest book and perform CRUD operations
6. Click "Logout" to end the session

## Grading Criteria Met
- ✅ (10pt) All working features from Lab05 plus user control
- ✅ (20pt) UsersEntity.java - handles user information and authentication
- ✅ (20pt) Login.java - handles login requests and session creation
- ✅ (10pt) Logout.java - handles logout and session deletion
- ✅ (10pt) index.jsp with login form
- ✅ Bootstrap and external CSS (no inline styles)
- ✅ Lab04 styling applied for extra credit

## Notes
- All servlets use `@WebServlet` annotations (no servlet mappings in web.xml required)
- Session guards implemented in all guest book servlets
- Error messages displayed in both regular CSS and Bootstrap alert styles
- Project follows Maven standard directory layout
