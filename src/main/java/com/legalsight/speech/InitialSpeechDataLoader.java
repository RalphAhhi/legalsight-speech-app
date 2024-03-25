package com.legalsight.speech;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.service.SpeechService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "legal-speech.initial-data.enabled", havingValue = "true")
public class InitialSpeechDataLoader implements CommandLineRunner {

	private final SpeechService speechService;
	private final ObjectMapper objectMapper;
	@Override
	public void run(String... args) {
		List<SpeechDto> speechDtos = getDataFromFile();
		if(CollectionUtils.isEmpty(speechDtos)){
			return;
		}

		speechDtos.forEach(speechService::create);
	}

	private List<SpeechDto> getDataFromFile() {
		try {
			ClassPathResource path = new ClassPathResource("test-data/speech.json");
			return this.objectMapper.readValue(path.getFile(), new TypeReference<>() {});
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}