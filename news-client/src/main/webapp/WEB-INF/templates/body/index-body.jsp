<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.newsportal" var="language"/>
<fmt:message bundle="${language}" key="header.site-title" var="sitetitle" />

<p>
    <div class="drownboxes">
        <form action="/controller" method="post" id="filter-form">
            <select id="author">
                <c:forEach items="${authors}" var="author">
                    <option value="" disabled selected>Select the author</option>
                    <option value="${author.authorId}">${author.name}</option>
                </c:forEach>
            </select>
            <select id="tag" multiple="multiple">
                <c:forEach items="${tags}" var="tag">
                    <option value="" disabled selected>Select the tag</option>
                    <option value="${tag.tagId}">${tag.tagName}</option>
                </c:forEach>
            </select>
            <button type="submit" value="Submit">Submit</button>
            <button type="reset" value="Reset">Reset</button>
        </form>
    </div>
    <div id="pagination">
        <c:forEach begin="1" end="${maxPage}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <div class="inline">
                        <form>
                            <input id="selected-page-button" type="submit" value="${i.index}" disabled="true">
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="inline">
                        <form id="pagination-form">
                            <input type="submit" value="${i.index}">
                            <input type="hidden" name="pageNumber" id="pageNumber" value="${i.index}"/>
                            <input type="hidden" name="page" id="page" value="INDEX"/>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</p>