package edu.austral.ingsis.clifford.fileSystem;

public class File implements FileSystemObject {
    private final String name;
    private final Directory parentDir;


    public File(String name, Directory parentDir) {
        this.name = name;
        this.parentDir = parentDir;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Directory getParentDir() {
        return parentDir;
    }
}
