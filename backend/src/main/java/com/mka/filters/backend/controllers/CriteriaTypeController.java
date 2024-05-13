package com.mka.filters.backend.controllers;

import com.mka.filters.backend.enums.CriteriaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/criteria-types")
public class CriteriaTypeController {

    @GetMapping
    public CriteriaType[] getCriteriaTypes() {
        return CriteriaType.values();
    }
    
}
