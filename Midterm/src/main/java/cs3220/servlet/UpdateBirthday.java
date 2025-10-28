package cs3220.servlet;

import cs3220.model.BirthdayEntity;
import cs3220.model.DataStore;
import cs3220.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateBirthday")
public class UpdateBirthday extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity u = currentUser(req);
        if (u == null) { resp.sendRedirect(req.getContextPath()+"/Login"); return; }

        int id = intParam(req, "id");
        BirthdayEntity b = find(DataStore.getListFor(u.getEmail()), id);
        if (b == null) { resp.sendRedirect(req.getContextPath()+"/Birthday"); return; }

        req.setAttribute("b", b);
        req.getRequestDispatcher("/WEB-INF/views/update-birthday.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity u = currentUser(req);
        if (u == null) { resp.sendRedirect(req.getContextPath()+"/Login"); return; }

        int id = intParam(req, "id");
        List<BirthdayEntity> list = DataStore.getListFor(u.getEmail());
        BirthdayEntity b = find(list, id);
        if (b != null) {
            b.setFriendName(p(req,"name"));
            b.setMonth(intParam(req,"month"));
            b.setDay(intParam(req,"day"));
        }
        resp.sendRedirect(req.getContextPath()+"/Birthday");
    }

    private BirthdayEntity find(List<BirthdayEntity> list, int id){
        for (BirthdayEntity b : list) if (b.getId()==id) return b;
        return null;
    }
    private String p(HttpServletRequest req, String k){ String v=req.getParameter(k); return v==null?"":v.trim(); }
    private int intParam(HttpServletRequest req, String k){ try { return Integer.parseInt(req.getParameter(k)); } catch(Exception e){ return 1; } }
    private UserEntity currentUser(HttpServletRequest req){ HttpSession s=req.getSession(false); return s==null?null:(UserEntity)s.getAttribute("user"); }
}
