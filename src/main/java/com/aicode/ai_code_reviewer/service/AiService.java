package com.aicode.ai_code_reviewer.service;

import org.springframework.stereotype.Service;

@Service
public class AiService {
    public String reviewCode(String code , String language) {
        return "Reviewing code " + code + " for " + language;
    }
}
