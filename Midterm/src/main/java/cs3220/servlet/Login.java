package cs3220.servlet;

import cs3220.model.DataStore;
import cs3220.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = param(req, "email").toLowerCase();
        String password = param(req, "password");

        UserEntity u = DataStore.USERS.get(email);
        if (u != null && u.getPassword().equals(password)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", u);
            resp.sendRedirect(req.getContextPath() + "/Birthday");
            return;
        }

        req.setAttribute("error", "Email and Password does not match");
        req.setAttribute("emailValue", email);
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    private String param(HttpServletRequest req, String name) {
        String v = req.getParameter(name);
        return v == null ? "" : v.trim();
    }
}
