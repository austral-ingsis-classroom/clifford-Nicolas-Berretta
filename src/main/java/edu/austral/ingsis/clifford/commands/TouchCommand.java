package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CommandBody;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.fileSystem.Directory;
import edu.austral.ingsis.clifford.fileSystem.File;

import java.util.List;

public class TouchCommand implements Command {
    FileSystem fileSystem;
    public TouchCommand(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }
    @Override
    public String execute(CommandBody commandBody) {
        if(commandBody.isEmpty()){
            return "missing command body";
        }
        List<String> args = commandBody.getArguments();
        if(args.size() != 1){
            return "invalid number of arguments";
        }

        if(!notValidArgs(args)){
            Directory currentDir = fileSystem.getWorkingDirectory();
            String name = args.getFirst();
            currentDir.addChild(new File(name, currentDir));
        }
        return "'" + args.getFirst() + "' file created";
    }
    private boolean notValidArgs(List<String> args){
        return (args.contains(" ") || args.contains("/"));
    }
}
