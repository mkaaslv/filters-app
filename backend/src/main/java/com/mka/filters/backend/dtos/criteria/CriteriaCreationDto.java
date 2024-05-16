package com.mka.filters.backend.dtos.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CriteriaCreationDto {

    private int criteriaType;

    private String operator;

    private String value;
}
