<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %><%--
  Created by IntelliJ IDEA.
  servlets.User: kusakin
  Date: 08.10.2019
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    response.setCharacterEncoding("utf-8");
    request.setCharacterEncoding("utf-8");
    String DATABASE_URL = "jdbc:oracle:thin:@192.168.1.151:1521:gmudb";
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection(DATABASE_URL, "INTERNSHIP", "internship");
        Statement statement = connection.createStatement();
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
        request.getSession().setAttribute("registration","Вы успешно зарегистрированы!");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller");
        requestDispatcher.forward(request,response);
    } catch (Exception e) {
        System.out.println(e);
    }
%>
</body>
</html>
