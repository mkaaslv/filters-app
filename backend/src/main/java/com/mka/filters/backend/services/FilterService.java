package com.mka.filters.backend.services;

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
import com.mka.filters.backend.mappers.CriteriaMapper;
import com.mka.filters.backend.mappers.FilterMapper;
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
    CriteriaMapper            criteriaMapper;


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

        // Set filter refrence to criteria
        for (Criteria criteria : filter.getCriterias()) {
            criteria.setFilter(filter);
        }

        // Create filter (will cascade save the criteria)
        Filter createdFilter = filterDao.save(filter);
        return filterMapper.toDto(createdFilter);
    }

    public FilterDto updateFilter(Long id, FilterDto filterDto) {
        Filter filter = filterDao.findById(id).orElseThrow(()-> new IllegalStateException("No filter found for id: " + id));

        // Set filter refrence to criteria
        List<Criteria> updatedCriterias = criteriaMapper.toEntityList(filterDto.getCriterias());
        for (Criteria criteria : updatedCriterias) {
            criteria.setFilter(filter);
        }

        // Clear existing and set new criterias
        filter.getCriterias().clear();
        filter.getCriterias().addAll(updatedCriterias);

        // Update filter fields and save
        filterMapper.updateFilterFromDto(filterDto, filter);
        Filter updatedFilter = filterDao.save(filter);
        return filterMapper.toDto(updatedFilter);
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
