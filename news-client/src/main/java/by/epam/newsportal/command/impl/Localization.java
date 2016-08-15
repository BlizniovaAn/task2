package by.epam.newsportal.command.impl;

import by.epam.newsportal.command.Command;
import by.epam.newsportal.command.CommandException;
import by.epam.newsportal.command.PageHelper;
import by.epam.newsportal.controller.JspPageName;
import by.epam.newsportal.controller.RequestParameterName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Hanna_Blizniova on 8/8/2016.
 */
public class Localization implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(true);
        session.setAttribute(RequestParameterName.LANGUAGE,request.getParameter(RequestParameterName.LANGUAGE));
        String uri = request.getParameter(RequestParameterName.PAGE);
        System.out.println("page from pagehelper - " + PageHelper.preparePage(uri));
        //System.out.println("pageName - " + pageName);
        String preparedPage = JspPageName.getPage(PageHelper.preparePage(uri));
        System.out.println("prepared page - " + preparedPage);
        return preparedPage;
    }
}
