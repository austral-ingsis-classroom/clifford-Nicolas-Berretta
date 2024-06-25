package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CommandBody;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.fileSystem.FileSystemObject;

import java.util.Deque;
import java.util.LinkedList;

public class PrintWorkingDirectoryCommand implements Command {
    private final FileSystem fileSystem;

    public PrintWorkingDirectoryCommand(FileSystem fileSystem){
        this.fileSystem = fileSystem;;
    }

    @Override
    public String execute(CommandBody commandBody) {
        if(!commandBody.isEmpty()){
            return "unnecessary argument";
        }
        return getWorkingDirPath(fileSystem.getWorkingDirectory());
    }

    private String getWorkingDirPath(FileSystemObject fileSystemObject) {
        Deque<String> pathElements = new LinkedList<>();
        FileSystemObject object = fileSystemObject;
        while (object.getParentDir() != null){
            pathElements.addFirst(object.getName());
            object = object.getParentDir();
        }
        return "/" + String.join("/", pathElements);
    }
}
