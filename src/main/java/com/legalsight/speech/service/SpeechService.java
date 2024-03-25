package com.legalsight.speech.service;

import com.legalsight.speech.dto.ResultSetResponse;
import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.dto.SpeechFilterDto;
import com.legalsight.speech.entity.SpeechEntity;
import com.legalsight.speech.exception.NoDataFoundException;
import com.legalsight.speech.mapper.BaseMapper;
import com.legalsight.speech.repository.SpeechRepository;
import com.legalsight.speech.specification.SpeechSpecification;
import com.legalsight.speech.utils.PageRequestUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class SpeechService {

    private final SpeechRepository speechRepository;
    private final BaseMapper<SpeechDto, SpeechEntity> speechMapper;
    public SpeechDto findById(Long id) {
        SpeechEntity entity = findByIdOrThrowException(id);
        return speechMapper.toDto(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        SpeechEntity entity = findByIdOrThrowException(id);
        speechRepository.delete(entity);
    }

    private SpeechEntity findByIdOrThrowException(Long id){
        return speechRepository.findById(id).orElseThrow(()-> new NoDataFoundException(String.format("Speech with id %s not found", id)));
    }

    @Transactional
    public SpeechDto create(SpeechDto speechDto) {
        return speechMapper.toDto(speechRepository.save(speechMapper.toEntity(speechDto)));
    }

    public ResultSetResponse<SpeechDto> list(SpeechFilterDto filterDto) {
        SpeechSpecification speechSpecification = new SpeechSpecification(filterDto);
        Page<SpeechEntity> speechEntityPage = speechRepository.findAll(speechSpecification, PageRequestUtils.createPageRequest(filterDto.getPage(),filterDto.getPerPage(),filterDto.getSortBy(), filterDto.getSortOrder()));
        return new ResultSetResponse<>(speechMapper.toDto(speechEntityPage.getContent()),filterDto.getPage(), filterDto.getPerPage(),speechEntityPage.getTotalElements());
    }

    @Transactional
    public SpeechDto update(Long id, SpeechDto speechDto) {
        SpeechEntity speechEntity = findByIdOrThrowException(id);
        speechMapper.updateEntity(speechEntity, speechDto);
        return speechMapper.toDto(speechRepository.save(speechEntity));
    }
}