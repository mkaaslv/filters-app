package com.mka.filters.backend.mappers;

// import java.util.List;

import com.mka.filters.backend.dtos.criteria.CriteriaCreationDto;
import com.mka.filters.backend.dtos.criteria.CriteriaDto;
import com.mka.filters.backend.entities.Criteria;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;


/**
 * Mapper for the entity {@link Criteria} and its DTO {@link CriteriaDto}.
 */
@Mapper(componentModel = "spring")
public interface CriteriaMapper extends IBaseMapper<Criteria, CriteriaDto> {

    @Mapping(target = "filter", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Criteria toCriteria(CriteriaDto criteriaDto);

    @Override
    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    @Mapping(target = "businessUnitHistoryRecords", ignore = true)
    @Mapping(target = "costCenterHistoryRecordRecords", ignore = true)
    @Mapping(target = "employeeHistoryRecords", ignore = true)
    @Mapping(target = "groupManagementHistoryRecords", ignore = true)
    @Mapping(target = "workingPositionHistoryRecords", ignore = true)
//         @Mapping(source = "businessUnit.id", target = "businessUnitId")
//        @Mapping(source = "costCenter.id", target = "costCenterId")
//        @Mapping(source = "solidLineManager.id", target = "solidLineManagerId")
    @Mapping(target = "businessUnitHistoryRecords", ignore = true)
    CriteriaDto toDto(Criteria criteria);

    @Mapping(target = "filter", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Criteria toCriteria(CriteriaCreationDto criteriaDto);

    @Mapping(target = "filter", ignore = true)
    CriteriaDto toCriteriaDto(Criteria criteria);

    // List<CriteriaDto> toCriteriaDtos(List<Criteria> criterias);
}
