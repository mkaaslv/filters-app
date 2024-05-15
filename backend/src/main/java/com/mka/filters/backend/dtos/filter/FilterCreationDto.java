package com.mka.filters.backend.dtos.filter;

import java.util.List;

import com.mka.filters.backend.dtos.criteria.CriteriaDto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FilterCreationDto {

    @Nonnull
    private String name;

    private String selection;

    private List<CriteriaDto> criterias;
}