package com.argano.librarysysapisb.controller;

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

import com.argano.librarysysapisb.dto.BookTrackDto;
import com.argano.librarysysapisb.service.BookTrackService;

@RestController
@RequestMapping("/api/booktracks")
public class BookTrackController {

    private final BookTrackService bookTrackService;

    @Autowired
    public BookTrackController(BookTrackService bookTrackService) {
        this.bookTrackService = bookTrackService;
    }

    @PostMapping
    public ResponseEntity<BookTrackDto> createBookTrack(@RequestBody BookTrackDto bookTrackDto) {
        BookTrackDto savedBookTrack = bookTrackService.createBookTrack(bookTrackDto);
        return new ResponseEntity<>(savedBookTrack, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookTrackDto>> getAllBookTracks() {
        List<BookTrackDto> bookTracks = bookTrackService.getAllBookTracks();
        return ResponseEntity.ok(bookTracks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookTrackDto> getBookTrackById(@PathVariable Long id) {
        BookTrackDto bookTrack = bookTrackService.getBookTrackById(id);
        return ResponseEntity.ok(bookTrack);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookTrackDto> updateBookTrack(@PathVariable Long id, @RequestBody BookTrackDto bookTrackDto) {
        BookTrackDto updatedBookTrack = bookTrackService.updateBookTrack(id, bookTrackDto);
        return ResponseEntity.ok(updatedBookTrack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookTrack(@PathVariable Long id) {
        bookTrackService.deleteBookTrack(id);
        return ResponseEntity.noContent().build();
    }
}
