package by.epam.newsportal.command.impl;

import by.epam.newsportal.command.Command;
import by.epam.newsportal.command.CommandException;
import by.epam.newsportal.controller.JspPageName;
import by.epam.newsportal.controller.RequestParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
public class GoTo implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = JspPageName.getPage(request.getParameter(RequestParameterName.PAGE));
        return page;
    }
}
