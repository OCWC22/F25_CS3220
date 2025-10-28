# Changelog: 01_2025-10-23-task-Midterm-birthday_calendar_implementation.md

**Task:** Midterm Birthday Calendar Application Implementation
**Status:** Done

### Files Updated:
- **CREATED:** `pom.xml` – Maven configuration with coordinates edu.csula.cs3220:Midterm
- **CREATED:** `src/main/java/cs3220/model/BirthdayEntity.java` – Birthday data model class
- **CREATED:** `src/main/java/cs3220/model/UserEntity.java` – User data model class
- **CREATED:** `src/main/java/cs3220/model/DataStore.java` – In-memory data storage manager
- **CREATED:** `src/main/java/cs3220/servlet/AddBirthday.java` – Servlet for adding new birthdays
- **CREATED:** `src/main/java/cs3220/servlet/Birthday.java` – Main birthday list servlet
- **CREATED:** `src/main/java/cs3220/servlet/DeleteBirthday.java` – Servlet for deleting birthdays
- **CREATED:** `src/main/java/cs3220/servlet/Login.java` – User login servlet
- **CREATED:** `src/main/java/cs3220/servlet/Logout.java` – User logout servlet
- **CREATED:** `src/main/java/cs3220/servlet/Register.java` – User registration servlet
- **CREATED:** `src/main/java/cs3220/servlet/UpdateBirthday.java` – Servlet for updating birthdays
- **CREATED:** `src/main/webapp/index.jsp` – Application entry point with redirect
- **CREATED:** `src/main/webapp/WEB-INF/views/login.jsp` – Login page view
- **CREATED:** `src/main/webapp/WEB-INF/views/register.jsp` – Registration page view
- **CREATED:** `src/main/webapp/WEB-INF/views/birthday.jsp` – Birthday list page view
- **CREATED:** `src/main/webapp/WEB-INF/views/add-birthday.jsp` – Add birthday page view
- **CREATED:** `src/main/webapp/WEB-INF/views/update-birthday.jsp` – Update birthday page view
- **CREATED:** `src/main/webapp/assets/css/styles.css` – External CSS with Bootstrap styling
- **CREATED:** `src/main/webapp/WEB-INF/web.xml` – Web application configuration

### Description:
Implemented a complete friends birthday calendar application with all required features including user registration, login/logout with session tracking, birthday CRUD operations, and proper security (login-required for birthday pages).

### Reasoning:
This implementation follows the exact specifications provided in the midterm requirements, ensuring all grading criteria are met including proper package structure (cs3220.model, cs3220.servlet), session management, external CSS with Bootstrap, and all specified servlet mappings.

### Key Decisions & Trade-offs:
- Used in-memory data storage (DataStore) for simplicity and to meet requirements without database setup
- Implemented exact servlet URL mappings as specified (/Login, /Register, /Birthday, etc.)
- Used HttpSession for user authentication state management
- Applied Bootstrap 5 for styling with custom CSS overrides to match design requirements
- All birthday lists are automatically sorted by month/day for better user experience
