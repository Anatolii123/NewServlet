<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %><%--
  Created by IntelliJ IDEA.
  User: kusakin
  Date: 08.10.2019
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign up</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8"
          http-equiv="Cache-Control" content="no-cache">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
        form {
            position: absolute;
            left: 15px;
        }

        * {
            box-sizing: border-box;
        }

        input[type=text], input[type=password] {
            width: 40%;
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
<h3>Вы не зарегистрированы! Введите данные для регистрации.</h3>
<form action="AddToDB.jsp" method="post">
    <table width="200%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right" width="150">Имя:</td>
            <td><input type="text" name="TEXT_1" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td align="right">Фамилия:</td>
            <td><input type="text" name="TEXT_2" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td align="right">Email:</td>
            <td><input type="text" name="TEXT_3" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td align="right">Пароль:</td>
            <td><input type="text" name="TEXT_4" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td align="right">Подтверждение пароля:</td>
            <td><input type="text" name="TEXT_5" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td align="right">Дата рождения:</td>
            <td><input type="text" name="TEXT_6" maxlength="50" size="20"></td>
        </tr>
        <tr>
            <td align="right">Пол:</td>
            <td><select type="select" name="TEXT_7" rows="1">
                <option>мужской</option>
                <option>женский</option>
                <option>не определилось</option>
            </select>
            </td>
        </tr>
        <tr>
            <td align="right">Хотите поговорить о баге?</td>
            <td>
                <input name="TEXT_8" type="radio" value="Аминь"> Аминь
                <input name="TEXT_8" type="radio" value="Алюминь"> Алюминь
                <input name="TEXT_8" type="radio" value="Нет" checked> Нет
            </td>
        </tr>
        <tr>
            <td align="right" valign="top">Комментарий</td>
            <td><textarea name="TEXT_9" cols="49" rows="10"></textarea></td>
        </tr>
    </table>
    <br>
    <input type="submit" value="Регистрация">
</form>
</body>
</html>
