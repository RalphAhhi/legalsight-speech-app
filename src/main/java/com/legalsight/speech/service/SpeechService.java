package com.legalsight.speech.service;

import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.entity.SpeechEntity;
import com.legalsight.speech.exception.NoDataFoundException;
import com.legalsight.speech.mapper.BaseMapper;
import com.legalsight.speech.repository.SpeechRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class SpeechService {

    private final SpeechRepository speechRepository;
    private final BaseMapper<SpeechDto, SpeechEntity> speechMapper;
    public SpeechDto findById(String id) {
        SpeechEntity entity = findByIdOrThrowException(id);
        return speechMapper.toDto(entity);
    }

    @Transactional
    public void deleteById(String id) {
        SpeechEntity entity = findByIdOrThrowException(id);
        speechRepository.delete(entity);
    }

    private SpeechEntity findByIdOrThrowException(String id){
        return speechRepository.findById(id).orElseThrow(()-> new NoDataFoundException(String.format("Speech with id %s not found", id)));
    }

    @Transactional
    public SpeechDto create(SpeechDto speechDto) {
        return speechMapper.toDto(speechRepository.save(speechMapper.toEntity(speechDto)));
    }

    public List<SpeechDto> list() {
        return speechMapper.toDto(speechRepository.findAll());
    }

    @Transactional
    public SpeechDto update(String id, SpeechDto speechDto) {
        SpeechEntity speechEntity = findByIdOrThrowException(id);
        speechMapper.updateEntity(speechEntity, speechDto);
        return speechMapper.toDto(speechRepository.save(speechEntity));
    }
}