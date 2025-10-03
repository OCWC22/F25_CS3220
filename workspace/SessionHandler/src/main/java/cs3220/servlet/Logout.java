package cs3220.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Logout servlet handles session termination.
 * Invalidates the session and redirects to login page with logout message.
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Invalidate session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        
        // Set logout message and redirect to login
        req.setAttribute("logoutMessage", "You are logged out");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
