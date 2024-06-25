package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CommandBody;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.fileSystem.Directory;
import edu.austral.ingsis.clifford.fileSystem.FileSystemObject;

import java.util.List;

public class RemoveCommand implements Command{
    FileSystem fileSystem;

    public RemoveCommand(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String execute(CommandBody commandBody) {
        if(commandBody.isEmpty()){
            return "missing arguments and options";
        }
        List<String> args = commandBody.getArguments();
        if(args.size() != 1) {
            return "invalid number of arguments";
        }
        String name = args.getFirst();
        if(!exists(name)){
            return name + "directory or file not found";
        };
        FileSystemObject object = fileSystem.getWorkingDirectory().getChildByName(name);

        if(isDir(object)){
            if(commandBody.getOptions().isEmpty()){
                return "cannot remove " + "'" + name + "'" + ", is a directory";
            }
            String option = commandBody.getOptions().getFirst();
            if(option != null && option.equals("--recursive")){
                fileSystem.getWorkingDirectory().deleteChild(name);
            } else {
                return "unable to remove directory";
            }
        } else {
            fileSystem.getWorkingDirectory().deleteChild(name);
        }
        return "'" + name + "'" + " removed";
    }

    private boolean isDir(FileSystemObject fileSystemObject) {
        return fileSystemObject instanceof Directory;
    }

    private boolean exists(String name){
        try {
            fileSystem.getWorkingDirectory().getChildByName(name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
