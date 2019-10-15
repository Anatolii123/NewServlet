package Filters;

import servlets.AddToDB;
import servlets.Controller;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


public class NewFilter implements Filter {
//todo удалять лишнее
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletResponse.setCharacterEncoding("utf-8");
        servletRequest.setCharacterEncoding("utf-8");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String login = req.getParameter("EMAIL");
        String password = req.getParameter("PASSWORD");
        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();

            if (req.getSession().getAttribute("user") != null && !path.startsWith("/LogOut")) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
                requestDispatcher.forward(req,resp);
                return;
            }  else if (path.startsWith("/LogOut")) {
                req.getSession().setAttribute("user",null);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Signin.jsp");
                requestDispatcher.forward(req,resp);
                return;
            } else if (path.startsWith("/AddToDB")) {
                AddToDB.setRequestAttributesFromForm(req);
                filterChain.doFilter(req,resp);
            } else if (path.startsWith("/LogIn")) {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PEOPLE WHERE EMAIL = ? AND PASSWORD = ?");
                preparedStatement.setString(1, req.getParameter("EMAIL"));
                preparedStatement.setString(2, req.getParameter("PASSWORD"));
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Controller.setRequestAttributesFromDB(req,resultSet);
                }
                filterChain.doFilter(req,resp);
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + login +"'" +
                    " AND PASSWORD = " +
                    "'" + password +"'" +
                    "").next()) {
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
