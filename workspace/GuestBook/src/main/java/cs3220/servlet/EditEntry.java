package cs3220.servlet;

import cs3220.model.GuestBookEntry;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/edit")
public class EditEntry extends HttpServlet {
    @SuppressWarnings("unchecked")
    private List<GuestBookEntry> entries() {
        return (List<GuestBookEntry>) getServletContext().getAttribute("entries");
    }

    private GuestBookEntry find(int id) {
        for (GuestBookEntry e : entries()) if (e.getId() == id) return e;
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        GuestBookEntry e = find(id);
        if (e == null) {
            resp.sendRedirect(req.getContextPath() + "/guestbook");
            return;
        }
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!doctype html><html><head><title>Edit Entry</title></head><body>");
        out.println("<h1>Edit Entry</h1>");
        out.printf("<form method='post' action='edit?id=%d'>", e.getId());
        out.printf("Name: <input name='name' value='%s' required><br>", escape(e.getName()));
        out.printf("Message: <textarea name='message' required>%s</textarea><br>", escape(e.getMessage()));
        out.println("<button type='submit'>Update</button> ");
        out.println("<a href='guestbook'>Cancel</a>");
        out.println("</form></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        GuestBookEntry e = find(id);
        if (e != null) {
            e.setName(req.getParameter("name"));
            e.setMessage(req.getParameter("message"));
        }
        resp.sendRedirect(req.getContextPath() + "/guestbook");
    }

    private String escape(String s) {
        return s == null ? "" : s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
    }
}