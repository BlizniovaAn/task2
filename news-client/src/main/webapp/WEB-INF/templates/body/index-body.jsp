<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.newsportal" var="language"/>
<fmt:message bundle="${language}" key="header.site-title" var="sitetitle" />

<p>
<form action="/controller" method="post" id="filter-form">
    <div class="drownboxes">
            <select id="author" name="authorId">
                <option value="" disabled selected>Select the author</option>
                <option value="0">All</option>
                <c:forEach items="${authors}" var="author">
                    <option value="${author.authorId}">${author.name}</option>
                </c:forEach>
            </select>
            <select id="tag">
                <option value="" disabled selected>Select the tag</option>
                <c:forEach items="${tags}" var="tag">
                    <option value="${tag.tagId}">
                       ${tag.name}
                    </option>
                </c:forEach>
            </select>
            <input type="hidden" name="command"  value="FILTER_NEWS"/>
            <button type="submit" value="Submit">Submit</button>
            <button type="reset" value="Reset">Reset</button>

    </div>
    <div class="items">
        <c:choose>
            <c:when test="${empty newsList}">
                <h1>No result</h1>
            </c:when>
            <c:when test="${not empty newsList}">
                <c:forEach items="${newsList}" var="news">
                    <div class="item">
                            <p class="top-item">${news.title}
                                (by <c:forEach items="${news.authors}" var="author">
                                    ${author.name}
                                </c:forEach>)   ${news.creationDate}</p>
                            <p class="body-item"> ${news.shortText} </p>
                            <p class="bottom-item">
                                <c:forEach items="${news.tags}" var="tag">
                                    ${tag.name}
                                </c:forEach>
                                Comments(${news.commentCount})
                                <c:choose>
                                    <c:when test="${empty param['authorId']}">
                                        <a href="/controller?command=GO_TO_CURRENT_NEWS&newsId=${news.newsId}&authorId=0&tagId=${param['tagId']}"/>View</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/controller?command=GO_TO_CURRENT_NEWS&newsId=${news.newsId}&authorId=${param['authorId']}&tagId=${param['tagId']}"/>View</a>
                                    </c:otherwise>
                                </c:choose>

                            </p>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </div
</form>
</p>
<h4></h4>