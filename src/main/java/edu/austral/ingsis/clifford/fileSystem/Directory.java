package edu.austral.ingsis.clifford.fileSystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemObject {
    private final String name;
    private Directory parent;
    private final List<FileSystemObject> children;

    public Directory(String name,Directory parent,List<FileSystemObject> children) {
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Directory getParentDir() {
        return parent;
    }

    public void deleteChild(String name) {
        this.children.removeIf(child -> child.getName().equals(name));
    }

    public void setParent(Directory parent) {
        if(this.name.equals("/")) {
            throw new IllegalArgumentException("cannot assign a parent directory to the root directory.");
        }
        this.parent = parent;
    }
}
