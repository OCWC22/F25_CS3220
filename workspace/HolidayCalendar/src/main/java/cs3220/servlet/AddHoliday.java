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
 * Servlet handling the addition of new holidays.
 * Displays form with dropdown date selectors and handles submission.
 */
@WebServlet("/add")
public class AddHoliday extends HttpServlet {

    @SuppressWarnings("unchecked")
    private List<HolidayEntry> entries() {
        return (List<HolidayEntry>) getServletContext().getAttribute("entries");
    }

    private int nextId() {
        ServletContext ctx = getServletContext();
        int id = (Integer) ctx.getAttribute("nextId");
        ctx.setAttribute("nextId", id + 1);
        return id;
    }

    private void sortEntries(List<HolidayEntry> entries) {
        entries.sort(Comparator.comparing(HolidayEntry::getHolidayDate));
    }

    private boolean existsOnDate(List<HolidayEntry> entries, LocalDate date) {
        return entries.stream().anyMatch(entry -> entry.getHolidayDate().equals(date));
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
        out.println("  <title>Add Holiday</title>");
        out.println("  <link rel='stylesheet' href='" + contextPath + "/styles/holiday.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("  <div class='container'>");
        out.println("    <h1>Add Holiday</h1>");
        
        out.println("    <form method='post' action='" + contextPath + "/add'>");
        out.println("      <div class='form-group'>");
        out.println("        <label>Holiday Date:</label>");
        
        // Day dropdown (1-31)
        out.println("        <select name='day' required>");
        for (int i = 1; i <= 31; i++) {
            out.println("          <option value='" + i + "'>" + i + "</option>");
        }
        out.println("        </select>");
        
        // Month dropdown
        out.println("        <select name='month' required>");
        String[] months = {"January", "February", "March", "April", "May", "June", 
                          "July", "August", "September", "October", "November", "December"};
        for (int i = 0; i < months.length; i++) {
            out.println("          <option value='" + (i + 1) + "'>" + months[i] + "</option>");
        }
        out.println("        </select>");
        
        // Year dropdown (2024-2030)
        out.println("        <select name='year' required>");
        for (int i = 2024; i <= 2030; i++) {
            out.println("          <option value='" + i + "'>" + i + "</option>");
        }
        out.println("        </select>");
        
        out.println("      </div>");
        
        out.println("      <div class='form-group'>");
        out.println("        <label>Holiday Name:</label>");
        out.println("        <input type='text' name='holiday' required>");
        out.println("      </div>");
        
        out.println("      <button type='submit' class='add-button'>Add Holiday</button>");
        out.println("    </form>");
        
        out.println("  </div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        
        try {
            int day = Integer.parseInt(req.getParameter("day"));
            int month = Integer.parseInt(req.getParameter("month"));
            int year = Integer.parseInt(req.getParameter("year"));
            String holiday = req.getParameter("holiday");
            
            LocalDate date = LocalDate.of(year, month, day);
            
            List<HolidayEntry> entries = entries();
            
            // Check if date already exists - if so, ignore and redirect
            if (existsOnDate(entries, date)) {
                resp.sendRedirect(req.getContextPath() + "/holidays");
                return;
            }
            
            // Add new holiday
            HolidayEntry newEntry = new HolidayEntry(nextId(), date, holiday);
            entries.add(newEntry);
            
            // Sort by date
            sortEntries(entries);
            
            resp.sendRedirect(req.getContextPath() + "/holidays");
            
        } catch (Exception e) {
            // Invalid date or parameters - redirect back
            resp.sendRedirect(req.getContextPath() + "/holidays");
        }
    }
}
