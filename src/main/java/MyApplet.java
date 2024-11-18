import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet(urlPatterns={"/patients","/doctors"},loadOnStartup = 1)
public class MyApplet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String dbUrl = "jdbc:postgresql://mmholl:mmholl@172.22.141.28:5432/mmholl_db";
        Connection conn=null;
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
            resp.getWriter().write("Driver registered");
            conn= DriverManager.getConnection(dbUrl);
            Statement s=conn.createStatement();
            String sqlStr = "create table patients (\n" +
                    "    id SERIAL PRIMARY KEY,\n" +
                    "    familyname varchar(128) NOT NULL,\n" +
                    "    givenname varchar(128) NOT NULL,\n" +
                    "    phonenumber varchar(32)\n" +
                    ");\n";
            s.execute(sqlStr);

            sqlStr = "insert into patients (familyname,givenname,phonenumber) values('Jones','Bill','07755678899');";
            s.execute(sqlStr);

            s.close();
            conn.close();

        } catch (Exception e) {
            resp.getWriter().write("Exception"+e.getMessage());
        }


        resp.getWriter().write("Hello world");
    }
}
