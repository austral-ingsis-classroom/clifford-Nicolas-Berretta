package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.fileSystem.Directory;

public class FileSystem {
  private Directory root;
  private Directory workingDirectory;

  public FileSystem() {
    this.root = new Directory("/", null);
    this.workingDirectory = this.root;
  }

  public void setWorkingDirectory(Directory workingDirectory) {
    this.workingDirectory = workingDirectory;
  }

  public Directory getRoot() {
    return root;
  }

  public Directory getWorkingDirectory() {
    return workingDirectory;
  }
}
