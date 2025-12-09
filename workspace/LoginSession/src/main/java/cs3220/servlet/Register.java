package cs3220.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cs3220.model.UserEntry;

public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (email == null || email.trim().isEmpty() ||
            name == null || name.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("/Register.jsp").forward(request, response);
            return;
        }

        @SuppressWarnings("unchecked")
        List<UserEntry> users = (List<UserEntry>) getServletContext().getAttribute("users");

        // Check if email already exists
        if (users != null) {
            for (UserEntry u : users) {
                if (u.getEmail().equals(email)) {
                    request.setAttribute("error", "Email already exists");
                    request.getRequestDispatcher("/Register.jsp").forward(request, response);
                    return;
                }
            }
        }

        // Create new user
        UserEntry newUser = new UserEntry(email.trim(), name.trim(), password);
        if (users != null) {
            users.add(newUser);
        }

        request.setAttribute("message", "Registration successful! Please login.");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}

