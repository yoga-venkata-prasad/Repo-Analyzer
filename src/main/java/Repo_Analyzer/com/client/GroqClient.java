package Repo_Analyzer.com.client;

import Repo_Analyzer.com.dto.ChatCompletionRequest;
import Repo_Analyzer.com.dto.ChatCompletionResponse;
import Repo_Analyzer.com.dto.StreamChunk;
import Repo_Analyzer.com.properties.GroqProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import tools.jackson.databind.ObjectMapper;

@Component
public class GroqClient {
    private final WebClient webClient;
    private final GroqProperties groqProperties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    GroqClient(GroqProperties properties) {
        this.groqProperties = properties;
        this.webClient = WebClient.builder().baseUrl(properties.baseUrl()).
                defaultHeader("Authorization", "Bearer " + properties.apiKey()).
                defaultHeader("Content-Type", "application/json").build();
    }

    public ChatCompletionResponse chatCompletion(ChatCompletionRequest request) {
        return webClient.post()
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatCompletionResponse.class)
                .block();
    }

    public Flux<StreamChunk> streamChatCompletion(ChatCompletionRequest request) {
        ChatCompletionRequest streamingRequest = new ChatCompletionRequest(
                request.model(),
                request.messages(),
                request.tools(),
                request.temperature(),
                true
        );

        return webClient.post()
                .bodyValue(streamingRequest)
                .retrieve()
                .bodyToFlux(String.class)
                .filter(line -> !line.isBlank() && !line.equals("[DONE]"))
                .map(this::parseChunk)
                .filter(chunk -> chunk != null);
    }

    private StreamChunk parseChunk(String rawLine) {
        try {
            return objectMapper.readValue(rawLine, StreamChunk.class);
        } catch (Exception e) {
            return null;
        }
    }
}

