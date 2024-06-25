package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CommandBody;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.fileSystem.FileSystemObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ListCommand implements Command{
    private final FileSystem fileSystem;
    public ListCommand(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }
    @Override
    public String execute(CommandBody commandBody) {
        String option = commandBody.getOptions().getFirst();
        List<FileSystemObject> currentDir = fileSystem.getWorkingDirectory().getChildren();
        List<String> names = getNames(currentDir);
        return switch (option) {
            case "--ord=desc" -> {
                names.sort(Comparator.reverseOrder());
                yield namesToString(names);
            }
            case "--ord=asc" -> {
                names.sort(Comparator.naturalOrder());
                yield namesToString(names);
            }
            default -> throw new IllegalArgumentException("unknown option :" + option);
        };
    }

    private List<String> getNames(List<FileSystemObject> currentDir) {
        List<String> names = new ArrayList<>();
        for (FileSystemObject fileSystemObject : currentDir) {
            names.add(fileSystemObject.getName());
        }
        return names;
    }
    private String namesToString(List<String> names) {
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
            sb.append(name);
        }
        return sb.toString();
    }
}
