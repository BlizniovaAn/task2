package by.epam.newsportal.controller;

import by.epam.newsportal.command.Command;
import by.epam.newsportal.command.CommandException;
import by.epam.newsportal.command.CommandHelper;
import by.epam.newsportal.command.impl.FilterNews1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hanna_Blizniova on 8/3/2016.
 */
public final class Controller extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public Controller() { super();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response);
    }

    private static void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //FilterNews1 fn = new FilterNews1();
        CommandHelper ch = CommandHelper.getInstance();
      //  Command command = CommandHelper.getInstance();
        String command = request.getParameter(RequestParameterName.COMMAND);
        Command command1 = ch.getCommand(command);
        String page = null;
        try {
            page = command1.execute(request, response);
        } catch (CommandException ex){
            page = JspPageName.TECHNICAL_ERROR_PAGE;
        }catch (Exception e){
            page = JspPageName.TECHNICAL_ERROR_PAGE;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if(dispatcher != null){
            dispatcher.forward(request, response);
        }
        else{
            response.sendError(response.SC_NO_CONTENT);
        }
    }
}
