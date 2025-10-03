package cs3220.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple HelloServlet for testing purposes.
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Hello Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Hello from SessionHandler!</h1>");
        out.println("<p>This is a simple test servlet.</p>");
        out.printf("<p><a href='%s/Login'>Go to Login</a></p>", req.getContextPath());
        out.println("</body>");
        out.println("</html>");
    }
}
