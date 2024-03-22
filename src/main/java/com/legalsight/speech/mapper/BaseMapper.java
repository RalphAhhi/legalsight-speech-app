package com.legalsight.speech.mapper;


import com.legalsight.speech.entity.BaseEntity;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<D, E extends BaseEntity> {

    D toDto(E e);

    default List<D> toDto(List<E> e){
        return e.stream().map(this::toDto).toList();
    }

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    E toEntity(D d);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    void updateEntity(@MappingTarget E e,D d);

    default List<E> toEntities(List<D> d){
        return d.stream().map(this::toEntity).toList();
    }
}