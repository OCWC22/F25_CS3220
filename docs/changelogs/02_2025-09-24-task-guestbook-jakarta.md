# Changelog: 02_2025-09-24 - Migrate GuestBook to Jakarta EE (Task guestbook-jakarta)

**Task:** [guestbook-jakarta] Restore GuestBook Maven/Tomcat project functionality  
**Status:** Done

### Files Updated:
- **UPDATED:** `workspace/GuestBook/pom.xml` – target Java 17 and depend on `jakarta.servlet:jakarta.servlet-api` 6.0.0 (provided scope).
- **UPDATED:** `workspace/GuestBook/src/main/java/cs3220/servlet/AddComment.java` – migrate servlet imports to `jakarta.servlet` package.
- **UPDATED:** `workspace/GuestBook/src/main/java/cs3220/servlet/DeleteEntry.java` – migrate servlet imports to `jakarta.servlet` package.
- **UPDATED:** `workspace/GuestBook/src/main/java/cs3220/servlet/EditEntry.java` – migrate servlet imports to `jakarta.servlet` package.
- **UPDATED:** `workspace/GuestBook/src/main/java/cs3220/servlet/GuestBook.java` – migrate servlet imports to `jakarta.servlet` package.
- **UPDATED:** `workspace/GuestBook/src/main/webapp/WEB-INF/web.xml` – update deployment descriptor to Jakarta EE 10 schema.

### Description:
Retrofitted the GuestBook WAR project for Jakarta EE 10 compatibility by updating servlet imports, deployment descriptor schema, and Maven compiler settings. The project now aligns with Tomcat 10+/Jakarta servlet expectations.

### Reasoning:
Servlet 5+ containers (e.g., Tomcat 10, Eclipse IDE defaults) require the `jakarta.servlet` namespace. Updating the dependency and imports ensures the application deploys successfully without `ClassNotFoundException` or `NoSuchMethodError` issues.

### Key Decisions & Trade-off:
- Chose Java 17 as the Maven compiler target because it is the baseline LTS supported by current servlet containers and aligns with Jakarta EE 10.
- Did not attempt to run `mvn` locally because the tool is not installed (`zsh: command not found: mvn`). Installation is required before build/test automation can run on this machine.

### Follow-up Actions:
- Install Maven locally (`brew install maven`) or use Eclipse's embedded Maven to build and deploy the WAR.
- If deploying to Tomcat 9 or earlier, downgrade to `javax.servlet` artifacts instead; otherwise keep the Jakarta configuration for Tomcat 10+.
