<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="pagination" class="pagination">
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
                    <form method="post" action="/controller" id="pagination-form" >
                        <input type="hidden" name="command" value="GO_TO_MAIN_PAGE"/>
                        <input type="submit" class="pagination-button" value="${i.index}">
                        <input type="hidden" name="pageNumber" id="pageNumber" value="${i.index}"/>
                        <input type="hidden" name="page" id="page" value="INDEX"/>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
