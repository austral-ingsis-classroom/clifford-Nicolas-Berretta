package edu.austral.ingsis;

import edu.austral.ingsis.clifford.CommandLineInterface;

import java.util.ArrayList;
import java.util.List;

public class TestFileSystemRunner implements FileSystemRunner{
    @Override
    public List<String> executeCommands(List<String> commands) {
        CommandLineInterface cli = new CommandLineInterface();
        List<String> commandsOutput = new ArrayList<>();
        for (String command : commands) {
            commandsOutput.add(cli.executeCmd(command));
        }
        return commandsOutput;
    }
}
