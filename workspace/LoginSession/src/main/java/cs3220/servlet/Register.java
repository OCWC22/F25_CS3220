package cs3220.servlet;

import cs3220.model.UserEntry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends HttpServlet {
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Please fill out all fields");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        ServletContext ctx = getServletContext();
        List<UserEntry> users = (List<UserEntry>) ctx.getAttribute("users");
        if (users == null) {
            users = new ArrayList<>();
            ctx.setAttribute("users", users);
            ctx.setAttribute("userIdSeq", new AtomicInteger(1));
        }

        for (UserEntry u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                request.setAttribute("error", "Email already registered");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
        }

        AtomicInteger seq = (AtomicInteger) ctx.getAttribute("userIdSeq");
        UserEntry entry = new UserEntry(seq.getAndIncrement(), name, email, password);
        users.add(entry);

        request.setAttribute("message", "Registration successful. Please log in.");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/register.jsp");
    }
}
