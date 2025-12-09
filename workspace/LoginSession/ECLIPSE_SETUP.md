# Eclipse IDE Setup Guide for LoginSession Project

## Prerequisites

1. **Eclipse IDE for Enterprise Java and Web Developers** (or Eclipse IDE with Java EE tools)
   - Download from: https://www.eclipse.org/downloads/
   - Select "Eclipse IDE for Enterprise Java and Web Developers"

2. **Apache Tomcat Server** (v9.0 or later)
   - Download from: https://tomcat.apache.org/download-90.cgi
   - Extract to a directory (e.g., `/opt/tomcat` or `C:\apache-tomcat-9.0`)

3. **Maven** (usually comes with Eclipse, but can install separately)
   - Eclipse includes m2e (Maven Integration for Eclipse)

## Step-by-Step Setup

### Step 1: Import Maven Project into Eclipse

1. Open Eclipse IDE
2. Go to **File** → **Import**
3. Select **Maven** → **Existing Maven Projects**
4. Click **Next**
5. In the "Root Directory" field, browse to:
   ```
   /Users/chen/Projects/F25_CS3220/workspace/LoginSession
   ```
6. Eclipse should detect the `pom.xml` file
7. Click **Finish**

### Step 2: Wait for Maven Build

- Eclipse will automatically download dependencies from Maven
- Watch the **Progress** view at the bottom right
- Wait until "Building workspace" completes
- If you see errors, right-click project → **Maven** → **Update Project** → Check "Force Update"

### Step 3: Configure Tomcat Server in Eclipse

1. Go to **Window** → **Show View** → **Servers**
   - If "Servers" is not available, go to **Window** → **Perspective** → **Open Perspective** → **Java EE**

2. Right-click in the Servers view → **New** → **Server**

3. Select **Apache** → **Tomcat v9.0 Server** (or your version)
   - If Tomcat 9.0 is not listed:
     - Click **Download additional server adapters**
     - Install "Tomcat v9.0 Server Adapter"

4. Click **Next**

5. Browse to your Tomcat installation directory
   - Example: `/opt/tomcat` or `C:\apache-tomcat-9.0`
   - Click **Next**

6. Select **LoginSession** from the "Available" list
   - Click **Add >** to move it to "Configured"
   - Click **Finish**

### Step 4: Configure Project Facets (if needed)

1. Right-click **LoginSession** project → **Properties**
2. Select **Project Facets**
3. Ensure these are checked:
   - ☑ **Java** (version 1.8)
   - ☑ **Dynamic Web Module** (version 4.0)
   - ☑ **JavaScript** (version 1.0)
4. Click **Apply and Close**

### Step 5: Set Deployment Assembly

1. Right-click **LoginSession** project → **Properties**
2. Select **Deployment Assembly**
3. Verify these mappings exist:
   - `/src/main/webapp` → `/` (WebContent)
   - `Maven Dependencies` → `/WEB-INF/lib`
4. If missing, click **Add** to add them
5. Click **Apply and Close**

### Step 6: Add Sample Images (Required)

Before running, add three sample images to:
```
src/main/webapp/images/
```

Files needed:
- `sample1.jpg` (Apple image)
- `sample2.jpg` (Orange image)
- `sample3.jpg` (Banana image)

You can use any fruit images or download sample images from the internet.

### Step 7: Run the Project

1. In the **Servers** view, right-click on **Tomcat v9.0 Server**
2. Select **Start**
   - Wait for server to start (check Console view)
   - You should see: "Server startup in [XXXX] milliseconds"

3. Once started, right-click the server → **Add and Remove...**
   - Ensure **LoginSession** is in "Configured" column
   - If not, select it and click **Add >**
   - Click **Finish**

4. Open a web browser and navigate to:
   ```
   http://localhost:8080/LoginSession/
   ```
   or
   ```
   http://localhost:8080/LoginSession/index.jsp
   ```

### Step 8: Test the Application

1. **Login Page**: Should show the dark-themed login form
2. **Test Login**: Try logging in with a non-existent email to see "User not found" error
3. **Register**: Click "Create New Account" to register a new user
4. **Download Page**: After successful login, you should see the download page with images table
5. **Logout**: Click logout button to return to login page
6. **404 Error**: Navigate to a non-existent page to test 404 error page

## Troubleshooting

### Maven Dependencies Not Downloading

1. Right-click project → **Maven** → **Update Project**
2. Check **Force Update of Snapshots/Releases**
3. Click **OK**

### Server Won't Start

1. Check if port 8080 is already in use:
   ```bash
   lsof -i :8080  # macOS/Linux
   netstat -ano | findstr :8080  # Windows
   ```
2. Change Tomcat port in server configuration:
   - Double-click server in Servers view
   - Change port from 8080 to 8081 (or another available port)

### ClassNotFoundException or Servlet Not Found

1. Ensure project is properly configured as Dynamic Web Project
2. Check **Project Facets** (Step 4 above)
3. Clean and rebuild:
   - **Project** → **Clean** → Select LoginSession → **Clean**

### JSP Pages Not Found

1. Verify `src/main/webapp` is set as WebContent root
2. Check **Deployment Assembly** (Step 5 above)
3. Ensure `index.jsp` exists in `src/main/webapp/`

### Images Not Displaying

1. Verify images exist in `src/main/webapp/images/`
2. Check file names match exactly: `sample1.jpg`, `sample2.jpg`, `sample3.jpg`
3. Refresh the browser (Ctrl+F5 or Cmd+Shift+R)

## Alternative: Run with Maven Tomcat Plugin

You can also run directly from command line using Maven:

```bash
cd /Users/chen/Projects/F25_CS3220/workspace/LoginSession
mvn clean package
mvn tomcat7:run
```

Then access: http://localhost:8080/LoginSession/

## Project Structure in Eclipse

After import, your project should look like:

```
LoginSession
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── cs3220/
│   │   │       ├── Bootstrap.java
│   │   │       ├── filter/
│   │   │       │   └── AuthFilter.java
│   │   │       ├── model/
│   │   │       │   └── UserEntry.java
│   │   │       └── servlet/
│   │   │           ├── Download.java
│   │   │           ├── Login.java
│   │   │           ├── Logout.java
│   │   │           └── Register.java
│   │   └── webapp/
│   │       ├── 404.html
│   │       ├── css/
│   │       │   └── styles.css
│   │       ├── images/
│   │       ├── download.jsp
│   │       ├── index.jsp
│   │       ├── register.jsp
│   │       └── WEB-INF/
│   │           └── web.xml
├── target/
├── pom.xml
└── .project
```

## Quick Reference

- **Project Type**: Maven Dynamic Web Project
- **Java Version**: 1.8
- **Servlet API**: 4.0
- **Server**: Apache Tomcat 9.0+
- **Default Port**: 8080
- **Context Path**: /LoginSession

