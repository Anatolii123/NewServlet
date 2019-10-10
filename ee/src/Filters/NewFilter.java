package Filters;

import servlets.Controller;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class NewFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String login = req.getParameter("TEXT_3");
        String password = req.getParameter("TEXT_4");
        servletResponse.setCharacterEncoding("utf-8");
        servletRequest.setCharacterEncoding("utf-8");
        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();

            if (req.getSession().getAttribute("user") != null) {
                Controller.setRequestAttributes(req);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
                requestDispatcher.forward(req,resp);
                return;
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + login +"'" +
                    " AND PASSWORD = " +
                    "'" + password +"'" +
                    "").next()) {
                Controller.setRequestAttributes(req);
                filterChain.doFilter(req,resp);
                return;
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(req,resp);
                return;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
