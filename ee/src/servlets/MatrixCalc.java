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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ((request.getParameter("Operation").equals("Sum") ||
                request.getParameter("Operation").equals("Sub")) &&
                (!request.getParameter("size11").equals(request.getParameter("size21")) ||
                !request.getParameter("size12").equals(request.getParameter("size22")))) {
            request.setAttribute("CalcError","Матрицы разных размерностей. " +
                    "Размерность первой матрицы - " + request.getParameter("size11") + "x" + request.getParameter("size12") + ". " +
                    "Размерность второй матрицы - " + request.getParameter("size21") + "x" + request.getParameter("size22") + ".");
            request.getSession().setAttribute("CalcError","Матрицы разных размерностей. " +
                    "Размерность первой матрицы - " + request.getParameter("size11") + "x" + request.getParameter("size12") + ". " +
                    "Размерность второй матрицы - " + request.getParameter("size21") + "x" + request.getParameter("size22") + ".");
            Controller.setRequestAttributes(request);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(request,response);
            return;
        } else if (request.getParameter("Operation").equals("Mult") &&
                !request.getParameter("size12").equals(request.getParameter("size21"))) {
            request.setAttribute("CalcError","Матрицы несогласованы: число столбцов первой матрицы - " +
                    request.getParameter("size12") + ". " +
                    "Число строк второй матрицы - " + request.getParameter("size21") + ".");
            request.getSession().setAttribute("CalcError","Матрицы несогласованы: число столбцов первой матрицы - " +
                    request.getParameter("size12") + ". " +
                    "Число строк второй матрицы - " + request.getParameter("size21") + ".");
            Controller.setRequestAttributes(request);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(request,response);
        }
        request.setAttribute("CalcError",request.getParameter("11") + " " + request.getParameter("12"));
        request.getSession().setAttribute("CalcError",request.getParameter("11") + " " + request.getParameter("12"));
        System.out.println(request.getParameter("matrix1"));
        Controller.setRequestAttributes(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
        requestDispatcher.forward(request,response);
    }
}
