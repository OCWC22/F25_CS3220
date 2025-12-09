# Task ID: login-session
# Status: Completed

## Files Updated
- UPDATED: src/main/java/cs3220/servlet/Login.java
- UPDATED: src/main/java/cs3220/servlet/Register.java
- UPDATED: src/main/java/cs3220/filter/AuthFilter.java
- UPDATED: src/main/java/cs3220/servlet/Download.java
- UPDATED: src/main/java/cs3220/servlet/Logout.java
- UPDATED: src/main/webapp/index.jsp
- UPDATED: src/main/webapp/Register.jsp
- UPDATED: src/main/webapp/Download.jsp
- UPDATED: src/main/webapp/assets/css/Styles.css
- UPDATED: src/main/webapp/WEB-INF/web.xml
- MOVED: src/main/webapp/404.html (from WEB-INF/404.html)
- CREATED: src/main/webapp/assets/images/apples.jpg
- CREATED: src/main/webapp/assets/images/oranges.jpg
- CREATED: src/main/webapp/assets/images/bananas.jpg

## Summary
Implemented end-to-end session-tracked auth (login, logout, registration) with error handling and protected download flow. Added fully-styled JSPs, bootstrap-backed CSS, downloadable assets, and a custom 404 experience wired via web.xml.

## Reasoning & Trade-offs
- Used ServletContext-scoped user list per requirements to avoid unasked persistence layers.
- Auth filter guards both JSP and servlet routes to prevent bypass without over-complicating navigation.
- Generated placeholder images to satisfy download/testing needs while keeping repo self-contained.

## Issues Encountered
- Missing changelog structure: created new docs/changelogs entry per repo guidelines.
- No assets existed for download screen; generated gradient text renders via JShell to keep build portable.

## Future Work
- Persist registrations beyond JVM lifecycle via database or file store if/when requested.
- Replace inline JSP scriptlets with JSTL/EL for cleaner separation once permitted.
