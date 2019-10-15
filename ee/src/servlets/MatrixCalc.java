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
                (!request.getParameter("matrix1_rows").equals(request.getParameter("matrix2_rows")) ||
                !request.getParameter("matrix1_columns").equals(request.getParameter("matrix2_columns")))) {
            request.getSession().setAttribute("CalcError","Матрицы разных размерностей. " +
                    "Размерность первой матрицы - " + request.getParameter("matrix1_rows") + "x" + request.getParameter("matrix2_rows") + ". " +
                    "Размерность второй матрицы - " + request.getParameter("matrix1_columns") + "x" + request.getParameter("matrix2_columns") + ".");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(request,response);
            return;
        } else if (request.getParameter("Operation").equals("Mult") &&
                !request.getParameter("matrix1_columns").equals(request.getParameter("matrix2_rows"))) {
            request.getSession().setAttribute("CalcError","Матрицы несогласованы: число столбцов первой матрицы - " +
                    request.getParameter("matrix1_columns") + ". " +
                    "Число строк второй матрицы - " + request.getParameter("matrix2_rows") + ".");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
            requestDispatcher.forward(request,response);
        }
        request.getSession().setAttribute("CalcError",request.getParameter("111") + " " + request.getParameter("112"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/View.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
