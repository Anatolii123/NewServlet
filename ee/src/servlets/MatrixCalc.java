package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MatrixCalc")
public class MatrixCalc extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("size11").equals(request.getParameter("size21"))) {

        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Signin.jsp");
        requestDispatcher.forward(request,response);
    }
}
