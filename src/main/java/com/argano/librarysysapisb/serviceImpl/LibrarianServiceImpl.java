package com.argano.librarysysapisb.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.argano.librarysysapisb.dto.LibrarianDto;
import com.argano.librarysysapisb.entity.Librarian;
import com.argano.librarysysapisb.repository.LibrarianRepository;
import com.argano.librarysysapisb.service.LibrarianService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LibrarianServiceImpl implements LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LibrarianServiceImpl(LibrarianRepository librarianRepository, PasswordEncoder passwordEncoder) {
        this.librarianRepository = librarianRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public LibrarianDto createLibrarian(LibrarianDto librarianDto) {
        Librarian librarian = new Librarian();
        librarian.setName(librarianDto.getName());
        librarian.setEmailId(librarianDto.getEmailId());
        // Encrypt the password before saving. It's safe here because we're ensuring
        // the DTO's password field is only populated during the creation or updating,
        // and not when fetching or listing librarians.
        librarian.setPassword(passwordEncoder.encode(librarianDto.getPassword()));

        Librarian savedLibrarian = librarianRepository.save(librarian);

        // Convert and return the saved entity, excluding the password
        return convertToDto(savedLibrarian);
    }

    @Override
    public List<LibrarianDto> getAllLibrarians() {
        // Fetch all librarian entities from the database
        List<Librarian> librarians = librarianRepository.findAll();

        // Convert each librarian entity to a LibrarianDto
        List<LibrarianDto> librarianDtos = librarians.stream().map(this::convertToDto).collect(Collectors.toList());

        // Return the list of LibrarianDto objects
        return librarianDtos;
    }

    @Override
    public LibrarianDto getLibrarianById(Long id) {
        // Retrieve the librarian entity by ID from the repository
        Librarian librarian = librarianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found with id: " + id));

        // Convert the retrieved librarian entity to a DTO and return it
        return convertToDto(librarian);
    }

    @Override
    @Transactional
    public LibrarianDto updateLibrarian(Long id, LibrarianDto librarianDto) {
        // Fetch the existing librarian from the database
        Librarian existingLibrarian = librarianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found with id: " + id));

        // Update the librarian's details
        existingLibrarian.setName(librarianDto.getName());
        existingLibrarian.setEmailId(librarianDto.getEmailId());

        // Check if password needs to be updated
        if (librarianDto.getPassword() != null && !librarianDto.getPassword().isBlank()) {
            existingLibrarian.setPassword(passwordEncoder.encode(librarianDto.getPassword()));
        }

        // Save the updated librarian
        Librarian updatedLibrarian = librarianRepository.save(existingLibrarian);

        // Convert and return the updated librarian, excluding the password
        return convertToDto(updatedLibrarian);
    }

    @Override
    @Transactional
    public void deleteLibrarian(Long id) {
        // Check if the librarian exists before attempting to delete to provide a
        // clearer error response
        if (!librarianRepository.existsById(id)) {
            throw new EntityNotFoundException("Librarian not found with id: " + id);
        }

        librarianRepository.deleteById(id);
    }

    private LibrarianDto convertToDto(Librarian librarian) {
        LibrarianDto dto = new LibrarianDto();
        dto.setId(librarian.getId());
        dto.setName(librarian.getName());
        dto.setEmailId(librarian.getEmailId());
        // Intentionally not setting the password for security reasons
        return dto;
    }
}
