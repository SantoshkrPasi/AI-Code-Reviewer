package com.aicode.ai_code_reviewer.controller;

import com.aicode.ai_code_reviewer.dto.CodeRequest;
import com.aicode.ai_code_reviewer.dto.CodeResponse;
import com.aicode.ai_code_reviewer.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiController {
    @Autowired
    private AiService aiService;
    @PostMapping("/review")
    public CodeResponse review(@RequestBody CodeRequest codeRequest) {
        return new CodeResponse(aiService.reviewCode(codeRequest.getCode(), codeRequest.getLanguage()));
    }

}
