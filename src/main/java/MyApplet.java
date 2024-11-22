import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(urlPatterns={"/patients","/doctors"},loadOnStartup = 1)
public class MyApplet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //String dbUrl = "jdbc:postgresql://172.22.141.28:5432/postgres";
        String dbUrl = "jdbc:postgresql://"+System.getenv("PGHOST")+":"+System.getenv("PGPORT")+"/"+System.getenv("PGDATABASE");
        Connection conn=null;
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");


            conn= DriverManager.getConnection(dbUrl,System.getenv("PGUSER"),System.getenv("PGPASSWORD"));
            Statement s=conn.createStatement();
            String sqlStr ="select * from patients where true";

             /*       String sqlStr = "create table patients (\n" +
                    "    id SERIAL PRIMARY KEY,\n" +
                    "    familyname varchar(128) NOT NULL,\n" +
                    "    givenname varchar(128) NOT NULL,\n" +
                    "    phonenumber varchar(32)\n" +
                    ");\n";
            s.execute(sqlStr);

            sqlStr = "insert into patients (familyname,givenname,phonenumber) values('Smith','Gina','0745633899');";
            s.execute(sqlStr);
            sqlStr = "insert into patients (familyname,givenname,phonenumber) values('Holloway','Nettles','0778443899');";
            s.execute(sqlStr);
            sqlStr = "insert into patients (familyname,givenname,phonenumber) values('Holloway','Martin','0749722099');";
            s.execute(sqlStr);
            sqlStr = "insert into patients (familyname,givenname,phonenumber) values('Fellow','Johnny','0745622453');";
*/
            ResultSet rs=s.executeQuery(sqlStr);
            s.close();
            conn.close();
            ArrayList<Patient> pats=new ArrayList<>();
            Gson gson=new Gson();
            while(rs.next()){
                Patient p=new Patient(rs.getString("givenname")+" "+rs.getString("familyname"),rs.getString("phonenumber"));
                pats.add(p);
            }

            String jsonString = gson.toJson(pats);
            resp.getWriter().write(jsonString);

        } catch (Exception e) {
            resp.setStatus(502);
            resp.getWriter().write("Exception"+e.getMessage());
        }



    }
}
