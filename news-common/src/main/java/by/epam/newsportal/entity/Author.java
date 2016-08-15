package by.epam.newsportal.entity;

import java.util.*;
import javax.persistence.Table;

/**
 * This class used like transfer object for Author entity.
 */

public class Author {
    public static final String NAME = "name";
    public static final Long ID = 1L;
    private Long authorId;

    private String name;
    private Date expiredDate;
    private List<NewsPiece> news;

    public Author(){
        this.name = NAME;
        this.authorId = ID;
        this.expiredDate = new Date();
        this.news = new ArrayList<NewsPiece>();
    }

    public Author(Long authorId, String name, Date expiredDate,ArrayList<NewsPiece> news) {
        this.authorId = authorId;
        this.name = name;
        this.expiredDate = expiredDate;
        this.news = news;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public List<NewsPiece> getNews() {
        return news;
    }

    public void setNews(List<NewsPiece> news) {
        this.news = news;
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
        Author temp = (Author) obj;
        if(!this.authorId.equals(temp.authorId)){
            return false;
        }
        if(!this.name.equals(temp.name)){
            return false;
        }
        if(!this.expiredDate.equals(temp.expiredDate)){
            return false;
        }
        if(!this.news.equals(temp.news)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 3 * this.authorId.hashCode() + 5 * this.name.hashCode()  +
                9 * this.expiredDate.hashCode() + 7 * this.news.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(authorId);
        sb.append(name);
        sb.append(expiredDate);
        sb.append(news);
        return sb.toString();
    }

}
