package by.epam.newsportal.command;

/**
 * Created by Hanna_Blizniova on 8/3/2016.
 */
public class CommandException extends Exception {
    public CommandException(String msg) { super(msg);}
    public CommandException(Exception e) { super(e);}
    public CommandException(String msg, Exception e) { super(msg, e);}
}
