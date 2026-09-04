package Repo_Analyzer.com.dto;

import tools.jackson.databind.JsonNode;

public record FunctionSpec(String name , String description , JsonNode parameters) {
}
