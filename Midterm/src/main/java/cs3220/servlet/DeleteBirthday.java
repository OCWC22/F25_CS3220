package cs3220.servlet;

import cs3220.model.BirthdayEntity;
import cs3220.model.DataStore;
import cs3220.model.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/DeleteBirthday")
public class DeleteBirthday extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserEntity u = currentUser(req);
        if (u == null) { resp.sendRedirect(req.getContextPath()+"/Login"); return; }

        int id = id(req);
        List<BirthdayEntity> list = DataStore.getListFor(u.getEmail());
        for (Iterator<BirthdayEntity> it = list.iterator(); it.hasNext();) {
            if (it.next().getId() == id) { it.remove(); break; }
        }
        resp.sendRedirect(req.getContextPath()+"/Birthday");
    }

    private int id(HttpServletRequest req){ try { return Integer.parseInt(req.getParameter("id")); } catch(Exception e){ return -1; } }
    private UserEntity currentUser(HttpServletRequest req){ HttpSession s=req.getSession(false); return s==null?null:(UserEntity)s.getAttribute("user"); }
}
