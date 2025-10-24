package cs3220;

import cs3220.model.UserEntry;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class Bootstrap implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        List<UserEntry> users = new ArrayList<>();
        users.add(new UserEntry(1, "Test User", "test@example.com", "pass"));
        sce.getServletContext().setAttribute("users", users);
        sce.getServletContext().setAttribute("userIdSeq", new AtomicInteger(2));
    }
    public void contextDestroyed(ServletContextEvent sce) {}
}
