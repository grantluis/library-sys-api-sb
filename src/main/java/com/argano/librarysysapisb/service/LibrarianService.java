package com.argano.librarysysapisb.service;

import com.argano.librarysysapisb.dto.LibrarianDto;
import java.util.List;

public interface LibrarianService {

    LibrarianDto createLibrarian(LibrarianDto librarianDto);

    List<LibrarianDto> getAllLibrarians();

    LibrarianDto getLibrarianById(Long id);

    LibrarianDto updateLibrarian(Long id, LibrarianDto librarianDto);

    void deleteLibrarian(Long id);
}
