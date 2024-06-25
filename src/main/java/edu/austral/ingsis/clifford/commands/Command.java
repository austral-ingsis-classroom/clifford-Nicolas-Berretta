package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.CommandBody;

public interface Command {
  // command [options] [arguments]
  public String execute(CommandBody commandBody);
}
