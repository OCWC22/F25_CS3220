# Lab 13 – REST API + SPA Message Board

**Task ID:** Lab 13  
**Status:** COMPLETE  
**Date:** 2025-12-09

---

## Files Updated

| File | Status |
|------|--------|
| `workspace/Api/pom.xml` | CREATED |
| `workspace/Api/src/main/java/cs3220/ApiApplication.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/config/AppConfig.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/model/UserEntry.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/model/MessageEntry.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/repository/UserEntryRepository.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/repository/MessageEntryRepository.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/dto/UserEntryDto.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/dto/MessageEntryDto.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/dto/AuthRequestDto.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/dto/AuthResponseDto.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/dto/RegisterRequestDto.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/dto/MessageCreateDto.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/dto/MessageUpdateDto.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/service/DtoMapper.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/service/AuthService.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/service/MessageService.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/controller/IndexController.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/controller/AuthController.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/controller/MessageController.java` | CREATED |
| `workspace/Api/src/main/java/cs3220/controller/GlobalExceptionHandler.java` | CREATED |
| `workspace/Api/src/main/resources/application.properties` | CREATED |
| `workspace/Api/src/main/resources/templates/index.ftlh` | CREATED |
| `workspace/Api/src/main/resources/templates/messageboard.ftlh` | CREATED |
| `workspace/Api/src/main/resources/static/css/Styles.css` | CREATED |
| `workspace/Api/src/main/resources/static/javascript/LoginAndRegistrationAndMessageBoard.js` | CREATED |
| `workspace/Api/src/test/java/cs3220/ApiApplicationTests.java` | CREATED |
| `workspace/Api/.gitignore` | CREATED |

---

## Summary

Implemented Lab 13 as a Spring Boot REST API + Single Page Application (SPA) message board with:

1. **User Registration & Login** – BCrypt password hashing, unique username enforcement
2. **Message Board CRUD** – Create, Read, Update, Delete messages via REST API
3. **Access Control** – Only message owners can edit/delete their own messages (403 Forbidden otherwise)
4. **SPA Architecture** – Two Freemarker templates (`index.ftlh` for login/register, `messageboard.ftlh` for board), all interactions via AJAX
5. **DTO Pattern** – Clean separation between JPA entities and API responses
6. **External Styling** – Bootstrap 5.3.3 via WebJar + custom CSS

---

## Reasoning & Trade-offs

- **H2 In-Memory DB** – Simplifies setup for grading; data resets on restart
- **BCrypt** – Industry-standard password hashing; imported via `spring-security-crypto` (no full Spring Security)
- **Stateless API** – Every mutating request includes `userId` in payload/query param per assignment spec
- **EAGER fetch on MessageEntry.user** – Ensures author data always available for DTO mapping
- **ISO 8601 timestamps** – `OffsetDateTime` serialized via Jackson for "correct date format" requirement

---

## Issues Encountered

- Port 8080 conflict → switched to 8081
- Missing `-parameters` compiler flag caused Spring to fail binding `@PathVariable`/`@RequestParam` → added `maven-compiler-plugin` config

---

## How to Run

```bash
cd workspace/Api
mvn clean spring-boot:run
```

Open http://localhost:8081 in browser.

**Demo credentials:** `demo` / `password`

---

## Grading Checklist

| Criterion | Points | Status |
|-----------|--------|--------|
| User Registration | 10 | ✅ |
| User Login | 10 | ✅ |
| Add new message(s) | 10 | ✅ |
| Edit existing message(s) | 10 | ✅ |
| Delete message(s) | 10 | ✅ |
| Access Control | 10 | ✅ |
| Correct date format | 10 | ✅ |
| Correct user info on messages | 10 | ✅ |
| Logout redirects to login | 10 | ✅ |
| External CSS, JS, Bootstrap | 10 | ✅ |
