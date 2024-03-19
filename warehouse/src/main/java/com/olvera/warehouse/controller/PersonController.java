package com.olvera.warehouse.controller;

import com.olvera.warehouse.dto.PersonDto;
import com.olvera.warehouse.service.IPersonService;
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
    private IPersonService personService;


    @PostMapping("/signUp")
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto personDto) {

        PersonDto response = personService.createUser(personDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/fetch/{personId}")
    public ResponseEntity<PersonDto> fetchPerson(@PathVariable(name = "personId") Long personId) {
        PersonDto response = personService.getUserById(personId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
