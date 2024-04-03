package com.argano.librarysysapisb.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.argano.librarysysapisb.dto.BookDto;
import com.argano.librarysysapisb.entity.Book;
import com.argano.librarysysapisb.repository.BookRepository;
import com.argano.librarysysapisb.service.BookService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public BookDto createBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setPageCount(bookDto.getPageCount());
        book.setPublishDate(bookDto.getPublishDate());
        book.setAvailableStock(bookDto.getAvailableStock());

        Book savedBook = bookRepository.save(book);

        return convertToDto(savedBook);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return convertToDto(book);
    }

    @Override
    @Transactional
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book bookToUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        bookToUpdate.setTitle(bookDto.getTitle());
        bookToUpdate.setAuthor(bookDto.getAuthor());
        bookToUpdate.setDescription(bookDto.getDescription());
        bookToUpdate.setPageCount(bookDto.getPageCount());
        bookToUpdate.setPublishDate(bookDto.getPublishDate());
        bookToUpdate.setAvailableStock(bookDto.getAvailableStock());

        Book updatedBook = bookRepository.save(bookToUpdate);
        return convertToDto(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
    }

    private BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setDescription(book.getDescription());
        bookDto.setPageCount(book.getPageCount());
        bookDto.setPublishDate(book.getPublishDate());
        bookDto.setAvailableStock(book.getAvailableStock());
        return bookDto;
    }

}
