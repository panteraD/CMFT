<%--
  Created by IntelliJ IDEA.
  User: IRBIS
  Date: 01.02.2016
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body onload='document.login.username.focus();'>
<h3>Please, enter username and password</h3>

<c:if test="${not empty error}">
  <div>${error}</div>
</c:if>
<c:if test="${not empty message}">
  <div>${message}</div>
</c:if>

<form name='login' action="<c:url value='/login' />" method='POST'>
  <table>
    <tr>
      <td>UserName:</td>
      <td><input type='text' autocomplete="off" name='username' value=''></td>
    </tr>
    <tr>
      <td>Password:</td>
      <td><input type='password' name='password' /></td>
    </tr>
    <tr>
      <td colspan='2'><input name="submit" type="submit" value="Enter" /></td>
    </tr>
  </table>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<a href="/registerPage/">Register</a>
</body>
</html>
