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
<div style="display: grid">
  <form name="account" id="account" action="LogOut">
    <h4><c:out value="${sessionScope.CalcError ne null ? sessionScope.CalcError : ''}"/></h4>
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
          <td><input type="text" name="matrix1_rows" id="m1r" size="5" oninput="buildMatrix(s11,s12,1,firstMat)"
                     value="<c:out value="${sessionScope.matrix1_rows eq null ? '4' : sessionScope.matrix1_rows}"/>"
                     onkeyup="return check(this);" onchange="return check(this);"></td>
          <td align="center"><b>x</b></td>
          <td><input type="text" name="matrix1_columns" id="m1c" size="5" oninput="buildMatrix(s11,s12,1,firstMat)"
                     value="<c:out value="${sessionScope.matrix1_columns eq null ? '4' : sessionScope.matrix1_columns}"/>"
                     onkeyup="return check(this);" onchange="return check(this);"></td>
        </tr>
        <tr>
          <td><b>Матрица 2:</b></td>
          <td><input type="text" style="width: 14px" name="matrix2_rows" id="m2r" size="5" oninput="buildMatrix(s21,s22,2,secondMat)"
                     value="<c:out value="${sessionScope.matrix2_rows eq null ? '4' : sessionScope.matrix2_rows}"/>"
                     onkeyup="return check(this);" onchange="return check(this);"></td>
          <td align="center"><b>x</b></td>
          <td><input type="text" name="matrix2_columns" id="m2c" size="5" oninput="buildMatrix(s21,s22,2,secondMat)"
                     value="<c:out value="${sessionScope.matrix2_columns eq null ? '4' : sessionScope.matrix2_columns}"/>"
                     onkeyup="return check(this);" onchange="return check(this);"></td>
        </tr>
        <tr>
          <td><b>Операция: </b></td>
          <td><input name="Operation" type="radio" value="Sum" checked>+</td>
          <td><input name="Operation" type="radio" value="Sub">-</td>
          <td><input name="Operation" type="radio" value="Mult">*</td>
        </tr>
      </table>
      <table name="matrix1" id="matrix1"></table>
      <table name="matrix2" id="matrix2"></table>
      <c:if test="${sessionScope.m311 ne null}">
        <table id="result" style="left: 670px">
          <caption><b>Результат</b></caption>
          <%
            String row = new String();
            row += "<tr>";
            for (int i = 1; i <= Integer.parseInt(request.getSession().getAttribute("matrix1_rows").toString()); i++) {
              for (int j = 1; j <= Integer.parseInt(request.getSession().getAttribute("matrix2_columns").toString()); j++) {
                row += "<td><input style=\"width: " + request.getSession().getAttribute("m3" + i + j).toString().length()*9 + "px\"" +
                        " type=\"text\" maxlength=\"50\" size=\"5\" id=\"m3" + i + j +
                        "\" name=\"m3" + i + j +"\"\n" +
                        " onkeyup=\"return checkInput(this);\" onchange=\"return checkInput(this);\" value=\"" +
                        request.getSession().getAttribute("m3" + i + j).toString().replace(".0","") + "\"" +
                        "></td>";
              }
              row += "</tr>";
            }
          %>
          <%=row%>
        </table>
      </c:if>
<%--      <c:if test="${sessionScope.matrix1_rows ne null && sessionScope.matrix2_rows ne null}">--%>
<%--        <table name="matrix3" id="matrix3">--%>
<%--          <c:forEach begin="1" end="${sessionScope.matrix1_rows}" var="i">--%>
<%--            <tr>--%>
<%--              <c:forEach begin="1" end="${sessionScope.matrix2_columns}" var="j">--%>
<%--                &lt;%&ndash;                <c:set var="cellName" value="${\".m3\" + i.toString()+j.toString()}"/>&ndash;%&gt;--%>
<%--                <c:set var="cellName" value="${i.toString()}"/>--%>
<%--                <td>--%>
<%--                  <c:out value="${cellName}"/>--%>
<%--                </td>--%>
<%--              </c:forEach>--%>
<%--            </tr>--%>
<%--          </c:forEach>--%>
<%--        </table>--%>
<%--      </c:if>--%>

    </div>
    <input type="submit" value="Вычислить" id="calc">
  </form>
</div>
<div class="center">
  <input type="checkbox" id="cbx" style="display:none"/>
  <label for="cbx" class="toggle"><span></span></label>
</div>
</body>
</html>
