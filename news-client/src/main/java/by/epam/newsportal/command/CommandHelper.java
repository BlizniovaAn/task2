package by.epam.newsportal.command;

import by.epam.newsportal.command.impl.FilterNews;
import by.epam.newsportal.command.impl.GoTo;
import by.epam.newsportal.command.impl.GoToMainPage;
import by.epam.newsportal.command.impl.Localization;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hanna_Blizniova on 8/3/2016.
 */
public class CommandHelper {
    private Map<CommandName,Command> commands = new HashMap<CommandName, Command>();
    private static final CommandHelper instance = new CommandHelper();
    private CommandHelper(){
        commands.put(CommandName.GO_TO,new GoTo());
        commands.put(CommandName.LOCALIZATION,new Localization());
        commands.put(CommandName.FILTER_NEWS,new FilterNews());
        commands.put(CommandName.GO_TO_MAIN_PAGE,new GoToMainPage());
    }
    public static CommandHelper getInstance(){return instance;}

    public Command getCommand(String commandName){
        CommandName name = CommandName.valueOf(commandName.toUpperCase().trim());
        Command command;
        if(name != null){
            command = commands.get(name);
        }
        else{
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return command;
    }
}
