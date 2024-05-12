package com.mka.filters.backend.mappers;

import java.util.List;

import com.mka.filters.backend.dtos.filter.FilterCreationDto;
import com.mka.filters.backend.dtos.filter.FilterDto;
import com.mka.filters.backend.entities.Filter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Mapper for the entity {@link Filter} and its DTO {@link FilterDto}.
 */
@Mapper(componentModel = "spring")
public interface FilterMapper extends IBaseMapper<Filter, FilterDto> {

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    // @Mapping(target = "criterias", ignore = true)
    Filter toFilter(FilterDto filterDto);

    @Mapping(target = "createdDate", ignore = true)
    // @Mapping(target = "criterias", ignore = true)
    Filter toFilter(FilterCreationDto newFilterDto);

    @Mapping(target = "criterias", ignore = true)
    FilterDto toFilterDto(Filter filter);

    List<FilterDto> toFilterDtos(List<Filter> filters);
}
