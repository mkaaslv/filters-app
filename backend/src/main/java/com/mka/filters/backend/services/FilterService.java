package com.mka.filters.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mka.filters.backend.dtos.filter.FilterCreationDto;
import com.mka.filters.backend.dtos.filter.FilterDto;
import com.mka.filters.backend.entities.Criteria;
import com.mka.filters.backend.entities.Filter;
import com.mka.filters.backend.exceptions.NotFoundException;
import com.mka.filters.backend.mappers.FilterMapper;
import com.mka.filters.backend.repositories.CriteriaRepository;
import com.mka.filters.backend.repositories.FilterRepository;

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

    public List<FilterDto> allFilters() {
        List<Filter> allFilters = filterDao.findAll();
        return filterMapper.toDtoList(allFilters);
    }

    public FilterDto getFilter(Long id) {
        Filter filter = filterDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Filter not found with id %o", id), HttpStatus.NOT_FOUND));

        return filterMapper.toDto(filter);
    }

    public FilterDto addFilter(FilterCreationDto newFilterDto) {
        Filter filter = filterMapper.toEntity(newFilterDto);
        List<Criteria> criteriaList = filter.getCriterias();

        filter.setCriterias(new ArrayList<>());
        Filter createdFilter = filterDao.save(filter);
        
        List<Criteria> createdCriterias = new ArrayList<>();
        for (Criteria criteria : criteriaList) {
            criteria.setFilter(createdFilter);
            Criteria createdCriteria = criteriaDao.save(criteria);
            createdCriterias.add(createdCriteria);
        }

        createdFilter.setCriterias(createdCriterias);
        return filterMapper.toDto(createdFilter);
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
