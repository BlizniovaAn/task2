<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
   <span class="site-title">News portal</span>
    <ul class="languages-block">
        <li><a class="link" href="/controller?command=LOCALIZATION&language=en&page=${pageContext.request.servletPath}">EN</a></li>
        <li><a class="link" href="/controller?command=LOCALIZATION&language=ru&page=${pageContext.request.servletPath}">RU</a></li>
    </ul>
</header>
