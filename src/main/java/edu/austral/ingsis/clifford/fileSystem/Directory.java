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
    public FileSystemObject getChildByName(String name) {
        for(FileSystemObject child : children) {
            if(child.getName().equals(name)) {
                return child;
            }
        }
        throw new IllegalArgumentException("no such file or directory: " + name);
    }
    public Directory getDirChildByName(String name) {
        FileSystemObject child = getChildByName(name);

        if(child instanceof Directory) {
            return (Directory) child;
        }
        throw new IllegalArgumentException("no such directory: " + name);
    }
    public void deleteChildByName(String name) {
        boolean result = children.removeIf(child -> child.getName().equals(name));
        if(!result) {
            throw new IllegalArgumentException("no such file or directory: " + name);
        }
    }
    public List<FileSystemObject> getChildren(){
        return children;
    }
    public void addChild(FileSystemObject child) {
        children.add(child);
    }

}
