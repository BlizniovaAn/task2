<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="login">
    <form name='loginForm' action="<c:url value='/login' />"method='POST'>
        <label>Login:</label><input type='text' name='login'>
        <label>Password:</label><input type='password' name='password' />
        <input name="submit" type="submit" value="submit" />
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</div>
