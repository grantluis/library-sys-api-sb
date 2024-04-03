package com.argano.librarysysapisb.repository;

import com.argano.librarysysapisb.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    // You can define custom query methods here, for example:
    // Optional<Librarian> findByEmailId(String emailId);
}
