## Task ID
- **lab9-spring-boot-complete**
- **Status:** Done

## Files Updated
- `workspace/BeanHolidayCalendar/pom.xml` — UPDATED
- `workspace/BeanHolidayCalendar/src/main/java/cs3220/controller/IndexController.java` — UPDATED
- `workspace/BeanHolidayCalendar/src/main/java/cs3220/model/HolidayForm.java` — CREATED
- `workspace/BeanHolidayCalendar/src/main/resources/templates/index.ftlh` — REVIEWED (no change)
- `workspace/BeanHolidayCalendar/src/main/resources/templates/addHoliday.ftlh` — UPDATED
- `workspace/BeanHolidayCalendar/src/main/resources/templates/updateHoliday.ftlh` — UPDATED

## Summary
Replaced ad-hoc request handling with a validated MVC flow backed by a reusable `HolidayForm` bean and FreeMarker Spring macros. Updated the add/update templates to use dropdowns with proper validation feedback and ensured dependencies support Jakarta Bean Validation.

## Reasoning & Trade-offs
- Introduced a dedicated form model to centralize dropdown parsing and validation, reducing controller branching.
- Leveraged `spring.ftl` macros for concise error rendering instead of manual conditionals, which required pulling in Bootstrap assets for consistent styling.
- Added `spring-boot-starter-validation`; Spring already manages its lifecycle so no extra configuration was necessary.

## Issues Encountered
- Existing templates lacked Spring form bindings; converting to macro-based markup required ensuring dropdown data is exposed via `@ModelAttribute` methods to avoid `null` bindings.

## Future Work
- Add persistence (e.g., JPA) if the course later requires data to survive restarts.
