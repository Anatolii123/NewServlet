package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";
        User user = new User();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();
            if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + request.getParameter("TEXT_3") + "'" +
                    "").next() &&
                statement.executeQuery("SELECT * FROM PEOPLE WHERE PASSWORD = " +
                    "'" + request.getParameter("TEXT_4") + "'" +
                    "").next()) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller");
                requestDispatcher.forward(request,response);
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + request.getParameter("TEXT_3") + "'" +
                    "").next() && request.getParameter("TEXT_4").equals("")) {
                request.setAttribute("email",request.getParameter("TEXT_3"));
                request.setAttribute("loginError","Введите пароль.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signin.jsp");
                requestDispatcher.forward(request,response);
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + request.getParameter("TEXT_3") + "'" +
                    "").next() &&
                    !statement.executeQuery("SELECT * FROM PEOPLE WHERE PASSWORD = " +
                            "'" + request.getParameter("TEXT_4") + "'" +
                            "").next()) {
                request.setAttribute("email",request.getParameter("TEXT_3"));
                request.setAttribute("loginError","Пароль введён неверно! Попробуйте ещё раз.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signin.jsp");
                requestDispatcher.forward(request,response);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signup.jsp");
            requestDispatcher.forward(request,response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
        requestDispatcher.forward(req,resp);
    }
}
