package com.mka.filters.backend.mappers;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.NullValueMappingStrategy;

public interface IBaseMapper<MDL, DTO> {

    DTO toDto(MDL model);
    MDL toEntity(DTO dto);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<DTO> toDtoList(List<MDL> entityList);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<MDL> toEntityList(List<DTO> dtoList);
}
