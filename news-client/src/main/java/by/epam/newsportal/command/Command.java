package by.epam.newsportal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Hanna_Blizniova on 8/3/2016.
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
