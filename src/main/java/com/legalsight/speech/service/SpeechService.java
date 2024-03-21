package com.legalsight.speech.service;

import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.entity.SpeechEntity;
import com.legalsight.speech.exception.NoDataFoundException;
import com.legalsight.speech.mapper.BaseMapper;
import com.legalsight.speech.repository.SpeechRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class SpeechService {


    private final SpeechRepository speechRepository;
    private final BaseMapper<SpeechDto, SpeechEntity> speechMapper;
    public SpeechDto findById(String id) {
        SpeechEntity entity = speechRepository.findById(id).orElseThrow(()-> new NoDataFoundException("Id not found"));
        return speechMapper.toDto(entity);
    }
}
