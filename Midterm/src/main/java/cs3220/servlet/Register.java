package cs3220.servlet;

import cs3220.model.DataStore;
import cs3220.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = p(req, "email").toLowerCase();
        String name = p(req, "name");
        String password = p(req, "password");

        if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Please fill out all fields.");
            req.setAttribute("emailValue", email);
            req.setAttribute("nameValue", name);
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        if (DataStore.USERS.containsKey(email)) {
            req.setAttribute("error", "User already exist");
            req.setAttribute("emailValue", email);
            req.setAttribute("nameValue", name);
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        }

        UserEntity u = new UserEntity(email, name, password);
        DataStore.USERS.put(email, u);

        // create session right after registering
        HttpSession session = req.getSession(true);
        session.setAttribute("user", u);

        resp.sendRedirect(req.getContextPath() + "/Birthday");
    }

    private String p(HttpServletRequest req, String key) {
        String v = req.getParameter(key);
        return v == null ? "" : v.trim();
    }
}
