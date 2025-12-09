# Eclipse Troubleshooting Guide

## Error: "The servlets named [Login] and [cs3220.servlet.Login] are both mapped to the url-pattern [/login]"

### Problem
This error occurs when servlets are registered **twice** - once via `@WebServlet` annotations and once via `web.xml`. Tomcat doesn't allow duplicate mappings.

### Solution ✅ FIXED
I've removed all `@WebServlet` and `@WebFilter` annotations from the servlet classes since we're using `web.xml` for configuration (as required by the assignment).

**Fixed Files:**
- ✅ `Login.java` - Removed `@WebServlet("/login")`
- ✅ `Logout.java` - Removed `@WebServlet("/logout")`
- ✅ `Register.java` - Removed `@WebServlet("/register")`
- ✅ `Download.java` - Removed `@WebServlet("/download")`
- ✅ `AuthFilter.java` - Removed `@WebFilter(urlPatterns = {"/download.jsp", "/download"})`
- ✅ `Bootstrap.java` - Removed `@WebListener` and added to `web.xml`

### Next Steps

1. **Clean the Project:**
   - Right-click **LoginSession** project
   - Select **Clean...**
   - Click **Clean**

2. **Update Maven Project:**
   - Right-click **LoginSession** project
   - Select **Maven** → **Update Project...**
   - Check **Force Update of Snapshots/Releases**
   - Click **OK**

3. **Rebuild:**
   - Right-click **LoginSession** project
   - Select **Build Project**

4. **Restart Tomcat Server:**
   - Stop the server if running
   - Start it again
   - The error should be resolved

## Additional Common Issues

### Issue: GuestBook Project Error
If you see errors about GuestBook project:
```
The main resource set specified [.../GuestBook] is not a directory or war file
```

**Solution:**
- Remove GuestBook from the server if it's configured
- Right-click server → **Add and Remove...**
- Remove GuestBook from "Configured" apps
- Keep only LoginSession

### Issue: Port 8080 Already in Use

**Solution:**
1. Check what's using port 8080:
   ```bash
   lsof -i :8080  # macOS/Linux
   netstat -ano | findstr :8080  # Windows
   ```

2. Change Tomcat port:
   - Double-click server in **Servers** view
   - Change port from 8080 to 8081
   - Save and restart server

### Issue: Maven Dependencies Not Found

**Solution:**
1. Right-click project → **Maven** → **Update Project**
2. Check **Force Update**
3. Click **OK**
4. Wait for dependencies to download

### Issue: Class Files Not Found

**Solution:**
1. Right-click project → **Properties**
2. Go to **Java Build Path** → **Source**
3. Verify:
   - `src/main/java` is included
   - `src/main/webapp` is included
4. Click **Apply and Close**
5. Clean and rebuild project

## Verification Checklist

After fixing, verify:

- [ ] No `@WebServlet` annotations in servlet classes
- [ ] No `@WebFilter` annotations in filter classes  
- [ ] All servlets registered in `web.xml`
- [ ] All filters registered in `web.xml`
- [ ] Bootstrap listener registered in `web.xml`
- [ ] Project builds without errors
- [ ] Server starts successfully
- [ ] Can access: http://localhost:8080/LoginSession/

## Quick Fix Commands

If you're still having issues, try this sequence:

1. **Stop Server**
2. **Clean Project** (Project → Clean)
3. **Update Maven** (Maven → Update Project, Force Update)
4. **Delete Server** (Right-click server → Delete, but keep configuration)
5. **Recreate Server** (New → Server → Tomcat v9.0)
6. **Add Project** (Right-click server → Add and Remove → Add LoginSession)
7. **Start Server**

This should resolve most deployment issues.

