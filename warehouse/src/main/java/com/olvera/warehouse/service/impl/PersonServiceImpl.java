package com.olvera.warehouse.service.impl;

import com.olvera.warehouse.dto.PersonDto;
import com.olvera.warehouse.entity.Person;
import com.olvera.warehouse.exception.BadBirthDateException;
import com.olvera.warehouse.exception.PasswordNotMatchException;
import com.olvera.warehouse.exception.PersonAlreadyExistsException;
import com.olvera.warehouse.exception.ResourceNotFoundException;
import com.olvera.warehouse.repository.PersonRepository;
import com.olvera.warehouse.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        optionalPerson = personRepository.findByEmail(personDto.getEmail());

        if (optionalPerson != null) {
            throw new PersonAlreadyExistsException("Person already registered with given email " + personDto.getEmail());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(personDto.getBirthDate(), formatter);
        LocalDate futureAge = LocalDate.now();

        if (birthDate.isAfter(futureAge)) {
            throw new BadBirthDateException("The user cannot have a future age");
        }


        String password = personDto.getPassword();
        String matchPassword = personDto.getMatchPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        String hashedMatchPassword = passwordEncoder.encode(matchPassword);

        if (!password.equals(matchPassword)) {
            throw new PasswordNotMatchException("Passwords don't match, please check them");
        }

        optionalPerson = Person.builder()
                .personId(personDto.getPersonId())
                .name(personDto.getName())
                .lastName(personDto.getLastName())
                .email(personDto.getEmail())
                .mobileNumber(personDto.getMobileNumber())
                .password(hashedPassword)
                .matchPassword(hashedMatchPassword)
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
                .password(person.getPassword())
                .matchPassword(person.getMatchPassword())
                .birthDate(birthDate.toString())
                .build();

    }

}
