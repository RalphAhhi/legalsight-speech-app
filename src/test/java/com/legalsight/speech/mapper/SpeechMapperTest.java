package com.legalsight.speech.mapper;

import com.legalsight.speech.dto.SpeechDto;
import com.legalsight.speech.entity.SpeechEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SpeechMapperTest {

    private final SpeechMapper speechMapper = Mappers.getMapper(SpeechMapper.class);

    @Test
    void givenEntity_whenMapToDto_thenFieldsShouldBeProperlyMapToDto(){
        SpeechEntity entity = new SpeechEntity();
        entity.setContent("test-content");
        entity.setVersion(1);
        entity.setId(UUID.randomUUID().toString());
        entity.setAuthor("test-author");
        entity.setCreatedDate(Instant.now());
        entity.setUpdatedDate(Instant.now());

        SpeechDto dto = speechMapper.toDto(entity);
        assertThat(dto)
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void givenDto_whenMapToEntity_thenFieldsShouldBeProperlyMapToEntity(){
        SpeechDto dto = new SpeechDto();
        dto.setContent("test-content");
        dto.setId(UUID.randomUUID().toString());
        dto.setAuthor("test-author");
        dto.setCreatedDate(Instant.now());
        dto.setUpdatedDate(Instant.now());

        SpeechEntity entity = speechMapper.toEntity(dto);
        assertThat(entity)
                .usingRecursiveComparison()
                .ignoringFields("id","createdDate","updatedDate","version")
                .isEqualTo(dto);
    }
}
