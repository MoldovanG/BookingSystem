package com.moldovan.uni.bookingsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moldovan.uni.bookingsystem.controller.PersonController;
import com.moldovan.uni.bookingsystem.dto.PersonDto;
import com.moldovan.uni.bookingsystem.mapper.PersonMapper;
import com.moldovan.uni.bookingsystem.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.xml.transform.OutputKeys;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest {

    private PersonService personServiceMock;
    private PersonController personController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init(){
        personServiceMock = Mockito.mock(PersonService.class);
        personController = new PersonController(personServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldCallPersonServiceWhenGetAll(){
        //ACT
        personController.getAll();
        //ASSERT
        Mockito.verify(personServiceMock).getAll();
    }

    @Test
    public void shouldRemovePerson() throws Exception {
        // Act & assert
        MvcResult result = mockMvc.perform(delete("/api/person/VS-778998"))
                .andExpect(status().isOk())
                .andReturn();
        Mockito.verify(personServiceMock).delete("VS-778998");
    }

    @Test
    public void shouldInsertANewPerson() throws Exception {
        PersonDto personDto = PersonDto.builder()
                .email("testEmail@yahoo.com")
                .address("test adress nr 1")
                .name("Testifer")
                .surname("Testiruf")
                .identityCardIdentifier("VS-777777")
                .build();
        when(personServiceMock.create(any())).thenReturn(personDto);

        // Act
        MvcResult result = mockMvc.perform(post("/api/person")
                .content(objectMapper.writeValueAsString(personDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(personDto));
    }


    @Test
    public void shouldUpdateASpecificPerson() throws Exception {
        //ARRANGE
        PersonDto personDto = PersonDto.builder()
                .email("testEmail@yahoo.com")
                .address("test adress nr 1")
                .name("Testifer")
                .surname("Testiruf")
                .identityCardIdentifier("VS-777777")
                .build();
        when(personServiceMock.update(any(), any())).thenReturn(personDto);

        // Act
        MvcResult result = mockMvc.perform(put("/api/person/1")
                .content(objectMapper.writeValueAsString(personDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(personDto));
    }

}
