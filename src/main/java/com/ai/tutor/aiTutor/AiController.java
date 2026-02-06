package com.ai.tutor.aiTutor;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {
	
	@Autowired
    private OllamaChatModel ollamaChatModel;
    
    @PostMapping("/ai")
    public String generate(@RequestBody String message) {
    	ChatResponse call = ollamaChatModel.call(
    		    new Prompt(
    		        message,
    		        OllamaChatOptions.builder()
    		            .model("deepseek-v3.1:671b-cloud")
    		            .temperature(0.4)
    		            .build()
    		    ));
    	
//    	String call = ollamaChatModel.call("Generate the names of 5 famous pirates.");
    	return call.getResult().getOutput().getText();
    }
}
