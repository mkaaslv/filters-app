package com.mka.filters.backend.controllers;

import java.net.URI;
import java.util.List;

import com.mka.filters.backend.dtos.filter.FilterCreationDto;
import com.mka.filters.backend.dtos.filter.FilterDto;
import com.mka.filters.backend.exceptions.NotFoundException;
import com.mka.filters.backend.services.FilterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<FilterDto> updateFilter(@RequestBody FilterDto filterDto) {
        FilterDto updatedFilter = filterService.updateFilter(filterDto);
        return ResponseEntity.ok(updatedFilter);
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

    @GetMapping(value ="/filters", params = { "page", "size" })
    public List<FilterDto> findPaginated(@RequestParam("page") int page, 
                                        @RequestParam("size") int size,
                                        UriComponentsBuilder uriBuilder,
                                        HttpServletResponse response) {
        Page<FilterDto> resultPage = filterService.findPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new NotFoundException(null, null);
        }
        // eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Foo>(
        //   FilterDto.class, uriBuilder, response, page, resultPage.getTotalPages(), size));
    
        return resultPage.getContent();
    }
}
