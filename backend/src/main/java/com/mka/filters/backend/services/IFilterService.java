package com.mka.filters.backend.services;

import java.util.List;

import com.mka.filters.backend.dtos.filter.FilterCreationDto;
import com.mka.filters.backend.dtos.filter.FilterDto;


public interface IFilterService {

    public List<FilterDto> allFilters();

    public FilterDto getFilter(Long id);

    public FilterDto addFilter(FilterCreationDto newFilterDto);

    public FilterDto updateFilter(Long id, FilterDto filterDto);

    public void deleteFilter(Long id);
}
