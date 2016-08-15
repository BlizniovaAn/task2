package by.epam.newsportal.service.search_criteria;

import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Object of this class used as container  for search criteria.
 */
public class SearchCriteria {
    private Author author;
    private List<Tag> tags;

    public SearchCriteria(){
        this.author = new Author();
        this.tags = new ArrayList<Tag>();
    }

    public SearchCriteria(Author author, List<Tag> tags) {
        this.author = author;
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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
        SearchCriteria temp = (SearchCriteria) obj;
        if(!this.author.equals(temp.author)){
            return false;
        }
        if(!this.tags.equals(temp.tags)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 3 * this.author.hashCode() + 5 * this.tags.hashCode();
    }
}
