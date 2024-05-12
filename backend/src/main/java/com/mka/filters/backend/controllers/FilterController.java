package com.mka.filters.backend.controllers;

import java.net.URI;
import java.util.List;

import com.mka.filters.backend.dtos.filter.FilterCreationDto;
import com.mka.filters.backend.dtos.filter.FilterDto;
import com.mka.filters.backend.services.FilterServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/filters")
public class FilterController {

    private final FilterServiceImpl filterService;

    @GetMapping
    public ResponseEntity<List<FilterDto>> allFilters() {
        return ResponseEntity.ok(filterService.allFilters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilterDto> getFilter(@PathVariable("id") Long id) {
        return ResponseEntity.ok(filterService.getFilter(id));
    }

    @PostMapping
    public ResponseEntity<FilterDto> addFilter(@RequestBody FilterCreationDto filterDto) {
        FilterDto addedFilter = filterService.addFilter(filterDto);
        return ResponseEntity.created(URI.create("/filters/" + addedFilter.getId())).body(addedFilter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilterDto> updateFilter(@RequestBody FilterDto filterDto) {
        FilterDto updatedFilter = filterService.updateFilter(filterDto);
        return ResponseEntity.ok(updatedFilter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilter(@PathVariable("id") Long id) {
        filterService.deleteFilter(id);
        return ResponseEntity.noContent().build();
    }
}
