package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CommandBody;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.fileSystem.FileSystemObject;

public class PrintWorkingDirectoryCommand implements Command {
    private FileSystem fileSystem;

    public PrintWorkingDirectoryCommand(FileSystem fileSystem){
        this.fileSystem = fileSystem;;
    }

    @Override
    public String execute(CommandBody commandBody) {
        if(commandBody.getArguments().size() != 1){
            return "missing argument";
        }
        return getWorkingDirPath(fileSystem.getWorkingDirectory());
    }

    private String getWorkingDirPath(FileSystemObject fileSystemObject) {
        StringBuilder workingPath = new StringBuilder();
        FileSystemObject object = fileSystemObject;
        while (object.getParentDir() != null){
            workingPath.append("/").append(object.getParentDir().getName());
            object = object.getParentDir();
        }
        return workingPath.toString();
    }
}
