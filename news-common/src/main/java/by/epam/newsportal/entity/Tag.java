package by.epam.newsportal.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * This class used like transfer object for Tag entity.
 */

public class Tag {
    public static final Long ID = 1L;
    public static final String NAME = "title";
    private Long tagId;
    private String name;
    private Set<NewsPiece> news;

    public Tag(Long tagId, String name,Set<NewsPiece> news) {
        this.tagId = tagId;
        this.name = name;
        this.news = news;
    }
    public Tag (){
        this.tagId = ID;
        this.name = NAME;
        this.news = new HashSet<NewsPiece>();
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NewsPiece> getNews() {
        return news;
    }

    public void setNews(Set<NewsPiece> news) {
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
        Tag temp = (Tag) obj;
        if(!this.tagId.equals(temp.tagId)){
            return false;
        }
        if(!this.name.equals(temp.name)){
            return false;
        }
        if(!this.news.equals(temp.news)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 3 * this.tagId.hashCode() + 5 * this.name.hashCode() + 7 * this.news.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(tagId);
        sb.append(name);
        sb.append(news);
        return sb.toString();
    }

}
