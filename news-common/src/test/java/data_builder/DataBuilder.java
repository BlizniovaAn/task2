package data_builder;

import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataBuilder implements Build {
    public static final String AUTHOR_NAME = "Anna";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "2016-04-15 08:49:16";
    public static final String NEWS_TITLE = "Muhammad Ali";
    public static final String NAME = "testName";
    public static final String TEST_TEXT = "testText";
    public static final Long COMMENT_ID = 6L;
    public static final Long OLD_COMMENT_ID = 1L;
    public static final Long NEW_TAG_ID = 5L;
    public static final Long OLD_TAG_ID = 2L;
    public static final Long ID = 1L;
    public static final Long NEW_ID = 100L;


    public DataBuilder(){}

    public Set<Author> createAuthors() throws ParseException {
        Set<Author> authors = new HashSet<Author>();
        Author newAuthor = new Author();
        newAuthor.setAuthorId(ID);
        authors.add(newAuthor);
        return authors;
    }

    public Author createAuthor() throws ParseException {
        Author author = new Author();
        author.setAuthorId(ID);
        author.setName(AUTHOR_NAME);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date date = format.parse(DATE);
        author.setExpiredDate(date);
        return author;
    }

    public Author createNewAuthor() throws ParseException {
        Author author = new Author();
        author.setAuthorId(NEW_ID);
        author.setName(AUTHOR_NAME);
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        Date date = format.parse(DATE);
        author.setExpiredDate(date);
        return author;
    }

    public NewsPiece createNewsPiece() {
        NewsPiece newsPiece = new NewsPiece();
        newsPiece.setNewsId(ID);
        newsPiece.setTitle(NEWS_TITLE);
        return newsPiece;
    }

    public NewsPiece createNewNewsPiece() {
        NewsPiece newsPiece = new NewsPiece();
        newsPiece.setTitle(TEST_TEXT);
        newsPiece.setShortText(TEST_TEXT);
        newsPiece.setFullText(TEST_TEXT);
        newsPiece.setCreationDate(new Date());
        newsPiece.setModificationDate(new Date());
        newsPiece.setNewsId(NEW_ID);
        newsPiece.setTitle(NEWS_TITLE);
        return newsPiece;
    }
    public List<Comment> createComments() {
        List<Comment> comments = new ArrayList<Comment>();
        Comment newComment = new Comment();
        newComment.setCommentId(COMMENT_ID);
        comments.add(newComment);
        return comments;
    }

    public Comment createComment() {
        Comment comment = new Comment();
        comment.setCommentId(OLD_COMMENT_ID);
        return comment;
    }

    public Comment createNewComment() {
        Comment comment = new Comment();
        comment.setCommentId(NEW_ID);
        return comment;
    }

    public Set<Tag> createTags(){
        Set<Tag> tags = new HashSet<Tag>();
        Tag tag = new Tag();
        tag.setTagId(OLD_TAG_ID);
        tags.add(tag);
        return tags;
    }

    public Tag createTag() {
        Tag tag = new Tag();
        tag.setTagId(3L);
        return tag;
    }

    public Tag createNewTag() {
        Tag tag = new Tag();
        tag.setTagId(NEW_ID);
        tag.setName(NAME);
        return tag;
    }

    public Set<Tag> createNewTags(){
        Set<Tag> tags = new HashSet<Tag>();
        Tag tag = new Tag();
        tag.setTagId(NEW_TAG_ID);
        tags.add(tag);
        return tags;
    }
}
