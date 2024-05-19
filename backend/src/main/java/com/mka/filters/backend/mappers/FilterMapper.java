package com.mka.filters.backend.mappers;

import com.mka.filters.backend.dtos.filter.FilterCreationDto;
import com.mka.filters.backend.dtos.filter.FilterDto;
import com.mka.filters.backend.entities.Filter;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


/**
 * Mapper for the entity {@link Filter} and its DTO {@link FilterDto}.
 */
@Mapper(componentModel = "spring", uses = { CriteriaMapper.class })
public interface FilterMapper extends IBaseMapper<Filter, FilterDto> {

    @Override
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Filter toEntity(FilterDto filterDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Filter toEntity(FilterCreationDto newFilterDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "criterias", ignore = true)
    void updateFilterFromDto(FilterDto dto, @MappingTarget Filter entity);
}
