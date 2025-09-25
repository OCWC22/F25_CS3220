package cs3220.servlet;

import cs3220.model.GuestBookEntry;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/delete")
public class DeleteEntry extends HttpServlet {
    @SuppressWarnings("unchecked")
    private List<GuestBookEntry> entries() {
        return (List<GuestBookEntry>) getServletContext().getAttribute("entries");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Iterator<GuestBookEntry> it = entries().iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/guestbook");
    }
}