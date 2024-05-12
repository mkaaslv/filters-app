package com.mka.filters.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// import com.mka.filters.backend.dtos.criteria.CriteriaDto;
import com.mka.filters.backend.dtos.filter.FilterCreationDto;
import com.mka.filters.backend.dtos.filter.FilterDto;
import com.mka.filters.backend.entities.Criteria;
import com.mka.filters.backend.entities.Filter;
import com.mka.filters.backend.exceptions.NotFoundException;
// import com.mka.filters.backend.mappers.CriteriaMapper;
import com.mka.filters.backend.mappers.FilterMapper;
import com.mka.filters.backend.repositories.CriteriaRepository;
import com.mka.filters.backend.repositories.FilterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilterService implements IFilterService {

    @Autowired
    FilterRepository          filterDao;
    @Autowired
    FilterMapper              filterMapper;
    @Autowired
    CriteriaRepository        criteriaDao;

    // @Autowired
    // CriteriaMapper            criteriaMapper;

    public List<FilterDto> allFilters() {
        List<Filter> allFilters = filterDao.findAll();
        // List<FilterDto> allFilterDtos = new ArrayList<>();

        // for (Filter filter : allFilters) {
        //     List<Criteria> criteriaList = criteriaDao.findByFilter(filter);
        //     List<CriteriaDto> criteriaDtos = criteriaMapper.toCriteriaDtos(criteriaList);
        //     FilterDto filterDto = filterMapper.toFilterDto(filter);
        //     filterDto.setCriterias(criteriaDtos);
        //     allFilterDtos.add(filterDto);
        // }

        return filterMapper.toFilterDtos(allFilters);
    }

    public FilterDto getFilter(Long id) {
        Filter filter = filterDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Filter not found with id %o", id), HttpStatus.NOT_FOUND));

        // List<Criteria> criteriaList = criteriaDao.findByFilter(filter);
        // List<CriteriaDto> criteriaDtos = criteriaMapper.toCriteriaDtos(criteriaList);
        FilterDto filterDto = filterMapper.toFilterDto(filter);
        // filterDto.setCriterias(criteriaDtos);

        return filterDto;
    }

    public FilterDto addFilter(FilterCreationDto newFilterDto) {
        Filter filter = filterMapper.toFilter(newFilterDto);

        // First save children
        List<Criteria> criteriaList = criteriaDao.findByFilter(filter);
        List<Criteria> createdCriterias = new ArrayList<>();
        for (Criteria criteria : criteriaList) {
            Criteria createdCriteria = criteriaDao.save(criteria);
            createdCriterias.add(createdCriteria);
        }

        // Save Filter
        filter.setCriterias(createdCriterias);
        Filter createdFilter = filterDao.save(filter);
    
        // Map enitities back to dtos
        // List<CriteriaDto> criteriaDtos = criteriaMapper.toCriteriaDtos(createdCriterias);
        FilterDto filterDto = filterMapper.toFilterDto(createdFilter);
        // filterDto.setCriterias(criteriaDtos);

        return filterDto;
    }

    public FilterDto updateFilter(FilterDto filterDto) {
        // TODO
        throw new UnsupportedOperationException();
    }

    public void deleteFilter(Long id) {
        Optional<Filter> filter = filterDao.findById(id);
        filter.ifPresentOrElse(f -> {
                try {
                    filterDao.delete(f);
                } catch (IllegalStateException e) {
                    throw new IllegalStateException("Could not delete filter!");
                }
            }, () -> {
                throw new NotFoundException(String.format("Filter not found with id %o", id), HttpStatus.NOT_FOUND);
            }
        );
    }
    
}
