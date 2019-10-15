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
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/AddToDB")
public class AddToDB extends HttpServlet {

    public static void setRequestAttributesFromForm(HttpServletRequest request) {
        request.getSession().setAttribute("name", request.getParameter("NAME"));
        request.getSession().setAttribute("surname", request.getParameter("SURNAME"));
        request.getSession().setAttribute("email", request.getParameter("EMAIL"));
        request.getSession().setAttribute("password", request.getParameter("PASSWORD"));
        request.getSession().setAttribute("copypassword", request.getParameter("COPY_PASSWORD"));
        request.getSession().setAttribute("dateOfBirth", request.getParameter("DATE_OF_BIRTH"));
        request.getSession().setAttribute("gender", request.getParameter("GENDER"));
        request.getSession().setAttribute("bug", request.getParameter("BUG"));
        request.getSession().setAttribute("comments", (request.getParameter("COMMENTS") == "")? "не задано":request.getParameter("COMMENTS"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();
            setRequestAttributesFromForm(request);
            if (request.getParameter("NAME").equals("") ||
                request.getParameter("SURNAME").equals("") ||
                request.getParameter("EMAIL").equals("") ||
                request.getParameter("PASSWORD").equals("") ||
                request.getParameter("COPY_PASSWORD").equals("") ||
                request.getParameter("DATE_OF_BIRTH").equals("")) {
                request.setAttribute("Error","Не все поля заполнены! Заполните пустые поля.");
                request.getSession().setAttribute("Error","Не все поля заполнены! Заполните пустые поля.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(request,response);
            } else if (!request.getParameter("PASSWORD").equals(request.getParameter("COPY_PASSWORD"))) {
                request.getSession().setAttribute("Error","Копия пароля введена неверно! Попробуйте ещё раз.");
                request.getSession().setAttribute("password","");
                request.getSession().setAttribute("copypassword","");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(request,response);
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + request.getParameter("EMAIL") + "'" +
                    "").next() &&
                    !statement.executeQuery("SELECT * FROM PEOPLE WHERE PASSWORD = " +
                            "'" + request.getParameter("PASSWORD") + "'" +
                            "").next()) {
                request.getSession().setAttribute("Error","Пользователь с таким аккаунтом уже существует! Попробуйте ещё раз.");
                request.getSession().setAttribute("email","");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(request,response);
            } else if(statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + request.getParameter("EMAIL") + "'" +
                    "").next() &&
                    statement.executeQuery("SELECT * FROM PEOPLE WHERE PASSWORD = " +
                            "'" + request.getParameter("PASSWORD") + "'" +
                            "").next()) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller");
                requestDispatcher.forward(request,response);
            } else if (request.getParameter("PASSWORD").equals(request.getParameter("COPY_PASSWORD")) &&
                    !statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                            "'" + request.getParameter("EMAIL") + "'" +
                            "").next()) {
                statement.executeUpdate("INSERT INTO PEOPLE VALUES (" +
                        "PEOPLE_ID_SEQUENCE.NEXTVAL, " +
                        "'" + request.getParameter("NAME") +"', " +
                        "'" + request.getParameter("SURNAME") +"', " +
                        "'" + request.getParameter("EMAIL") +"', " +
                        "'" + request.getParameter("PASSWORD") +"', " +
                        "'" + request.getParameter("DATE_OF_BIRTH") +"', " +
                        "'" + request.getParameter("GENDER") +"', " +
                        "'" + request.getParameter("BUG") +"', " +
                        "'" + request.getParameter("COMMENTS") +"')");
                request.getSession().setAttribute("registration","Вы успешно зарегистрированы!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller");
                requestDispatcher.forward(request,response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
