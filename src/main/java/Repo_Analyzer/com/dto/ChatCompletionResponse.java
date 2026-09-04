package Repo_Analyzer.com.dto;

import java.util.List;

public record  ChatCompletionResponse(String id,
        List<Choice> choices)
{}
