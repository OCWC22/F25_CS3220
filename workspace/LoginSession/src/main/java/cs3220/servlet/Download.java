package cs3220.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet(name = "Download", urlPatterns = "/download")
public class Download extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String file = request.getParameter("file");
        if (file == null || file.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ServletContext ctx = getServletContext();
        String path = "/images/" + file;
        InputStream in = ctx.getResourceAsStream(path);
        if (in == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String mime = ctx.getMimeType(path);
        if (mime == null) mime = "application/octet-stream";
        response.setContentType(mime);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");

        try (OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } finally {
            in.close();
        }
    }
}
