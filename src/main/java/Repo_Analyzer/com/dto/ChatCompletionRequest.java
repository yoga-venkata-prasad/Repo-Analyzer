package Repo_Analyzer.com.dto;

import java.util.List;

public record ChatCompletionRequest(String model,
         List<ChatMessage> messages,
         List<ToolDefinition> tools,
         Double temperature,
         Boolean stream
) {}
