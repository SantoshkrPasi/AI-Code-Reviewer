package com.aicode.ai_code_reviewer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeResponse {
    private String bugs;
    private String improvements;
    private String optimization;
    private String cleanCode;
    private String rating;
}
