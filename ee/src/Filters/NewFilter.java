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

//        if (!path.startsWith("8080/ee_war_exploded/Signin.jsp")) {
//            filterChain.doFilter(req, resp);
//            return;
//        }

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
            Statement statement = connection.createStatement();

            if (req.getSession().getAttribute("user") != null) {
                    req.setAttribute("name", req.getSession().getAttribute("name"));
                    req.setAttribute("surname", req.getSession().getAttribute("surname"));
                    req.setAttribute("email", req.getSession().getAttribute("email"));
                    req.setAttribute("dateOfBirth", req.getSession().getAttribute("dateOfBirth"));
                    req.setAttribute("gender", req.getSession().getAttribute("gender"));
                    req.setAttribute("bug", req.getSession().getAttribute("bug"));
                    req.setAttribute("comments", (req.getSession().getAttribute("comments") == null)? "не задано":req.getSession().getAttribute("comments"));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/View.jsp");
                requestDispatcher.forward(req,resp);
                return;
            } else if (statement.executeQuery("SELECT * FROM PEOPLE WHERE EMAIL = " +
                    "'" + login +"'" +
                    " AND PASSWORD = " +
                    "'" + password +"'" +
                    "").next()) {
                req.setAttribute("name", req.getSession().getAttribute("name"));
                req.setAttribute("surname", req.getSession().getAttribute("surname"));
                req.setAttribute("email", req.getSession().getAttribute("email"));
                req.setAttribute("dateOfBirth", req.getSession().getAttribute("dateOfBirth"));
                req.setAttribute("gender", req.getSession().getAttribute("gender"));
                req.setAttribute("bug", req.getSession().getAttribute("bug"));
                req.setAttribute("comments", (req.getSession().getAttribute("comments") == null)? "не задано":req.getSession().getAttribute("comments"));
                filterChain.doFilter(req,resp);
                return;
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
