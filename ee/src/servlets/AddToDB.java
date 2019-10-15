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
            request.setAttribute("name", request.getParameter("TEXT_1"));
            request.setAttribute("surname", request.getParameter("TEXT_2"));
            request.setAttribute("email", request.getParameter("TEXT_3"));
            request.setAttribute("password", request.getParameter("TEXT_4"));
            request.setAttribute("copypassword", request.getParameter("TEXT_5"));
            request.setAttribute("dateOfBirth", request.getParameter("TEXT_6"));
            request.setAttribute("gender", request.getParameter("TEXT_7"));
            request.setAttribute("bug", request.getParameter("TEXT_8"));
            request.setAttribute("comments", (request.getParameter("TEXT_9") == "")? "не задано":request.getParameter("TEXT_9"));
            if (request.getParameter("TEXT_1").equals("") ||
                request.getParameter("TEXT_2").equals("") ||
                request.getParameter("TEXT_3").equals("") ||
                request.getParameter("TEXT_4").equals("") ||
                request.getParameter("TEXT_5").equals("") ||
                request.getParameter("TEXT_6").equals("")) {
                request.setAttribute("Error","Не все поля заполнены! Заполните пустые поля.");
                request.getSession().setAttribute("Error","Не все поля заполнены! Заполните пустые поля.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(request,response);
            } else if (!request.getParameter("TEXT_4").equals(request.getParameter("TEXT_5"))) {
                request.setAttribute("Error","Копия пароля введена неверно! Попробуйте ещё раз.");
                request.getSession().setAttribute("Error","Копия пароля введена неверно! Попробуйте ещё раз.");
                request.setAttribute("password","");
                request.setAttribute("copypassword","");
                request.getSession().setAttribute("password","");
                request.getSession().setAttribute("copypassword","");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(request,response);
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + request.getParameter("TEXT_3") + "'" +
                    "").next() &&
                    !statement.executeQuery("SELECT * FROM PEOPLE WHERE PASSWORD = " +
                            "'" + request.getParameter("TEXT_4") + "'" +
                            "").next()) {
                request.setAttribute("Error","Пользователь с таким аккаунтом уже существует! Попробуйте ещё раз.");
                request.setAttribute("email","");
                request.getSession().setAttribute("Error","Пользователь с таким аккаунтом уже существует! Попробуйте ещё раз.");
                request.getSession().setAttribute("email","");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(request,response);
            } else if(statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + request.getParameter("TEXT_3") + "'" +
                    "").next() &&
                    statement.executeQuery("SELECT * FROM PEOPLE WHERE PASSWORD = " +
                            "'" + request.getParameter("TEXT_4") + "'" +
                            "").next()) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller");
                requestDispatcher.forward(request,response);
            } else if (request.getParameter("TEXT_4").equals(request.getParameter("TEXT_5")) &&
                    !statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
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
                request.getSession().setAttribute("registration","Вы успешно зарегистрированы!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller");
                requestDispatcher.forward(request,response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
