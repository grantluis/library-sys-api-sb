package com.argano.librarysysapisb.repository;

import com.argano.librarysysapisb.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // You can define custom query methods here, for example:
    // List<Author> findByLastName(String lastName);
}
