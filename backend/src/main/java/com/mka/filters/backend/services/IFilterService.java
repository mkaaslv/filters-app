package com.mka.filters.backend.services;

import java.util.List;

import com.mka.filters.backend.dtos.filter.FilterCreationDto;
import com.mka.filters.backend.dtos.filter.FilterDto;

import org.springframework.data.domain.Page;


public interface IFilterService {

    public List<FilterDto> allFilters();

    public FilterDto getFilter(Long id);

    public FilterDto addFilter(FilterCreationDto newFilterDto);

    public FilterDto updateFilter(FilterDto filterDto);

    public void deleteFilter(Long id);

    public Page<FilterDto> findPaginated(int page, int size);
}
