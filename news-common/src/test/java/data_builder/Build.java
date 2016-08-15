package data_builder;

import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface Build {
    Set<Author> createAuthors() throws ParseException;
    Author createAuthor() throws ParseException;
    Author createNewAuthor() throws ParseException;
    NewsPiece createNewsPiece();
    List<Comment> createComments();
    Comment createComment();
    Comment createNewComment();
    Set<Tag> createTags();
    Tag createTag();
    Tag createNewTag();
    Set<Tag> createNewTags();
    NewsPiece createNewNewsPiece();
}
