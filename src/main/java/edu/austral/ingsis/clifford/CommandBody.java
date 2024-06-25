package edu.austral.ingsis.clifford;

import java.util.List;

public class CommandBody {
  private final List<String> options;
  private final List<String> arguments;

  // command [options] [arguments]
  public CommandBody(List<String> options, List<String> arguments) {
    this.options = options;
    this.arguments = arguments;
  }

  public List<String> getOptions() {
    return options;
  }

  public List<String> getArguments() {
    return arguments;
  }

  public boolean isEmpty() {
    return options.isEmpty() && arguments.isEmpty();
  }
}
