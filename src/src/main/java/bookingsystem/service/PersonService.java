package bookingsystem.service;

import com.moldovan.uni.bookingsystem.domain.Person;
import com.moldovan.uni.bookingsystem.dto.PersonDto;
import com.moldovan.uni.bookingsystem.mapper.PersonMapper;
import com.moldovan.uni.bookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    public PersonDto save(PersonDto personDto) {
        Person person = personMapper.mapToEntity(personDto);
        Person savedPerson = personRepository.save(person);
        return personMapper.mapToDto(savedPerson);
    }

    public PersonDto update(PersonDto personDto, String personId) {
        Person currentEntity= personRepository.getOne(personId);
        currentEntity.setAddress(personDto.getAddress());
        currentEntity.setName(personDto.getName());
        currentEntity.setSurname(personDto.getSurname());
        currentEntity.setIdentityCardIdentifier(personDto.getIdentityCardIdentifier());
        Person update = personRepository.save(personMapper.mapToEntity(personDto));
        return personMapper.mapToDto(update);
    }

    public List<PersonDto> getAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        Person entity = personRepository.getOne(id);
        personRepository.delete(entity);
    }

}