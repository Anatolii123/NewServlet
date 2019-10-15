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

    public static void setRequestAttributesFromDB (HttpServletRequest req, ResultSet resultSet) throws SQLException {
        req.getSession().setAttribute("name", resultSet.getString("NAME"));
        req.getSession().setAttribute("surname", resultSet.getString("SURNAME"));
        req.getSession().setAttribute("email", resultSet.getString("EMAIL"));
        req.getSession().setAttribute("password", resultSet.getString("PASSWORD"));
        req.getSession().setAttribute("dateOfBirth", resultSet.getString("DATE_OF_BIRTH"));
        req.getSession().setAttribute("gender", resultSet.getString("GENDER"));
        req.getSession().setAttribute("bug", resultSet.getString("BUG"));
        req.getSession().setAttribute("comments", (resultSet.getString("COMMENTS") == null)? "не задано":resultSet.getString("COMMENTS"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String login = req.getParameter("EMAIL");
        String password = req.getParameter("PASSWORD");

        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";
        User user = new User();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PEOPLE WHERE EMAIL = ? AND PASSWORD = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                req.getSession().setAttribute("user",user);
                setRequestAttributesFromDB(req,resultSet);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (req.getSession().getAttribute("user") != null || path.startsWith("/AddToDB")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(req,resp);
            return;
        } else if (path.startsWith("/LogOut")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Signin.jsp");
            requestDispatcher.forward(req,resp);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Signup.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
