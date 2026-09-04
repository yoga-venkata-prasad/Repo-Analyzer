package Repo_Analyzer.com.tools;

import Repo_Analyzer.com.dto.ToolDefinition;

public interface Tools {
    ToolDefinition getDefinition();
    String execute(String argumentsJson);
}
