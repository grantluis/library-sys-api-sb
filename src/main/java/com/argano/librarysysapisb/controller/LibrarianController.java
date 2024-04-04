package com.argano.librarysysapisb.controller;

import com.argano.librarysysapisb.dto.LibrarianDto;
import com.argano.librarysysapisb.service.LibrarianService;

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

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController {

    private final LibrarianService librarianService;

    @Autowired
    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @PostMapping
    public ResponseEntity<LibrarianDto> createLibrarian(@RequestBody LibrarianDto librarianDto) {
        LibrarianDto savedLibrarian = librarianService.createLibrarian(librarianDto);
        // Exclude password from the response for security reasons
        savedLibrarian.setPassword(null);
        return new ResponseEntity<>(savedLibrarian, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LibrarianDto>> getAllLibrarians() {
        List<LibrarianDto> librarians = librarianService.getAllLibrarians();
        return new ResponseEntity<>(librarians, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrarianDto> getLibrarianById(@PathVariable Long id) {
        LibrarianDto librarianDto = librarianService.getLibrarianById(id);
        // Exclude password from the response for security reasons
        librarianDto.setPassword(null);
        return new ResponseEntity<>(librarianDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibrarianDto> updateLibrarian(@PathVariable Long id, @RequestBody LibrarianDto librarianDto) {
        // Ensure that the librarianDto ID matches the path variable ID
        librarianDto.setId(id);
        LibrarianDto updatedLibrarian = librarianService.updateLibrarian(id, librarianDto);
        // Exclude the password from the response for security reasons
        updatedLibrarian.setPassword(null);
        return new ResponseEntity<>(updatedLibrarian, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLibrarian(@PathVariable Long id) {
        librarianService.deleteLibrarian(id);
        return ResponseEntity.noContent().build();
    }
}
