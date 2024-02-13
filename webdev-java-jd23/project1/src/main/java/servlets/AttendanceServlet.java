package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet(urlPatterns = "/attendance")
public class AttendanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        out.println("<html><h1 style='color: ae8e42;'>GRIT</h1><h1>ACADEMY</h1></html><br>" +
                "<body>" +
                "<h2>Attendance:</h2>" +
                "<table>" +
                "<tr bgcolor=ae8e42>" +
                "<th style='color: white;'>Student</th><th style='color: white;'>Course</th>" +
                "</tr>");
        try {
            Statement stmt = connect().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT s.fname, c.name FROM attendance a " +
                                                    "JOIN students s ON a.students_id = s.student_id " +
                                                        "JOIN courses c ON a.subject_id = c.course_id");
            while (rs.next()) {
                out.println("<tr bgcolor= d4d4d2>" +
                        "<td>" + rs.getString("fname") + "</td>" +
                        "<td>" + rs.getString("name") + "</td>");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        out.println("</table>" +
                "<br><a href=/students> Se elever på skolan! </a><br>" +
                "<br><a href=courses> Se kurser på skolan! </a></body>");
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