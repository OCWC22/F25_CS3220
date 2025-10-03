package cs3220.servlet;

import cs3220.model.UsersEntity;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Login servlet handles authentication flow.
 * GET: displays login form (index.jsp)
 * POST: validates credentials and creates session
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Forward to login page
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UsersEntity user = UsersEntity.authenticate(email, password);
        
        if (user != null) {
            // Authentication successful - create session
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", user);
            resp.sendRedirect(req.getContextPath() + "/guestbook");
        } else {
            // Authentication failed - return to login with error
            req.setAttribute("error", "Email and password does not match");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
