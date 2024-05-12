package com.mka.filters.backend.dtos.criteria;

import com.mka.filters.backend.dtos.filter.FilterDto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CriteriaCreationDto {

    private Long id;

    @ManyToOne()
    @JoinColumn(name = "filter_id")
    private FilterDto filter;

    private int criteriaType;

    private String operator;

    private String value;
}
