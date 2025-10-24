package cs3220.servlet;

import cs3220.model.UserEntry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ServletContext context = getServletContext();
        List<UserEntry> users = (List<UserEntry>) context.getAttribute("users");

        if (users == null || users.isEmpty()) {
            request.setAttribute("error", "User not found");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        UserEntry match = null;
        for (UserEntry user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                match = user;
                break;
            }
        }

        if (match == null) {
            request.setAttribute("error", "User not found");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        if (!match.getPassword().equals(password)) {
            request.setAttribute("error", "Incorrect password");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("user", match);
        response.sendRedirect(request.getContextPath() + "/download.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
