package Filters;

import servlets.Controller;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class NewFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletResponse.setCharacterEncoding("utf-8");
        servletRequest.setCharacterEncoding("utf-8");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String login = req.getParameter("TEXT_3");
        String password = req.getParameter("TEXT_4");
        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();

            if (req.getSession().getAttribute("user") != null && !path.startsWith("/LogOut")) {
                Controller.setRequestAttributes(req);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
                requestDispatcher.forward(req,resp);
                return;
            }  else if (path.startsWith("/LogOut")) {
                req.getSession().setAttribute("user",null);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Signin.jsp");
                requestDispatcher.forward(req,resp);
                return;
            } else if (path.startsWith("/AddToDB")) {
                req.getSession().setAttribute("name", req.getParameter("TEXT_1"));
                req.getSession().setAttribute("surname", req.getParameter("TEXT_2"));
                req.getSession().setAttribute("email", req.getParameter("TEXT_3"));
                req.getSession().setAttribute("password", req.getParameter("TEXT_4"));
                req.getSession().setAttribute("dateOfBirth", req.getParameter("TEXT_6"));
                req.getSession().setAttribute("gender", req.getParameter("TEXT_7"));
                req.getSession().setAttribute("bug", req.getParameter("TEXT_8"));
                req.getSession().setAttribute("comments", (req.getParameter("TEXT_9") == "")? "не задано":req.getParameter("TEXT_9"));
                Controller.setRequestAttributes(req);
                filterChain.doFilter(req,resp);
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
