<%--
  Created by IntelliJ IDEA.
  servlets.User: Анатолий
  Date: 28.09.2019
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Обработка данных форм</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8"
        http-equiv="Cache-Control" content="no-cache">
  <style> @import "assets/css/ViewStyle.css"; </style>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
  <script async src="main.js"></script>
  </head>
<body>
<h4><c:out value="${sessionScope.CalcError ne null ? sessionScope.CalcError : ''}"/></h4>
<div style="display: grid">
  <form name="account" id="account" action="LogOut">
    <h3><c:out value="${sessionScope.registration ne null ? sessionScope.registration : ''}"/></h3>
    <p>Имя: <c:out value="${sessionScope.name eq '' ? 'не заполнено' : sessionScope.name}"/></p>
    <p>Фамилия: <c:out value="${sessionScope.surname eq '' ? 'не заполнено' : sessionScope.surname}"/></p>
    <p>Email: <c:out value="${sessionScope.email eq '' ? 'не заполнено' : sessionScope.email}"/></p>
    <p>Дата рождения: <c:out value="${sessionScope.dateOfBirth eq '' ? 'не заполнено' : sessionScope.dateOfBirth}"/></p>
    <p>Пол: <c:out value="${sessionScope.gender eq '' ? 'не заполнено' : sessionScope.gender}"/></p>
    <p >О баге: <c:out value="${sessionScope.bug eq '' ? 'не заполнено' : sessionScope.bug}"/></p>
    <p>Комментарий: <c:out value="${sessionScope.comments eq '' ? 'не заполнено' : sessionScope.comments}"/></p>
    <input type="submit" value="Выйти" id="logout">
  </form>
</div>
<br><br><br>
<div style="display: grid">
  <form name="mtx" id="mtx" action="MatrixCalc" method="post">
    <div>
      <table name="size">
        <tr>
          <td><b>Матрица 1: </b></td>
          <td><input type="text" name="size11" id="sz11" size="5" oninput="buildMatrix(s11,s12,1,firstMat)"
                     value="4"
                     onkeyup="return check(this);" onchange="return check(this);"></td>
          <td align="center"><b>x</b></td>
          <td><input type="text" name="size12" id="sz12" size="5" oninput="buildMatrix(s11,s12,1,firstMat)" value="4"
                     onkeyup="return check(this);" onchange="return check(this);"></td>
        </tr>
        <tr>
          <td><b>Матрица 2:</b></td>
          <td><input type="text" name="size21" id="sz21" size="5" oninput="buildMatrix(s21,s22,2,secondMat)" value="4"
                     onkeyup="return check(this);" onchange="return check(this);"></td>
          <td align="center"><b>x</b></td>
          <td><input type="text" name="size22" id="sz22" size="5" oninput="buildMatrix(s21,s22,2,secondMat)" value="4"
                     onkeyup="return check(this);" onchange="return check(this);"></td>
        </tr>
        <tr>
          <td><b>Операция: </b></td>
          <td><input name="Operation" type="radio" value="Sum" checked>+</td>
          <td><input name="Operation" type="radio" value="Sub">-</td>
          <td><input name="Operation" type="radio" value="Mult">*</td>
        </tr>
      </table>
    </div>
    <div style="display: table">
      <div style="display: table-row">
        <div style="display: table-cell"><table name="matrix1" id="matrix1" ></table></div>
        <div style="display: table-cell"><table name="matrix2" id="matrix2" ></table></div>
      </div>
    </div>
    <input type="submit" value="Вычислить" id="calc">
  </form>
</div>
<div class="center">
  <input type="checkbox" id="cbx" style="display:none"/>
  <label for="cbx" class="toggle"><span></span></label>
</div>
</form>
</body>
</html>
