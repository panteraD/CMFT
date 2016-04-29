<%--
  Created by IntelliJ IDEA.
  User: IRBIS
  Date: 01.02.2016
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
  <div>${checkDate}</div>
  <form name="date" action="<c:url value="/admin/input"/>" method="post">
    <table>
      <tr>
        <td>Enter date(dd.mm.yyyy)</td>
        <td><input type="text" autocomplete="off" name="inputDate" value=""></td>
        <td><input type="submit" name="input" value="Ok"></td>
      </tr>
      <tr>
        <td>Last date: ${lastDate}</td>
      </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  </form>
  <a href="<c:url value="/logout"/>">Logout</a>
</body>
</html>
