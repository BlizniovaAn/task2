<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.admin-i18n" var="language"/>
<fmt:message bundle="${language}" key="footer.text" var="footer_text" />
<p>${sitetitle}</p>
<footer>
    ${footer_text}
</footer>
