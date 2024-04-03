package com.argano.librarysysapisb.service;

import com.argano.librarysysapisb.dto.AuthorDto;
import java.util.List;

public interface AuthorService {

    AuthorDto createAuthor(AuthorDto authorDto);

    List<AuthorDto> getAllAuthors();

    AuthorDto getAuthorById(Long id);

    AuthorDto updateAuthor(Long id, AuthorDto authorDto);

    void deleteAuthor(Long id);
}
