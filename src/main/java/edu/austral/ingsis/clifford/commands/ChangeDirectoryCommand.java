package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CommandBody;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.fileSystem.Directory;

public class ChangeDirectoryCommand implements Command {
  private final FileSystem fileSystem;

  public ChangeDirectoryCommand(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  @Override
  public String execute(CommandBody commandBody) {
    if (commandBody.getArguments().size() != 1) {
      return "Invalid amount of arguments.";
    }
    if (!commandBody.getOptions().isEmpty()) {
      return "unnecessary options";
    }
    String argument = commandBody.getArguments().getFirst();
    switch (argument) {
      case "..":
        Directory parent = fileSystem.getWorkingDirectory().getParentDir();
        if (parent == null) {
          fileSystem.setWorkingDirectory(fileSystem.getRoot());
          return returnMessage(fileSystem.getRoot().getName());
        }
        fileSystem.setWorkingDirectory(parent);
        break;
      case ".":
        return argument + "is current directory";

      default:
        try {
          String[] path = argument.split("/");
          if (argument.startsWith("/")) {
            moveIfRoot(path);
          } else {
            moveTo(path);
          }
        } catch (Exception e) {
          return e.getMessage();
        }
        break;
    }
    return returnMessage(fileSystem.getWorkingDirectory().getName());
  }

  private void moveIfRoot(String[] path) {
    fileSystem.setWorkingDirectory(fileSystem.getRoot());
    try {
      moveTo(path);
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid path");
    }
  }

  private String returnMessage(String name) {
    return "moved to directory " + "'" + name + "'";
  }

  private void moveTo(String[] path) {
    Directory currentDirectory = fileSystem.getWorkingDirectory();
    for (String dir : path) {
      try {
        Directory object = fileSystem.getWorkingDirectory().getDirChildByName(dir);
        fileSystem.setWorkingDirectory(object);
      } catch (Exception e) {
        fileSystem.setWorkingDirectory(currentDirectory);
        throw new IllegalArgumentException("'" + dir + "'" + " directory does not exist");
      }
    }
  }
}
