package cs3220.servlet;

import cs3220.model.GuestBookEntry;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/guestbook")
public class GuestBook extends HttpServlet {
    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        if (ctx.getAttribute("entries") == null) {
            List<GuestBookEntry> seed = new ArrayList<>();
            seed.add(new GuestBookEntry(1, "John", "Hello!"));
            seed.add(new GuestBookEntry(2, "Jane", "Hello Again!"));
            ctx.setAttribute("entries", seed);
            ctx.setAttribute("nextId", 3);
        }
    }

    @SuppressWarnings("unchecked")
    private List<GuestBookEntry> entries() {
        return (List<GuestBookEntry>) getServletContext().getAttribute("entries");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("  <title>Guest Book</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <h1>Guest Book</h1>");

        out.println("  <table border='1' cellspacing='0' cellpadding='4'>");
        out.println("    <tr>");
        out.println("      <th>Name</th>");
        out.println("      <th>Message</th>");
        out.println("      <th>Edit | Delete</th>");
        out.println("    </tr>");

        String context = req.getContextPath();
        for (GuestBookEntry e : entries()) {
            int id = e.getId();
            out.printf(
                "    <tr><td>%s</td><td>%s</td><td><a href='%s/edit?id=%d'>Edit</a> | <a href='%s/delete?id=%d'>Delete</a></td></tr>",
                escape(e.getName()),
                escape(e.getMessage()),
                context,
                id,
                context,
                id
            );
        }

        out.println("  </table>");
        out.printf("  <p><a href='%s/add'>Add Comment</a></p>", context);

        out.println("</body>");
        out.println("</html>");
    }

    private String escape(String s) {
        return s == null ? "" : s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
    }
}