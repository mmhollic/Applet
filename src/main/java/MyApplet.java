import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/patients","/doctors"},loadOnStartup = 1)
public class MyApplet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String dbUrl = "jdbc:postgresql://mmholl:mmholl@172.22.141.28:5432/mmholl_db";

        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
            resp.getWriter().write("Driver registered");
        } catch (Exception e) {
            resp.getWriter().write("Exception"+e.getMessage());
        }

        resp.getWriter().write("Hello world");
    }
}
