package com.aicode.ai_code_reviewer.dto;

import lombok.Data;

@Data
public class CodeRequest {
    private String code;
    private String language;
}
