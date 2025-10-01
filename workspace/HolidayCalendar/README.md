# Holiday Calendar - CS3220 Assignment

A Java Servlet web application for managing US holidays with a dynamic calendar interface.

## Project Information

- **Group ID**: `edu.csula.cs3220`
- **Artifact ID**: `HolidayCalendar`
- **Package**: `cs3220`
- **Java Version**: 1.8
- **Servlet API**: javax.servlet 4.0.1

## Features

- View all holidays sorted by date
- Add new holidays with dropdown date selectors
- Update existing holiday dates and names
- Delete holidays
- Duplicate date prevention
- Gradient background styling
- Table row hover highlighting

## Project Structure

```
HolidayCalendar/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── cs3220/
│       │       ├── model/
│       │       │   └── HolidayEntry.java
│       │       └── servlet/
│       │           ├── HolidayCalendar.java
│       │           ├── AddHoliday.java
│       │           ├── UpdateHoliday.java
│       │           └── DeleteHoliday.java
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml
│           └── styles/
│               └── holiday.css
```

## Components

### Model
- **HolidayEntry.java**: Represents a holiday with id, date (LocalDate), and name

### Servlets
- **HolidayCalendar.java** (`/holidays`): Main page displaying holiday table
- **AddHoliday.java** (`/add`): Form and handler for adding new holidays
- **UpdateHoliday.java** (`/update`): Form and handler for updating holidays
- **DeleteHoliday.java** (`/delete`): Handler for deleting holidays

### Styling
- **holiday.css**: External stylesheet with gradient background and table styling

## Setup Instructions

### Eclipse IDE

1. **Import Project**:
   - File → Import → Existing Maven Projects
   - Browse to the `HolidayCalendar` folder
   - Click Finish

2. **Configure Tomcat**:
   - Window → Preferences → Server → Runtime Environments
   - Add Apache Tomcat 9.x
   - Point to your Tomcat installation directory

3. **Add to Server**:
   - Right-click project → Run As → Run on Server
   - Select Tomcat server
   - Click Finish

4. **Access Application**:
   - Open browser to `http://localhost:8080/HolidayCalendar/holidays`

### Command Line (Maven)

```bash
# Build the project
mvn clean package

# Deploy the generated WAR file
# Copy target/HolidayCalendar-1.0-SNAPSHOT.war to Tomcat's webapps directory
cp target/HolidayCalendar-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/

# Start Tomcat
$CATALINA_HOME/bin/catalina.sh run
```

## Initial Data

The application seeds with 8 US holidays:
- New Year's Day (2024-01-01)
- Martin Luther King Jr. Day (2024-01-15)
- Lincoln's Birthday (2024-02-12)
- Presidents' Day (2024-02-19)
- Independence Day (2024-07-04)
- Labor Day (2024-09-02)
- Thanksgiving Day (2024-11-28)
- Christmas Day (2024-12-25)

## Testing Checklist

- [ ] Main page displays all holidays sorted by date
- [ ] Add new holiday with unique date
- [ ] Attempt to add duplicate date (should be ignored)
- [ ] Update existing holiday date and name
- [ ] Delete a holiday
- [ ] Verify gradient background displays correctly
- [ ] Verify table row hover effect (lightblue)
- [ ] Verify all elements are center-aligned
- [ ] Verify dropdown date selectors work correctly

## Technical Notes

- Uses `ServletContext` for in-memory storage
- Holidays automatically sorted by date after mutations
- Date validation prevents invalid dates
- HTML escaping prevents XSS vulnerabilities
- Responsive to invalid inputs (redirects gracefully)

## Submission

To submit:
1. Ensure all files are present
2. Zip the entire `HolidayCalendar` folder
3. Submit the zip file

```bash
cd workspace
zip -r HolidayCalendar.zip HolidayCalendar/
```
