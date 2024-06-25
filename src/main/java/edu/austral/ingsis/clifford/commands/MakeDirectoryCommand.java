package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CommandBody;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.fileSystem.Directory;

public class MakeDirectoryCommand  implements Command{
    private FileSystem fileSystem;

    public MakeDirectoryCommand(final FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public String execute(CommandBody commandBody) {
        if(commandBody.getArguments().size() != 1){
            return "missing argument";
        }
        String directory = commandBody.getArguments().getFirst();
        if(directory.contains(" ") || directory.contains("/")){
            return "invalid directory name, it cannot contain spaces or '/' characters.";
        }
        Directory workingDirectory = fileSystem.getWorkingDirectory();
        workingDirectory.addChild(new Directory(directory, workingDirectory, null));
        return "'" + directory + "' directory created";
    }
}
