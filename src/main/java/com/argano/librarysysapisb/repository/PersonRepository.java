package com.argano.librarysysapisb.repository;

import com.argano.librarysysapisb.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // Here, you can define custom query methods if needed, for example:
    // Optional<Person> findByName(String name);
}
