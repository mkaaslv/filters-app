package com.mka.filters.backend.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mka.filters.backend.dtos.filter.FilterCreationDto;
import com.mka.filters.backend.dtos.filter.FilterDto;
import com.mka.filters.backend.services.FilterService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FilterController {

    @Autowired
    private final FilterService filterService;


    @GetMapping("/filters")
    public ResponseEntity<List<FilterDto>> allFilters() {
        try{
            return ResponseEntity.ok(filterService.allFilters());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/filters/{id}")
    public ResponseEntity<FilterDto> getFilter(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(filterService.getFilter(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/filters")
    public ResponseEntity<FilterDto> addFilter(@RequestBody FilterCreationDto filterDto) {
        try {
            FilterDto addedFilter = filterService.addFilter(filterDto);
            return ResponseEntity.created(URI.create("/filters/" + addedFilter.getId())).body(addedFilter);
        } catch (IllegalStateException | IllegalArgumentException i) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/filters/{id}")
    public ResponseEntity<FilterDto> updateFilter(@PathVariable("id") Long id, @RequestBody FilterDto filterDto) {
        try {
            FilterDto updatedFilter = filterService.updateFilter(id, filterDto);
            return ResponseEntity.ok(updatedFilter);
        } catch (IllegalStateException | IllegalArgumentException i) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/filters/{id}")
    public ResponseEntity<?> deleteFilter(@PathVariable("id") Long id) {
        try {
            filterService.deleteFilter(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
