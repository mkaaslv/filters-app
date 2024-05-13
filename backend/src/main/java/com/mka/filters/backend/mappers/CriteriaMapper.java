package com.mka.filters.backend.mappers;

// import java.util.List;

import com.mka.filters.backend.dtos.criteria.CriteriaCreationDto;
import com.mka.filters.backend.dtos.criteria.CriteriaDto;
import com.mka.filters.backend.entities.Criteria;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Mapper for the entity {@link Criteria} and its DTO {@link CriteriaDto}.
 */
@Mapper(componentModel = "spring", uses = { FilterMapper.class })
public interface CriteriaMapper extends IBaseMapper<Criteria, CriteriaDto> {

    @Override
    @Mapping(target = "filter", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Criteria toEntity(CriteriaDto criteriaDto);

    @Mapping(target = "filter", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Criteria toEntity(CriteriaCreationDto criteriaDto);

    @Override
    @Mapping(target = "filter", ignore = true)
    CriteriaDto toDto(Criteria criteria);
}
