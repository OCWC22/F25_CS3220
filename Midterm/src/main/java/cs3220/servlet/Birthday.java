package cs3220.servlet;

import cs3220.model.BirthdayEntity;
import cs3220.model.DataStore;
import cs3220.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/Birthday")
public class Birthday extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity u = currentUser(req);
        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/Login");
            return;
        }
        List<BirthdayEntity> list = DataStore.getListFor(u.getEmail());
        DataStore.sortListByMonthDay(list);
        req.setAttribute("user", u);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/birthday.jsp").forward(req, resp);
    }

    private UserEntity currentUser(HttpServletRequest req) {
        HttpSession s = req.getSession(false);
        return s == null ? null : (UserEntity) s.getAttribute("user");
    }
}
