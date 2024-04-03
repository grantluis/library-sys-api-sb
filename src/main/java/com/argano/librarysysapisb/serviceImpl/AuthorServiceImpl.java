package com.argano.librarysysapisb.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.argano.librarysysapisb.dto.AuthorDto;
import com.argano.librarysysapisb.entity.Author;
import com.argano.librarysysapisb.repository.AuthorRepository;
import com.argano.librarysysapisb.service.AuthorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());

        Author savedAuthor = authorRepository.save(author);
        return convertToDto(savedAuthor);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        // Retrieve all authors from the database
        List<Author> authors = authorRepository.findAll();

        // Convert each author to an AuthorDto object
        List<AuthorDto> authorDtos = authors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // Return the list of AuthorDto objects
        return authorDtos;
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        // Retrieve an author by id from the repository
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));

        // Convert the author entity to a DTO
        return convertToDto(author);
    }

    @Override
    @Transactional
    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        // Retrieve the existing Author entity
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));

        // Update the entity's properties
        existingAuthor.setFirstName(authorDto.getFirstName());
        existingAuthor.setLastName(authorDto.getLastName());

        // Save the updated entity
        Author updatedAuthor = authorRepository.save(existingAuthor);

        // Convert and return the updated entity as a DTO
        return convertToDto(updatedAuthor);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        // Check if the author exists. This throws an EntityNotFoundException if not
        // found.
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));

        // Delete the author
        authorRepository.delete(author);
    }

    private AuthorDto convertToDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        return dto;
    }
}
