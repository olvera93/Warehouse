package com.olvera.warehouse.controller;

import com.olvera.warehouse.dto.LoginDto;
import com.olvera.warehouse.dto.PersonDto;
import com.olvera.warehouse.service.impl.PersonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1", produces = (MediaType.APPLICATION_JSON_VALUE))
@Validated
@CrossOrigin("*")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;


    @PostMapping("/signUp")
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto personDto) {

        PersonDto response = personService.createUser(personDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/signIn")
    public ResponseEntity<PersonDto> login(@Valid @RequestBody LoginDto loginDto) {
        PersonDto response = personService.authenticate(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/fetch/{personId}")
    public ResponseEntity<PersonDto> fetchPerson(@PathVariable(name = "personId") Long personId) {
        PersonDto response = personService.getUserById(personId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
