package cs3220.servlet;

import cs3220.model.HolidayEntry;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Main servlet displaying the holiday calendar list.
 * Initializes seed data and renders the holiday table.
 */
@WebServlet("/holidays")
public class HolidayCalendar extends HttpServlet {

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        if (ctx.getAttribute("entries") == null) {
            List<HolidayEntry> entries = new ArrayList<>();
            
            // Seed initial holidays as specified
            entries.add(new HolidayEntry("2024-01-01", "New Year's Day"));
            entries.add(new HolidayEntry("2024-01-15", "Martin Luther King Jr. Day"));
            entries.add(new HolidayEntry("2024-02-12", "Lincoln's Birthday"));
            entries.add(new HolidayEntry("2024-02-19", "Presidents' Day"));
            entries.add(new HolidayEntry("2024-07-04", "Independence Day"));
            entries.add(new HolidayEntry("2024-09-02", "Labor Day"));
            entries.add(new HolidayEntry("2024-11-28", "Thanksgiving Day"));
            entries.add(new HolidayEntry("2024-12-25", "Christmas Day"));
            
            // Assign IDs
            for (int i = 0; i < entries.size(); i++) {
                entries.set(i, new HolidayEntry(i + 1, entries.get(i).getHolidayDate(), entries.get(i).getHoliday()));
            }
            
            // Sort by date
            sortEntries(entries);
            
            ctx.setAttribute("entries", entries);
            ctx.setAttribute("nextId", entries.size() + 1);
        }
    }

    @SuppressWarnings("unchecked")
    private List<HolidayEntry> entries() {
        return (List<HolidayEntry>) getServletContext().getAttribute("entries");
    }

    private void sortEntries(List<HolidayEntry> entries) {
        entries.sort(Comparator.comparing(HolidayEntry::getHolidayDate));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String contextPath = req.getContextPath();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("  <meta charset='UTF-8'>");
        out.println("  <title>US Holidays</title>");
        out.println("  <link rel='stylesheet' href='" + contextPath + "/styles/holiday.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <div class='container'>");
        out.println("    <h1>US Holidays</h1>");
        
        // Holiday table
        out.println("    <table>");
        out.println("      <thead>");
        out.println("        <tr>");
        out.println("          <th>Date</th>");
        out.println("          <th>Holiday</th>");
        out.println("          <th>Update / Delete</th>");
        out.println("        </tr>");
        out.println("      </thead>");
        out.println("      <tbody>");

        List<HolidayEntry> entries = entries();
        sortEntries(entries); // Ensure sorted display
        
        for (HolidayEntry entry : entries) {
            out.println("        <tr>");
            out.println("          <td>" + escape(entry.getFormattedDate()) + "</td>");
            out.println("          <td>" + escape(entry.getHoliday()) + "</td>");
            out.println("          <td>");
            out.println("            <a href='" + contextPath + "/update?id=" + entry.getId() + "'>Update</a> | ");
            out.println("            <a href='" + contextPath + "/delete?id=" + entry.getId() + "'>Delete</a>");
            out.println("          </td>");
            out.println("        </tr>");
        }

        out.println("      </tbody>");
        out.println("    </table>");
        
        // Add Holiday button
        out.println("    <form action='" + contextPath + "/add' method='get'>");
        out.println("      <button type='submit' class='add-button'>Add Holiday</button>");
        out.println("    </form>");
        
        out.println("  </div>");
        out.println("</body>");
        out.println("</html>");
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
