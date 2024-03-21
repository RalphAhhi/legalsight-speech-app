package com.legalsight.speech.controller;

import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.service.SpeechService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SpeechControllerImpl implements SpeechController {

    private final SpeechService speechService;
    @GetMapping("/{id}")
    @ResponseBody
    public SpeechDto findById(@PathVariable String id){
        return speechService.findById(id);
    }

}
