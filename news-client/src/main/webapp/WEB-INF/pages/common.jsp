<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.newsportal" var="language"/>
<fmt:message bundle="${language}" key="header.site-title" var="title" />

<html>
<head>
    <title>${title}</title>
    <script src="${pageContext.request.contextPath}/scripts/news.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/link.js"></script>
    <link href="${pageContext.request.contextPath}/styles/styles.css" rel="stylesheet" />
</head>
<body>
<tiles:insertAttribute name="header" />
<div class="container">
    <div class="content">
        <tiles:insertAttribute name="body" />
    </div>
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>
