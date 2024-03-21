package com.legalsight.speech.mapper;

import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.entity.SpeechEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SpeechMapper extends BaseMapper<SpeechDto, SpeechEntity> {

}
