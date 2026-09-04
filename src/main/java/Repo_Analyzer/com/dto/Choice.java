package Repo_Analyzer.com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(
        Integer index,
        ChatMessage message,
        @JsonProperty("finish_reason")
        String finishReason
) {
}
