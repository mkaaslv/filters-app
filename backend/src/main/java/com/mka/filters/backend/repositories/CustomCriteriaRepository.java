package com.mka.filters.backend.repositories;

import java.util.List;

import com.mka.filters.backend.entities.Criteria;
import com.mka.filters.backend.entities.Filter;

public interface CustomCriteriaRepository {
    List<Criteria> findByFilter(Filter filter);
}
