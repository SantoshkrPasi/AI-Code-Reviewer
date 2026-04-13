package com.aicode.ai_code_reviewer.controller;

import com.aicode.ai_code_reviewer.dto.CodeRequest;
import com.aicode.ai_code_reviewer.dto.CodeResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiController {
    @PostMapping("/review")
    public CodeResponse review(@RequestBody CodeRequest codeRequest) {
        return new CodeResponse("Your code is not correct in the right way"  + codeRequest.getLanguage());
    }

}
