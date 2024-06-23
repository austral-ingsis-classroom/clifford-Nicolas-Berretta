package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandLineInterface {
    //command [options] [arguments]
    public String executeCmd(String commandLine) {
        String[] commandParts = commandLine.split(" ");
        String cmd = commandParts[0];
        CommandBody commandBody = commandBuilder(commandParts);

        return switch (cmd) {
            case "cd" -> new ChangeDirectoryCommand().execute(commandBody);
            case "ls" -> new ListCommand().execute(commandBody);
            case "mkdir" -> new MakeDirectoryCommand().execute(commandBody);
            case "rm" -> new RemoveCommand().execute(commandBody);
            case "touch" -> new TouchCommand().execute(commandBody);
            case "pwd" -> new PrintWorkingDirectoryCommand().execute(commandBody);
            default -> "command not found: " + cmd;
        };
    }

    private CommandBody commandBuilder(String[] commandParts){
        List<String> options = new ArrayList<>();
        List<String> arguments = new ArrayList<>();

        for (int i = 1; i < commandParts.length; i++) {
            //grabs the first  after the command, if it's an option, it needs to start with -
            // if not, it's only an arg
            if (commandParts[i].startsWith("-")) {
                options.add(commandParts[i]);
            } else {
                arguments.add(commandParts[i]);
            }
        }
        return new CommandBody(options, arguments);
    }
}
