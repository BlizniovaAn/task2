<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.admin-i18n" var="language"/>
<fmt:message bundle="${language}" key="button_en" var="EN" />
<fmt:message bundle="${language}" key="button_ru" var="RU" />
<fmt:message bundle="${language}" key="site-title" var="title" />
<header>
    <span class="site-title">${title}</span>
    <div class="logout-block">
        <p>
            Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
        </p>
        <input type="submit" value="Logout"/>
    </div>
    <ul class="languages-block">
        <li><a class="link" href="/controller?command=LOCALIZATION&language=en&page=${pageContext.request.servletPath}">${EN}</a></li>
        <li><a class="link" href="/controller?command=LOCALIZATION&language=ru&page=${pageContext.request.servletPath}">${RU}</a></li>
    </ul>
</header>
