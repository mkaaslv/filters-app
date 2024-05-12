package com.mka.filters.backend.repositories;

import com.mka.filters.backend.entities.Criteria;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CriteriaRepository extends JpaRepository<Criteria, Long>, CustomCriteriaRepository {
    
}

