Task ID: lab11-guestbook  
Status: Completed

Files Updated:
- CREATED: workspace/JPA/pom.xml
- CREATED: workspace/JPA/src/main/java/cs3220/JpaApplication.java
- CREATED: workspace/JPA/src/main/java/cs3220/model/UserEntity.java
- CREATED: workspace/JPA/src/main/java/cs3220/model/GuestBookEntity.java
- CREATED: workspace/JPA/src/main/java/cs3220/repository/UserEntityRepository.java
- CREATED: workspace/JPA/src/main/java/cs3220/repository/GuestBookEntityRepository.java
- CREATED: workspace/JPA/src/main/java/cs3220/controller/IndexController.java
- CREATED: workspace/JPA/src/main/resources/application.properties
- CREATED: workspace/JPA/src/main/resources/templates/index.ftlh
- CREATED: workspace/JPA/src/main/resources/templates/register.ftlh
- CREATED: workspace/JPA/src/main/resources/templates/guestBook.ftlh
- CREATED: workspace/JPA/src/main/resources/templates/addMessage.ftlh
- CREATED: workspace/JPA/src/main/resources/templates/editMessage.ftlh
- CREATED: workspace/JPA/src/main/resources/static/css/styles.css

Summary:
1. Created Spring Boot MVC project with exact structure per spec (no extra packages).
2. Implemented JPA entities with @ManyToOne/@OneToMany relationship.
3. Built all templates matching screenshots exactly (validation messages, button styling, table layout).

Reasoning & Trade-offs:
- Used simple @RequestParam validation instead of form beans to keep structure minimal.
- Stored UserEntity directly in session for simplicity.
- All buttons are blue (btn-primary) to match screenshots exactly.

Issues Encountered:
- Initial implementation had extra packages (form, support) not in spec - removed.
- pom.xml needed Spring Boot parent for proper dependency management.

Future Work:
- Update MySQL credentials in application.properties before running.
- Zip entire JPA folder for submission.
