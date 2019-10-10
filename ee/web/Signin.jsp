<%@ page import="servlets.User" %><%--
  Created by IntelliJ IDEA.
  servlets.User: kusakin
  Date: 08.10.2019
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8"
          http-equiv="Cache-Control" content="nocache">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;

        }
        form {
            position: absolute;
            left: 28px;
        }

        * {
            box-sizing: border-box;
        }

        input[type=text], input[type=password] {
            width: 99%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

    </style>
</head>
<body>
<h3><%=request.getAttribute("loginError") != null ? request.getAttribute("loginError"):""%></h3>
<form action="LogIn" method="post">
    <table width="200%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right">Email:</td>
            <td><input type="text" name="TEXT_3" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td align="right">Пароль:</td>
            <td><input type="text" name="TEXT_4" maxlength="50" size="20"></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Войти">
</form>
</body>
</html>
