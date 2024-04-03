package com.argano.librarysysapisb.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.argano.librarysysapisb.dto.BookTrackDto;
import com.argano.librarysysapisb.entity.Book;
import com.argano.librarysysapisb.entity.BookTrack;
import com.argano.librarysysapisb.entity.Person;
import com.argano.librarysysapisb.repository.BookRepository;
import com.argano.librarysysapisb.repository.BookTrackRepository;
import com.argano.librarysysapisb.repository.PersonRepository;
import com.argano.librarysysapisb.service.BookTrackService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class BookTrackServiceImpl implements BookTrackService {

    private final BookRepository bookRepository;

    private final PersonRepository personRepository;

    private final BookTrackRepository bookTrackRepository;

    @Autowired
    public BookTrackServiceImpl(BookRepository bookRepository, PersonRepository personRepository,
            BookTrackRepository bookTrackRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
        this.bookTrackRepository = bookTrackRepository;
    }

    @Override
    @Transactional
    public BookTrackDto createBookTrack(BookTrackDto bookTrackDto) {
        BookTrack bookTrack = new BookTrack();
        bookTrack.setStartDate(bookTrackDto.getStartDate());
        bookTrack.setExpectedReturnDate(bookTrackDto.getExpectedReturnDate());
        bookTrack.setActualReturnDate(bookTrackDto.getActualReturnDate());

        Book book = bookRepository.findById(bookTrackDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookTrackDto.getBookId()));
        bookTrack.setBook(book);

        Person person = personRepository.findById(bookTrackDto.getPersonId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Person not found with id: " + bookTrackDto.getPersonId()));
        bookTrack.setPerson(person);

        BookTrack savedBookTrack = bookTrackRepository.save(bookTrack);

        return convertToDto(savedBookTrack);
    }

    @Override
    public List<BookTrackDto> getAllBookTracks() {
        // Fetch all BookTrack entities from the database
        List<BookTrack> bookTracks = bookTrackRepository.findAll();

        // Convert each entity to a DTO
        List<BookTrackDto> bookTrackDtos = bookTracks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // Return the list of DTOs
        return bookTrackDtos;
    }

    @Override
    public BookTrackDto getBookTrackById(Long id) {
        // Retrieve the bookTrack entity by id
        BookTrack bookTrack = bookTrackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BookTrack not found with id: " + id));

        // Convert the entity to DTO
        return convertToDto(bookTrack);
    }

    @Override
    @Transactional
    public BookTrackDto updateBookTrack(Long id, BookTrackDto bookTrackDto) {
        // Retrieve the existing BookTrack entity
        BookTrack existingBookTrack = bookTrackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BookTrack not found with id: " + id));

        // Update the entity fields
        existingBookTrack.setStartDate(bookTrackDto.getStartDate());
        existingBookTrack.setExpectedReturnDate(bookTrackDto.getExpectedReturnDate());
        existingBookTrack.setActualReturnDate(bookTrackDto.getActualReturnDate());

        // Fetch and set the associated Book and Person by IDs provided in DTO
        Book book = bookRepository.findById(bookTrackDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookTrackDto.getBookId()));
        existingBookTrack.setBook(book);

        Person person = personRepository.findById(bookTrackDto.getPersonId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Person not found with id: " + bookTrackDto.getPersonId()));
        existingBookTrack.setPerson(person);

        // Save the updated entity
        BookTrack updatedBookTrack = bookTrackRepository.save(existingBookTrack);

        // Convert the updated entity to DTO and return it
        return convertToDto(updatedBookTrack);
    }

    @Override
    @Transactional
    public void deleteBookTrack(Long id) {
        // Check if the BookTrack entity exists
        BookTrack bookTrack = bookTrackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BookTrack not found with id: " + id));

        // Delete the bookTrack entity
        bookTrackRepository.delete(bookTrack);
    }

    private BookTrackDto convertToDto(BookTrack bookTrack) {
        BookTrackDto bookTrackDto = new BookTrackDto();
        bookTrackDto.setId(bookTrack.getId());
        bookTrackDto.setBookId(bookTrack.getBook().getId());
        bookTrackDto.setStartDate(bookTrack.getStartDate());
        bookTrackDto.setExpectedReturnDate(bookTrack.getExpectedReturnDate());
        bookTrackDto.setActualReturnDate(bookTrack.getActualReturnDate());
        bookTrackDto.setPersonId(bookTrack.getPerson().getId());
        return bookTrackDto;
    }

}
