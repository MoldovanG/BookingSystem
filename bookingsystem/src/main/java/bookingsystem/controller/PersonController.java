package com.moldovan.uni.bookingsystem.controller;

import com.moldovan.uni.bookingsystem.dto.PersonDto;
import com.moldovan.uni.bookingsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto create(
            @Valid
            @RequestBody PersonDto personDto) {
        return personService.create(personDto);
    }

    @DeleteMapping(value = "/{identityCardIdentifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String remove(@PathVariable("identityCardIdentifier") String identityCardIdentifier) {
        personService.delete(identityCardIdentifier);
        return String.format("Person %s was removed", identityCardIdentifier);
    }

    @PutMapping(value = "/{personId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto personDto,@PathVariable("personId") Long personId) {
        PersonDto updatedPerson = personService.update(personDto, personId);
        return new ResponseEntity<>(updatedPerson, null == updatedPerson ? HttpStatus.NO_CONTENT: HttpStatus.OK);
    }

}