package Repo_Analyzer.com.tools;

import Repo_Analyzer.com.dto.ToolDefinition;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import javax.tools.Tool;

@Component
public class ReadFiletool implements Tools {
    private static ObjectMapper objectMapper= new ObjectMapper();
    private static JsonNode jsonNode ;

    @Override
    public ToolDefinition getDefinition() {

    }

    @Override
    public String execute(String argumentsJson) {

    }
}
