package Repo_Analyzer.com.dto;

import java.util.List;

public record StreamChunk (
        String id,
        List<StreamChoice> choices
){
    public record StreamChoice(
            Integer index,
            Delta delta,
            String finishReason
    ) {}
    public record Delta(
            String role,      // usually only present in the first chunk
            String content,   // the actual token(s) being streamed
            List<ToolCall> toolCalls
    ) {}
}
