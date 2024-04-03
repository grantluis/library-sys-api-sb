package com.argano.librarysysapisb.service;

import com.argano.librarysysapisb.dto.BookTrackDto;
import java.util.List;

public interface BookTrackService {

    BookTrackDto createBookTrack(BookTrackDto bookTrackDto);

    List<BookTrackDto> getAllBookTracks();

    BookTrackDto getBookTrackById(Long id);

    BookTrackDto updateBookTrack(Long id, BookTrackDto bookTrackDto);

    void deleteBookTrack(Long id);
}
