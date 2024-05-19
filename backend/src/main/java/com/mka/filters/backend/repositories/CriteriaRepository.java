package com.mka.filters.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mka.filters.backend.entities.Criteria;


public interface CriteriaRepository extends JpaRepository<Criteria, Long>, CustomCriteriaRepository {
    
}

