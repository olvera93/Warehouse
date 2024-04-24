package com.olvera.warehouse.service.impl;


import com.olvera.warehouse.dto.LoginDto;
import com.olvera.warehouse.dto.PersonDto;
import com.olvera.warehouse.entity.Person;
import com.olvera.warehouse.exception.BadBirthDateException;
import com.olvera.warehouse.exception.PasswordNotMatchException;
import com.olvera.warehouse.exception.PersonAlreadyExistsException;
import com.olvera.warehouse.exception.ResourceNotFoundException;
import com.olvera.warehouse.repository.PersonRepository;
import com.olvera.warehouse.service.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PersonServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private IJwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

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
                .role(personDto.getRole())
                .build();

        Person savePerson = personRepository.save(optionalPerson);

        String token = jwtService.generateToken(savePerson);
        personDto.setToken(token);
        return PersonDto.builder()
                .personId(savePerson.getPersonId())
                .name(savePerson.getName())
                .lastName(savePerson.getLastName())
                .email(savePerson.getEmail())
                .mobileNumber(savePerson.getMobileNumber())
                .password(savePerson.getPassword())
                .matchPassword(savePerson.getMatchPassword())
                .birthDate(birthDate.toString())
                .role(savePerson.getRole())
                .token(token)
                .build();
    }



    public PersonDto getUserById(Long personId) {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person", "personId", personId.toString()));

        return personToDto(person);
    }

    public PersonDto authenticate(LoginDto personDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        personDto.getName(),
                        personDto.getPassword()
                )
        );

        Person person = personRepository.findByName(personDto.getName()).orElseThrow();
        String token = jwtService.generateToken(person);
        personDto.setToken(token);

        return PersonDto.builder()
                .personId(person.getPersonId())
                .name(person.getName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .mobileNumber(person.getMobileNumber())
                .password(person.getPassword())
                .matchPassword(person.getMatchPassword())
                .birthDate(person.getBirthDate().toString())
                .role(person.getRole())
                .token(token)
                .build();
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
                .role(person.getRole())
                .build();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
