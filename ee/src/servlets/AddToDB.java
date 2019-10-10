package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/AddToDB")
public class AddToDB extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();
            if (request.getParameter("TEXT_4").equals(request.getParameter("TEXT_5")) &&
                    statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                            "'" + request.getParameter("TEXT_3") + "'" +
                            "").next()) {
                statement.executeUpdate("INSERT INTO PEOPLE VALUES (" +
                        "PEOPLE_ID_SEQUENCE.NEXTVAL, " +
                        "'" + request.getParameter("TEXT_1") +"', " +
                        "'" + request.getParameter("TEXT_2") +"', " +
                        "'" + request.getParameter("TEXT_3") +"', " +
                        "'" + request.getParameter("TEXT_4") +"', " +
                        "'" + request.getParameter("TEXT_6") +"', " +
                        "'" + request.getParameter("TEXT_7") +"', " +
                        "'" + request.getParameter("TEXT_8") +"', " +
                        "'" + request.getParameter("TEXT_9") +"')");
                request.setAttribute("registration","Вы успешно зарегистрированы!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller");
                requestDispatcher.forward(request,response);
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + request.getParameter("TEXT_3") + "'" +
                    "").next() &&
                    !statement.executeQuery("SELECT * FROM PEOPLE WHERE PASSWORD = " +
                            "'" + request.getParameter("TEXT_4") + "'" +
                            "").next()) {
                request.setAttribute("passwordError","Пользователь с таким аккаунтом уже существует! Попробуйте ещё раз.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(request,response);
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + request.getParameter("TEXT_3") + "'" +
                    "").next() &&
                    statement.executeQuery("SELECT * FROM PEOPLE WHERE PASSWORD = " +
                            "'" + request.getParameter("TEXT_4") + "'" +
                            "").next()) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller");
                requestDispatcher.forward(request,response);
            }
            request.setAttribute("passwordError","Копия пароля введена неверно! Попробуйте ещё раз.");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signup.jsp");
            requestDispatcher.forward(request,response);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
