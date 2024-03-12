package com.olvera.warehouse.service.impl;

import com.olvera.warehouse.dto.PersonDto;
import com.olvera.warehouse.entity.Person;
import com.olvera.warehouse.exception.PersonAlreadyExistsException;
import com.olvera.warehouse.exception.ResourceNotFoundException;
import com.olvera.warehouse.repository.PersonRepository;
import com.olvera.warehouse.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonDto createUser(PersonDto personDto) {

        Person optionalPerson = personRepository.findByMobileNumber(personDto.getMobileNumber());

        if (optionalPerson != null) {
            throw new PersonAlreadyExistsException("Person already registered with given mobileNumber " + personDto.getMobileNumber());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(personDto.getBirthDate(), formatter);

         optionalPerson = Person.builder()
                .personId(personDto.getPersonId())
                .name(personDto.getName())
                .lastName(personDto.getLastName())
                .email(personDto.getEmail())
                .mobileNumber(personDto.getMobileNumber())
                .birthDate(birthDate)
                .createdAt(LocalDateTime.now())
                .build();

        Person savePerson = personRepository.save(optionalPerson);

        return personToDto(savePerson);
    }

    @Override
    public PersonDto getUserById(Long personId) {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "personId", personId.toString()));

        return personToDto(person);
    }


    private static PersonDto personToDto(Person person) {

        LocalDate birthDate = person.getBirthDate();

        return PersonDto.builder()
                .personId(person.getPersonId())
                .name(person.getName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .mobileNumber(person.getMobileNumber())
                .birthDate(birthDate.toString())
                .build();

    }

}
