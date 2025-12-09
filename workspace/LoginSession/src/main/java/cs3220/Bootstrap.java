package cs3220;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import cs3220.model.UserEntry;

public class Bootstrap implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        // Initialize user list
        List<UserEntry> users = new ArrayList<>();
        
        // Add some sample users
        users.add(new UserEntry("john@gmail.com", "John Doe", "password123"));
        users.add(new UserEntry("jane@gmail.com", "Jane Smith", "password456"));
        
        context.setAttribute("users", users);
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup if needed
    }
}

