<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.newsportal" var="language"/>
<fmt:message bundle="${language}" key="button_en" var="EN" />
<fmt:message bundle="${language}" key="button_ru" var="RU" />
<fmt:message bundle="${language}" key="header.site-title" var="title" />
<header>
   <span class="site-title">${title}</span>
    <ul class="languages-block">
        <li><a class="link" href="/controller?command=LOCALIZATION&language=en&page=${pageContext.request.servletPath}">${EN}</a></li>
        <li><a class="link" href="/controller?command=LOCALIZATION&language=ru&page=${pageContext.request.servletPath}">${RU}</a></li>
    </ul>
</header>
