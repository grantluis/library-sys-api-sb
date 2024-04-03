package com.argano.librarysysapisb.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.argano.librarysysapisb.dto.PersonDto;
import com.argano.librarysysapisb.entity.Person;
import com.argano.librarysysapisb.repository.PersonRepository;
import com.argano.librarysysapisb.service.PersonService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public PersonDto createPerson(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());
        person.setDob(personDto.getDob());
        person.setAddress(personDto.getAddress());

        // Save the person entity
        Person savedPerson = personRepository.save(person);

        // Convert the saved entity back to DTO
        return convertToDto(savedPerson);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        // Fetch all person entities from the database
        List<Person> persons = personRepository.findAll();

        // Convert each person entity to a PersonDto
        List<PersonDto> personDtos = persons.stream().map(this::convertToDto).collect(Collectors.toList());

        // Return the list of PersonDto objects
        return personDtos;
    }

    @Override
    public PersonDto getPersonById(Long id) {
        // Retrieve the person by id from the repository
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));

        // Convert the retrieved entity to DTO
        return convertToDto(person);
    }

    @Override
    @Transactional
    public PersonDto updatePerson(Long id, PersonDto personDto) {
        // Find the existing person
        Person personToUpdate = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + id));

        // Update the person's properties
        personToUpdate.setName(personDto.getName());
        personToUpdate.setDob(personDto.getDob());
        personToUpdate.setAddress(personDto.getAddress());

        // Save the updated person
        Person updatedPerson = personRepository.save(personToUpdate);

        // Convert to DTO and return
        return convertToDto(updatedPerson);
    }

    @Override
    @Transactional
    public void deletePerson(Long id) {
        // Check if the person exists in the database
        boolean exists = personRepository.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("Person not found with id: " + id);
        }

        // Delete the person
        personRepository.deleteById(id);
    }

    private PersonDto convertToDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        personDto.setDob(person.getDob());
        personDto.setAddress(person.getAddress());
        return personDto;
    }

}
