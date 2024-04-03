package com.argano.librarysysapisb.repository;

import com.argano.librarysysapisb.entity.BookTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTrackRepository extends JpaRepository<BookTrack, Long> {
    // Additional methods for specific queries can be declared here
}
