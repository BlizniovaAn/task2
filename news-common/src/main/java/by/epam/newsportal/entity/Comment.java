package by.epam.newsportal.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * This class used like transfer object for Comment entity.
 */

public class Comment {
    public static final Long ID = 1L;
    public static final String TEXT = "TEXT";

    private Long commentId;
    private NewsPiece newsPiece;
    private String commentText;
    private Date creationDate;

    public Comment(Long commentId, NewsPiece newsPiece, String commentText, Date creationDate) {
        this.commentId = commentId;
        this.newsPiece = newsPiece;
        this.commentText = commentText;
        this.creationDate = creationDate;
    }

    public Comment(){
        this.commentId = ID;
        this.newsPiece = new NewsPiece();
        this.commentText = TEXT;
        this.creationDate = new Date();
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public NewsPiece getNewsPiece() {
        return newsPiece;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        Comment temp = (Comment) obj;
        if(!this.commentId.equals(temp.commentId)){
            return false;
        }
        if(!this.newsPiece.equals(temp.newsPiece)){
            return false;
        }
        if(!this.commentText.equals(temp.commentText)){
            return false;
        }
        if(!this.creationDate.equals(temp.creationDate)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 3 * this.commentId.hashCode() + 5 * this.newsPiece.hashCode() + 9 * commentText.hashCode() +
                3 * this.creationDate.hashCode() ;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(commentId);
        sb.append(newsPiece);
        sb.append(commentText);
        sb.append(creationDate);
        return sb.toString();
    }

}
