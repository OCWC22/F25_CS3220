package cs3220.servlet;

import cs3220.model.GuestBookEntry;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/add")
public class AddComment extends HttpServlet {
    
    @SuppressWarnings("unchecked")
    private List<GuestBookEntry> entries() {
        return (List<GuestBookEntry>) getServletContext().getAttribute("entries");
    }

    private int nextId() {
        ServletContext ctx = getServletContext();
        int id = (Integer) ctx.getAttribute("nextId");
        ctx.setAttribute("nextId", id + 1);
        return id;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Check authentication
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!doctype html><html><head><title>Add Comment</title></head><body>");
        out.println("<h1>Add Comment</h1>");
        out.println("<form method='post' action='add'>");
        out.println("<p>Name: <input name='name' size='30' required></p>");
        out.println("<p><textarea name='message' rows='8' cols='60' required></textarea></p>");
        out.println("<p><button type='submit'>Add Comment</button></p>");
        out.println("</form>");
        out.printf("<p><a href='%s/guestbook'>Back to Guest Book</a></p>", req.getContextPath());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Check authentication
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/Login");
            return;
        }

        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String message = req.getParameter("message");
        entries().add(new GuestBookEntry(nextId(), name, message));
        resp.sendRedirect(req.getContextPath() + "/guestbook");
    }
}
