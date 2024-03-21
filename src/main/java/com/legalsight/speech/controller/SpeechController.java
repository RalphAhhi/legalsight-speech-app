package com.legalsight.speech.controller;

import com.legalsight.speech.dto.SpeechDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/speech")
public interface SpeechController {
    @GetMapping("/{id}")
    SpeechDto findById(@PathVariable String id);

}
