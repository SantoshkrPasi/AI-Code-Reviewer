package com.aicode.ai_code_reviewer.service;

import com.aicode.ai_code_reviewer.dto.CodeResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class AiService {
    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    // Constructor (Spring injects WebClient.Builder)
    public AiService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public CodeResponse reviewCode(String code, String language) {

        // Step 1: Create prompt
        String prompt = "You are a senior software engineer.\n"
                + "Analyze the given code and return ONLY valid JSON.\n"
                + "Do NOT leave any field empty.\n"
                + "Each field must contain meaningful explanation.\n\n"
                + "Return strictly in this format:\n"
                + "{\n"
                + "\"bugs\": \"Explain if any bugs or say 'No bugs found'\",\n"
                + "\"improvements\": \"Give at least one improvement\",\n"
                + "\"optimization\": \"Explain optimization or say 'No optimization needed'\",\n"
                + "\"cleanCode\": \"Comment on code quality\",\n"
                + "\"rating\": \"Give rating out of 10 like 6/10\"\n"
                + "}\n\n"
                + "Code:\n" + code;

        // Step 2: Create request body
        Map<String, Object> body = Map.of(
                "model", "openai/gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );

        // Step 3: Call OpenRouter API
        String response = webClient.post()
                .uri("https://openrouter.ai/api/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .header("HTTP-Referer", "http://localhost:8080")
                .header("X-Title", "AI Code Reviewer")
                .bodyValue(body)
                .retrieve()
                .onStatus(status -> status.isError(), res ->
                        reactor.core.publisher.Mono.error(
                                new RuntimeException("AI API Error")
                        )
                )
                .bodyToMono(String.class)
                .block();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            String content = root
                    .path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();
            // Convert AI JSON string → CodeResponse
            System.out.println("RAW AI CONTENT:\n" + content);
            return mapper.readValue(content, CodeResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("AI failed: " + e.getMessage());
        }
    }
}

