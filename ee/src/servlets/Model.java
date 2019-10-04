package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/Model")
public class Model extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO PEOPLE VALUES (" +
                    "PEOPLE_ID_SEQUENCE.NEXTVAL, " +
                    "'" + req.getParameter("TEXT_1") +"', " +
                    "'" + req.getParameter("TEXT_2") +"', " +
                    "'" + req.getParameter("TEXT_3") +"', " +
                    "'" + req.getParameter("TEXT_4") +"', " +
                    "'" + req.getParameter("TEXT_6") +"', " +
                    "'" + req.getParameter("TEXT_5") +"')");

        } catch (Exception e) {
            System.out.println(e);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
        requestDispatcher.forward(req,resp);
    }
}
