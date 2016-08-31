<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="top-link">
    <a href="/controller?command=FILTER_NEWS&authorId=${param['authorId']}&tagId=${param['tagId']}">BACK</a>
</div>
    <div class="news-header">
           <div class="header-left"> <span class="top-span"> <b>${newsPiece.title}</b> </span></div>
           <div class="header-right"> <span class="top-span"> ${newsPiece.creationDate}</span></div>
           <div class="header-middle"> <span class="top-span">(by
                <c:forEach items="${newsPiece.authors}" var="author">
                  ${author.name}
                </c:forEach>)</span></div>
    </div>
    <div class="main-body-current-news">
           <p>${newsPiece.fullText}</p>
    </div>
   <div class="comments-block">
       <c:forEach items="${newsPiece.comments}" var="comment">
           <div class="comment-item">
               <span class="data">${comment.creationDate}</span><br/>
               <span class="comment-text">${comment.commentText}</span>
           </div>
       </c:forEach>

       <textarea></textarea><br/>
       <input type="submit" value="Post comment" class="post-comment-button"/>
   </div>
<div class="bottom-links">
    <div class="prev"> <a href="#">PREVIOUS</a></div>
    <div class="next"> <a href="#">NEXT</a></div>
</div>


