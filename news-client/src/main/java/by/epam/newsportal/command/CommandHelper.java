package by.epam.newsportal.command;

import by.epam.newsportal.command.impl.GoTo;
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
    }
    public static CommandHelper getInstance(){
        return instance;
    }

    public Command getCommand(String commandName){
        String trimmed = commandName.toUpperCase().trim();
        System.out.println("getCommand: " + trimmed);
        CommandName name = CommandName.valueOf(trimmed);
        Command command;
        if(name != null){
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return command;
    }
}
