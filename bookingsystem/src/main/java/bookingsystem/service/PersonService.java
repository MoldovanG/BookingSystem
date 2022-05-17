package com.moldovan.uni.bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.Person;
import com.moldovan.uni.bookingsystem.dto.PersonDto;
import com.moldovan.uni.bookingsystem.mapper.PersonMapper;
import com.moldovan.uni.bookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public PersonDto create(PersonDto personDto) {
        Person person = personMapper.mapToEntity(personDto);
        Person savedPerson = personRepository.save(person);
        PersonDto created = personMapper.mapToDto(savedPerson);
        created.setGetUrl("http://localhost:8081/api/person/"+savedPerson.getId());
        return created;
    }

    public PersonDto update(PersonDto personDto, Long personId) {
        Person currentEntity= personRepository.getOne(personId);
        currentEntity.setAddress(personDto.getAddress());
        currentEntity.setName(personDto.getName());
        currentEntity.setSurname(personDto.getSurname());
        currentEntity.setIdentityCardIdentifier(personDto.getIdentityCardIdentifier());
        Person update = personRepository.save(currentEntity);
        return personMapper.mapToDto(update);
    }

    public List<PersonDto> getAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void delete(String identityCardIdentifier) {
        Optional<Person> entity  = personRepository.findAll()
                .stream().filter(p -> p.getIdentityCardIdentifier().equals(identityCardIdentifier)).findFirst();
        entity.ifPresent((entityValue) -> personRepository.delete(entityValue));
    }

}