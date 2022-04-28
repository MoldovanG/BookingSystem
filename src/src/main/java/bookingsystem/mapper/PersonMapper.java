package com.moldovan.uni.bookingsystem.mapper;

import com.moldovan.uni.bookingsystem.domain.Person;
import com.moldovan.uni.bookingsystem.dto.PersonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDto mapToDto(Person person);
    Person mapToEntity(PersonDto personDto);
}
