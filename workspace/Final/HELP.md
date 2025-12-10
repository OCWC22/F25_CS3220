# CS3220 Final - Time Clock Application

## Prerequisites

### MySQL Setup (REQUIRED)
1. Start MySQL server:
   ```bash
   mysql.server start
   ```

2. Create database and user (run in MySQL as root):
   ```sql
   CREATE DATABASE IF NOT EXISTS CS3220;
   CREATE USER IF NOT EXISTS 'cs3220'@'localhost' IDENTIFIED BY 'test123';
   GRANT ALL PRIVILEGES ON CS3220.* TO 'cs3220'@'localhost';
   FLUSH PRIVILEGES;
   ```

## Database Configuration
- **Database:** CS3220
- **Username:** cs3220
- **Password:** test123

## How to Run

```bash
cd workspace/Final
mvn clean spring-boot:run
```

Open http://localhost:8080 in browser.

## Demo Users (auto-seeded on first run)
- tom@example.com / password
- jane@example.com / password
- john@example.com / password

## Features

1. **Login/Register SPA** - Single page toggles between login and register forms
2. **Time Clock Page** - Clock In, Clock Out, Sign Out buttons
3. **Records Table** - Shows all clock entries with alternating colors and hover highlight

## Project Structure (matches assignment)

```
Final/
├── src/main/java/cs3220/
│   ├── FinalApplication.java
│   ├── config/
│   │   └── AppConfig.java
│   ├── controller/
│   │   ├── ApiController.java
│   │   └── IndexController.java
│   ├── model/
│   │   ├── ClockedEntry.java
│   │   ├── ClockedEntryDto.java
│   │   ├── UserEntry.java
│   │   └── UserEntryDto.java
│   └── repository/
│       ├── ClockedEntryRepository.java
│       └── UserEntryRepository.java
├── src/main/resources/
│   ├── static/
│   │   ├── css/Styles.css
│   │   └── scripts/
│   │       ├── clocked.js
│   │       └── index.js
│   ├── templates/
│   │   ├── Clocked.ftlh
│   │   └── index.ftlh
│   └── application.properties
└── pom.xml
```

## Grading Checklist
- [x] MySQL database storage (10pt)
- [x] Access Control (10pt)
- [x] SPA design for login/register (10pt)
- [x] Correct date/time format (10pt)
- [x] Correct user info on actions (20pt)
- [x] Sign Out redirects to login (10pt)
- [x] External CSS, JS, Bootstrap (10pt)
