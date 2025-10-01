package cs3220.servlet;

import cs3220.model.HolidayEntry;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

/**
 * Servlet handling the deletion of holidays.
 * Removes the specified holiday and redirects to the main page.
 */
@WebServlet("/delete")
public class DeleteHoliday extends HttpServlet {

    @SuppressWarnings("unchecked")
    private List<HolidayEntry> entries() {
        return (List<HolidayEntry>) getServletContext().getAttribute("entries");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            
            List<HolidayEntry> entries = entries();
            Iterator<HolidayEntry> iterator = entries.iterator();
            
            while (iterator.hasNext()) {
                HolidayEntry entry = iterator.next();
                if (entry.getId() == id) {
                    iterator.remove();
                    break;
                }
            }
            
        } catch (Exception e) {
            // Invalid ID or other error - just redirect
        }
        
        resp.sendRedirect(req.getContextPath() + "/holidays");
    }
}
