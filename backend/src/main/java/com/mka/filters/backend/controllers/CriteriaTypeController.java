package com.mka.filters.backend.controllers;

import com.mka.filters.backend.enums.CriteriaType;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/criteria-types")
public class CriteriaTypeController {

    @GetMapping
    public CriteriaType[] getCriteriaTypes() {
        return CriteriaType.values();
    }
    
}
