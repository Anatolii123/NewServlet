package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();
            if (!statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + req.getParameter("TEXT_3") +"'" +
                    " AND PASSWORD = " +
                    "'" + req.getParameter("TEXT_4") +"'" +
                    "").next() &&
                    req.getParameter("TEXT_1") == null &&
                    req.getParameter("TEXT_2") == null &&
                    req.getParameter("TEXT_6") == null &&
                    req.getParameter("TEXT_7") == null &&
                    req.getParameter("TEXT_8") == null &&
                    req.getParameter("TEXT_9") == null) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(req,resp);
            }
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + req.getParameter("TEXT_3") +"'" +
                    " AND PASSWORD = " +
                    "'" + req.getParameter("TEXT_4") +"'" +
                    "");
            while (resultSet.next()) {
                req.setAttribute("name", resultSet.getString("NAME"));
                req.setAttribute("surname", resultSet.getString("SURNAME"));
                req.setAttribute("email", resultSet.getString("EMAIL"));
                req.setAttribute("dateOfBirth", resultSet.getString("DATE_OF_BIRTH"));
                req.setAttribute("gender", resultSet.getString("GENDER"));
                req.setAttribute("bug", resultSet.getString("BUG"));
                req.setAttribute("comments", (resultSet.getString("COMMENTS") == null)? "не задано":resultSet.getString("COMMENTS"));
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
        requestDispatcher.forward(req,resp);
    }
}
