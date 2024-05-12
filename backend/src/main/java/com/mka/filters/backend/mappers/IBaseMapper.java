package com.mka.filters.backend.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

public interface IBaseMapper<MDL, DTO> {

    DTO toDto(MDL model);
    MDL toModel(DTO dto);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<DTO> toDtoList(List<MDL> dtoList);
}
