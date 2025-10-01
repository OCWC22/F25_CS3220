package cs3220.servlet;

import cs3220.model.HolidayEntry;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

/**
 * Servlet handling the update of existing holidays.
 * Displays form pre-populated with current values and handles submission.
 */
@WebServlet("/update")
public class UpdateHoliday extends HttpServlet {

    @SuppressWarnings("unchecked")
    private List<HolidayEntry> entries() {
        return (List<HolidayEntry>) getServletContext().getAttribute("entries");
    }

    private HolidayEntry findById(int id) {
        for (HolidayEntry entry : entries()) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    private void sortEntries(List<HolidayEntry> entries) {
        entries.sort(Comparator.comparing(HolidayEntry::getHolidayDate));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            HolidayEntry entry = findById(id);
            
            if (entry == null) {
                resp.sendRedirect(req.getContextPath() + "/holidays");
                return;
            }

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            String contextPath = req.getContextPath();
            
            LocalDate date = entry.getHolidayDate();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("  <meta charset='UTF-8'>");
            out.println("  <title>Update Holiday</title>");
            out.println("  <link rel='stylesheet' href='" + contextPath + "/styles/holiday.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("  <div class='container'>");
            out.println("    <h1>Update Holiday</h1>");
            
            out.println("    <form method='post' action='" + contextPath + "/update?id=" + id + "'>");
            out.println("      <div class='form-group'>");
            out.println("        <label>Holiday Date:</label>");
            
            // Day dropdown (1-31) with current value selected
            out.println("        <select name='day' required>");
            for (int i = 1; i <= 31; i++) {
                String selected = (i == date.getDayOfMonth()) ? " selected" : "";
                out.println("          <option value='" + i + "'" + selected + ">" + i + "</option>");
            }
            out.println("        </select>");
            
            // Month dropdown with current value selected
            out.println("        <select name='month' required>");
            String[] months = {"January", "February", "March", "April", "May", "June", 
                              "July", "August", "September", "October", "November", "December"};
            for (int i = 0; i < months.length; i++) {
                String selected = ((i + 1) == date.getMonthValue()) ? " selected" : "";
                out.println("          <option value='" + (i + 1) + "'" + selected + ">" + months[i] + "</option>");
            }
            out.println("        </select>");
            
            // Year dropdown (2024-2030) with current value selected
            out.println("        <select name='year' required>");
            for (int i = 2024; i <= 2030; i++) {
                String selected = (i == date.getYear()) ? " selected" : "";
                out.println("          <option value='" + i + "'" + selected + ">" + i + "</option>");
            }
            out.println("        </select>");
            
            out.println("      </div>");
            
            out.println("      <div class='form-group'>");
            out.println("        <label>Holiday Name:</label>");
            out.println("        <input type='text' name='holiday' value='" + escape(entry.getHoliday()) + "' required>");
            out.println("      </div>");
            
            out.println("      <button type='submit' class='add-button'>Update Holiday</button>");
            out.println("    </form>");
            
            out.println("  </div>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/holidays");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int day = Integer.parseInt(req.getParameter("day"));
            int month = Integer.parseInt(req.getParameter("month"));
            int year = Integer.parseInt(req.getParameter("year"));
            String holiday = req.getParameter("holiday");
            
            LocalDate newDate = LocalDate.of(year, month, day);
            HolidayEntry entry = findById(id);
            
            if (entry != null) {
                // Update the entry
                entry.setHolidayDate(newDate);
                entry.setHoliday(holiday);
                
                // Sort by date
                sortEntries(entries());
            }
            
            resp.sendRedirect(req.getContextPath() + "/holidays");
            
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/holidays");
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
