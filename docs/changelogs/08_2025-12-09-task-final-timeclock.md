# CS3220 Final - Time Clock Application

**Task ID:** Final Project  
**Status:** COMPLETE  
**Date:** 2025-12-09

---

## Files Created

| File | Description |
|------|-------------|
| `workspace/Final/pom.xml` | Maven config with MySQL, FreeMarker, JPA, Validation |
| `workspace/Final/src/main/java/cs3220/FinalApplication.java` | Spring Boot entry point |
| `workspace/Final/src/main/java/cs3220/config/AppConfig.java` | BCrypt + FreeMarker java8 config |
| `workspace/Final/src/main/java/cs3220/model/UserEntry.java` | JPA entity for users |
| `workspace/Final/src/main/java/cs3220/model/UserEntryDto.java` | User DTO (no password) |
| `workspace/Final/src/main/java/cs3220/model/ClockedEntry.java` | JPA entity for clock records |
| `workspace/Final/src/main/java/cs3220/model/ClockedEntryDto.java` | Clock DTO with formatted date/time |
| `workspace/Final/src/main/java/cs3220/repository/UserEntryRepository.java` | User JPA repository |
| `workspace/Final/src/main/java/cs3220/repository/ClockedEntryRepository.java` | Clock JPA repository |
| `workspace/Final/src/main/java/cs3220/controller/IndexController.java` | View controller |
| `workspace/Final/src/main/java/cs3220/controller/ApiController.java` | REST API controller |
| `workspace/Final/src/main/resources/application.properties` | MySQL config |
| `workspace/Final/src/main/resources/templates/index.ftlh` | Login/Register SPA |
| `workspace/Final/src/main/resources/templates/Clocked.ftlh` | Time Clock page |
| `workspace/Final/src/main/resources/static/css/Styles.css` | All custom styles |
| `workspace/Final/src/main/resources/static/scripts/index.js` | Login/Register JS |
| `workspace/Final/src/main/resources/static/scripts/clocked.js` | Time Clock JS |

---

## Summary

Implemented CS3220 Final as a Spring Boot Time Clock application with:

1. **MySQL Database** - Persists users and clock entries
2. **SPA Login/Register** - Single page toggles between login and register forms
3. **REST API** - `/api/login`, `/api/register`, `/api/clocked` endpoints
4. **Time Clock Page** - Clock In, Clock Out, Sign Out buttons
5. **Records Table** - Alternating blue/white rows, black hover highlight
6. **Date/Time Format** - MM-dd-yyyy for date, H:mm for time
7. **Constructor Injection** - All dependencies injected via constructors
8. **BCrypt Password Hashing** - Secure password storage
9. **Access Control** - Only logged-in users can access clock page
10. **External Assets** - Bootstrap WebJar + custom CSS/JS

---

## Technical Details

- **Database:** MySQL (CS3220, cs3220/test123)
- **Port:** 8080
- **Password Hashing:** BCrypt via spring-security-crypto
- **Date/Time:** java.time.LocalDateTime with freemarker-java8

---

## Grading Checklist

| Criterion | Points | Status |
|-----------|--------|--------|
| MySQL database storage | 10 | ✅ |
| Access control | 10 | ✅ |
| SPA for login/register | 10 | ✅ |
| Correct date/time format | 10 | ✅ |
| Correct user info on actions | 20 | ✅ |
| Sign Out redirects to login | 10 | ✅ |
| External CSS, JS, Bootstrap | 10 | ✅ |
| **Total** | **80** | ✅ |
| Extra: Full SPA (optional) | +5 | ❌ (clock page uses standard nav) |

---

## How to Run

1. Start MySQL and create database/user:
   ```sql
   CREATE DATABASE IF NOT EXISTS CS3220;
   CREATE USER IF NOT EXISTS 'cs3220'@'localhost' IDENTIFIED BY 'test123';
   GRANT ALL PRIVILEGES ON CS3220.* TO 'cs3220'@'localhost';
   FLUSH PRIVILEGES;
   ```
2. `cd workspace/Final && mvn clean spring-boot:run`
3. Open http://localhost:8080
4. Demo users: tom@example.com, jane@example.com, john@example.com (password: password)
