package com.mka.filters.backend.repositories;

import com.mka.filters.backend.entities.Filter;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FilterRepository extends JpaRepository<Filter, Long> {
    
}
