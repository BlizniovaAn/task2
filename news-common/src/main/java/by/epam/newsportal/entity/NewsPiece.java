package by.epam.newsportal.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

/**
 * This class used like transfer object for News entity.
 */

public class NewsPiece {
    public static final String TITLE = "title";
    public static final String SHORT_TEXT = "s_text";
    public static final String FULL_TEXT = "f_text";
    public static final Long ID = 1L;

    private Long newsId;
    private String title;
    private String shortText;
    private String fullText;
    private Date creationDate;
    private Date modificationDate;
    private Set<Author> authors;
    private Set<Tag> tags;
    private List<Comment> comments;

    public NewsPiece(Long newsId, String title, String shortText, String fullText,
                     Date creationDate, Date modificationDate, Set<Author> authors,
                     Set<Tag> tags,List<Comment> comments) {
        this.newsId = newsId;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.authors = authors;
        this.tags = tags;
        this.comments = comments;
    }
    public NewsPiece(){
        this.authors = new HashSet<Author>();
        this.tags = new HashSet<Tag>();
        this.creationDate = new Date();
        this.modificationDate = new Date();
        this.comments = new ArrayList<Comment>();
        this.newsId = ID;
        this.shortText = SHORT_TEXT;
        this.fullText = FULL_TEXT;
        this.title = TITLE;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
        NewsPiece temp = (NewsPiece) obj;
        if(!this.newsId.equals(temp.newsId)){
            return false;
        }
        if(!this.title.equals(temp.title)){
            return false;
        }
        if(!this.shortText.equals(temp.shortText)){
            return false;
        }
        if(!this.fullText.equals(temp.fullText)){
            return false;
        }
        if(!this.creationDate.equals(temp.creationDate)){
            return false;
        }
        if(!this.modificationDate.equals(temp.modificationDate)){
            return false;
        }
        if(!this.authors.equals(temp.authors)){
            return false;
        }
        if(!this.tags.equals(temp.tags)){
            return false;
        }
        if(!this.comments.equals(temp.comments)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 3 * this.newsId.hashCode() + 5 * this.title.hashCode() + 9 * shortText.hashCode() +
                3 * this.fullText.hashCode() + 5 * this.creationDate.hashCode() + 9 * this.modificationDate.hashCode() +
                 3 * this.authors.hashCode() + 7 * this.tags.hashCode() + 9 * this.comments.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(newsId);
        sb.append(title);
        sb.append(shortText);
        sb.append(fullText);
        sb.append(creationDate);
        sb.append(modificationDate);
        sb.append(authors);
        sb.append(tags);
        sb.append(comments);
        return sb.toString();
    }
}
