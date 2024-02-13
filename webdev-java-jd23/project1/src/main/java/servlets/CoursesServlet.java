package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet(urlPatterns = "/courses")
public class CoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        out.println("<html><h1 style='color: ae8e42;'>GRIT</h1><h1>ACADEMY</h1></html><br>" +
                "<body>" +
                "<h2>Kurser:</h2>" +
                "<table>" +
                "<tr bgcolor=ae8e42>" +
                "<th style='color: white;'>Namn</th><th style='color: white;'>YHP</th><th style='color: white;'>Beskrivning</th>" +
                "</tr>");
        try {
            Statement stmt = connect().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Courses");
            while (rs.next()) {
                out.println("<tr bgcolor= d4d4d2>" +
                        "<td>" + rs.getString(2) + "</td>" +
                        "<td>" + rs.getString(3) + "</td>" +
                        "<td>" + rs.getString(4) + "</td>" +
                        "</tr>");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        out.println("</table>" +
                "<br><a href=/students> Se elever på skolan! </a><br>" +
                "<br><a href=attendance> Se vem som tar vilka kurser! </a></body>");
        //getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
        System.out.println("GET Request");
    }
    public Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:4306/gritacademy", "user", "user");
        } catch (Exception e) {
            System.out.println("FEL: " + e);
            return null;
        }
    }
}