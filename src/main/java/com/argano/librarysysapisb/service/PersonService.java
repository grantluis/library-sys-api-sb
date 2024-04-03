package com.argano.librarysysapisb.service;

import com.argano.librarysysapisb.dto.PersonDto;
import java.util.List;

public interface PersonService {

    PersonDto createPerson(PersonDto personDto);

    List<PersonDto> getAllPersons();

    PersonDto getPersonById(Long id);

    PersonDto updatePerson(Long id, PersonDto personDto);

    void deletePerson(Long id);
}
