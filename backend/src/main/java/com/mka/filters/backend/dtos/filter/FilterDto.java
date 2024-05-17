package com.mka.filters.backend.dtos.filter;

import java.util.Date;
import java.util.List;

import com.mka.filters.backend.dtos.criteria.CriteriaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class FilterDto {

    private Long id;
    
    private String name;

    private String selection;

    private List<CriteriaDto> criterias;

    private Date modifiedDate;
}
