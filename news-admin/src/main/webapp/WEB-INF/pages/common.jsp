<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.admin-i18n" var="language"/>
<fmt:message bundle="${language}" key="site-title" var="title" />

<html>
<head>
    <title>${title}</title>
    <link href="${pageContext.request.contextPath}/styles/styles.css" rel="stylesheet" />
</head>
<body>
<tiles:insertAttribute name="header" />
<div class="container">
    <tiles:insertAttribute name="left-menu" />
    <div class="content">
        <tiles:insertAttribute name="body" />
    </div>
    <tiles:insertAttribute name="pagination" />
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>
