package Filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import static java.util.Objects.*;


public class NewFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String path = req.getRequestURI().substring(req.getContextPath().length());
        String login = req.getParameter("TEXT_3");
        String password = req.getParameter("TEXT_4");
        servletResponse.setCharacterEncoding("utf-8");
        servletRequest.setCharacterEncoding("utf-8");
        String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";

        if (!path.startsWith("8080/ee_war_exploded/Signin.jsp")) {
            filterChain.doFilter(req, resp);
            return;
        }

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();

            if (req.getSession().getAttribute("user") != null) {
                req.getSession().setAttribute("name", req.getAttribute("name"));
                req.getSession().setAttribute("surname", session.getAttribute("TEXT_2"));
                req.getSession().setAttribute("email", session.getAttribute("TEXT_3"));
                req.getSession().setAttribute("dateOfBirth", session.getAttribute("TEXT_6"));
                req.getSession().setAttribute("gender", session.getAttribute("TEXT_7"));
                req.getSession().setAttribute("bug", session.getAttribute("TEXT_8"));
                req.getSession().setAttribute("comments", (servletRequest.getAttribute("comments") == null)? "не задано":servletRequest.getAttribute("comments"));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
                requestDispatcher.forward(req,resp);
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + login +"'" +
                    " AND PASSWORD = " +
                    "'" + password +"'" +
                    "").next()) {
                req.getSession().setAttribute("TEXT_3", login);
                req.getSession().setAttribute("TEXT_4", password);
                filterChain.doFilter(req,resp);
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Signup.jsp");
                requestDispatcher.forward(req,resp);
                return;
            }

//            if (!statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
//                    "'" + login +"'" +
//                    " AND PASSWORD = " +
//                    "'" + password +"'" +
//                    "").next() &&
//                    req.getParameter("TEXT_1") == null &&
//                    req.getParameter("TEXT_2") == null &&
//                    req.getParameter("TEXT_6") == null &&
//                    req.getParameter("TEXT_7") == null &&
//                    req.getParameter("TEXT_8") == null &&
//                    req.getParameter("TEXT_9") == null) {
////                resp.sendRedirect("http://localhost:8080/ee_war_exploded/Signup.jsp");
//            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
