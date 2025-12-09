package cs3220.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cs3220.model.UserEntry;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "User not found");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        @SuppressWarnings("unchecked")
        List<UserEntry> users = (List<UserEntry>) getServletContext().getAttribute("users");

        // Check if email exists
        UserEntry user = null;
        if (users != null) {
            for (UserEntry u : users) {
                if (u.getEmail().equals(email)) {
                    // Email exists, now check password
                    if (u.getPassword().equals(password)) {
                        user = u;
                    }
                    break; // Found the email, exit loop
                }
            }
        }

        if (user != null) {
            // Successful login
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/Download.jsp");
        } else {
            // Email doesn't exist or password is wrong - show "User not found"
            request.setAttribute("error", "User not found");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

