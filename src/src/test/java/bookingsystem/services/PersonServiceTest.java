package com.moldovan.uni.bookingsystem.services;

import com.moldovan.uni.bookingsystem.domain.Person;
import com.moldovan.uni.bookingsystem.dto.PersonDto;
import com.moldovan.uni.bookingsystem.mapper.PersonMapper;
import com.moldovan.uni.bookingsystem.repository.PersonRepository;
import com.moldovan.uni.bookingsystem.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceTest {
    PersonService personServiceMock;
    PersonRepository personRepositoryMock;
    PersonMapper personMapperMock;

    @BeforeEach
    public void init(){
          personRepositoryMock = Mockito.mock(PersonRepository.class);
          personMapperMock = Mockito.mock(PersonMapper.class);

         personServiceMock = new PersonService(personRepositoryMock, personMapperMock);
    }

    @Test
    public void shouldCreatePerson(){
        //ARRANGE
        PersonDto givenPersonDto = generateDummyPersonDto();
        Person expectedGeneratedPerson = generatePersonBasedOnDto(givenPersonDto, 1L);
        Mockito.when(personMapperMock.mapToEntity(givenPersonDto)).thenReturn(expectedGeneratedPerson);
        Mockito.when(personMapperMock.mapToDto(expectedGeneratedPerson)).thenReturn(givenPersonDto);
        Mockito.when(personRepositoryMock.save(expectedGeneratedPerson)).thenReturn(expectedGeneratedPerson);
        //ACT
        PersonDto generatedPerson = personServiceMock.create(givenPersonDto);
        //ASSERT
        givenPersonDto.setGetUrl("http://localhost:8081/api/person/"+expectedGeneratedPerson.getId());
        Assert.isTrue(generatedPerson.equals(givenPersonDto));
    }

    @Test
    public void shouldDeletePerson(){
        //ARRANGE
        Person givenPerson = generateDummyPerson();
        List<Person> givenList =  new ArrayList<Person>();
        givenList.add(givenPerson);
        Mockito.when(personRepositoryMock.findAll()).thenReturn((givenList));
        //ACT
        personServiceMock.delete("VS-77777");
        //ASSERT
        Mockito.verify(personRepositoryMock).delete(givenPerson);
    }

    @Test
    public void shouldUpdatePerson(){
        //ARRANGE
        PersonDto givenPersonDto = generateDummyPersonDto();
        Person expectedPerson = generatePersonBasedOnDto(givenPersonDto, 1L);
        Mockito.when(personMapperMock.mapToDto(expectedPerson)).thenReturn(givenPersonDto);
        Mockito.when(personRepositoryMock.getOne(1L)).thenReturn(expectedPerson);
        Mockito.when(personRepositoryMock.save(expectedPerson)).thenReturn(expectedPerson);
        //ACT
        PersonDto updatedPerson = personServiceMock.update(givenPersonDto, 1L);
        //ASSERT
        Mockito.verify(personRepositoryMock).save(expectedPerson);
        Assert.isTrue(updatedPerson.equals(givenPersonDto));
    }

    @Test
    public void shouldGetAll(){
        //ACT
        personServiceMock.getAll();
        //ASSERT
        Mockito.verify(personRepositoryMock).findAll();
    }


    private Person generatePersonBasedOnDto(PersonDto personDto,long id) {
        return Person.builder()
                .id(id)
                .email(personDto.getEmail())
                .address(personDto.getAddress())
                .surname(personDto.getSurname())
                .name(personDto.getName())
                .identityCardIdentifier(personDto.getIdentityCardIdentifier())
                .build();
    }

    private PersonDto generateDummyPersonDto(){
        return PersonDto.builder()
                .email("testEmail@yahoo.com")
                .address("test adress nr 1")
                .name("Testifer")
                .surname("Testiruf")
                .identityCardIdentifier("VS-77777")
                .build();
    }

    private Person generateDummyPerson(){
        return Person.builder()
                .email("testEmail@yahoo.com")
                .address("test adress nr 1")
                .name("Testifer")
                .surname("Testiruf")
                .identityCardIdentifier("VS-77777")
                .build();
    }
}

