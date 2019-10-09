package servlets;

import javax.servlet.Filter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String login = req.getParameter("TEXT_3");
        String password = req.getParameter("TEXT_4");
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
                user.setName(resultSet.getString("NAME"));
                user.setSurName(resultSet.getString("SURNAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setDateOfBirth(resultSet.getString("DATE_OF_BIRTH"));
                user.setGender(resultSet.getString("GENDER"));
                user.setBug(resultSet.getString("BUG"));
                user.setComments((resultSet.getString("COMMENTS") == null)? "не задано":resultSet.getString("COMMENTS"));
//                req.getSession().setAttribute("user",user);
//                req.setAttribute("name", resultSet.getString("NAME"));
//                req.setAttribute("surname", resultSet.getString("SURNAME"));
//                req.setAttribute("email", resultSet.getString("EMAIL"));
//                req.setAttribute("password", resultSet.getString("PASSWORD"));
//                req.setAttribute("dateOfBirth", resultSet.getString("DATE_OF_BIRTH"));
//                req.setAttribute("gender", resultSet.getString("GENDER"));
//                req.setAttribute("bug", resultSet.getString("BUG"));
//                req.setAttribute("comments", (resultSet.getString("COMMENTS") == null)? "не задано":resultSet.getString("COMMENTS"));
//                req.getSession().setAttribute("TEXT_3",null);
//                req.getSession().setAttribute("TEXT_4",null);
//                req.getSession().setAttribute("TEXT_3",resultSet.getString("EMAIL"));
//                req.getSession().setAttribute("TEXT_4",resultSet.getString("PASSWORD"));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (req.getSession().getAttribute("user") != null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(req,resp);
            return;
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Signup.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
