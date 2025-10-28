package cs3220.servlet;

import cs3220.model.BirthdayEntity;
import cs3220.model.DataStore;
import cs3220.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddBirthday")
public class AddBirthday extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (currentUser(req) == null) { resp.sendRedirect(req.getContextPath()+"/Login"); return; }
        req.getRequestDispatcher("/WEB-INF/views/add-birthday.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity u = currentUser(req);
        if (u == null) { resp.sendRedirect(req.getContextPath()+"/Login"); return; }

        String name = p(req,"name");
        int month = toInt(p(req,"month"));
        int day = toInt(p(req,"day"));

        if (name.isEmpty()) {
            req.setAttribute("error", "Please fill out this field.");
            req.setAttribute("nameValue", name);
            req.setAttribute("monthValue", month);
            req.setAttribute("dayValue", day);
            req.getRequestDispatcher("/WEB-INF/views/add-birthday.jsp").forward(req, resp);
            return;
        }

        List<BirthdayEntity> list = DataStore.getListFor(u.getEmail());
        int id = DataStore.ID_GEN.getAndIncrement();
        list.add(new BirthdayEntity(id, name, month, day));
        resp.sendRedirect(req.getContextPath() + "/Birthday");
    }

    private String p(HttpServletRequest req, String k) { String v=req.getParameter(k); return v==null?"":v.trim(); }
    private int toInt(String s){ try { return Integer.parseInt(s); } catch(Exception e){ return 1; } }
    private UserEntity currentUser(HttpServletRequest req){ HttpSession s=req.getSession(false); return s==null?null:(UserEntity)s.getAttribute("user");}
}
