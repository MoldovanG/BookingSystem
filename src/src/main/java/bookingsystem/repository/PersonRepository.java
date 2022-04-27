package bookingsystem.repository;
import com.moldovan.uni.bookingsystem.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonRepository {

    private List<Person> persons = new ArrayList<>();

    public PersonRepository() {
        loadData();
    }

    public Person save(Person person) {
        persons.add(person);
        return person;
    }

    public Person update(Person person) {
        Optional<Person> optionalPerson = persons.stream()
                .filter(personIterator -> personIterator.getIdentityCardIdentifier().equals(person.getIdentityCardIdentifier()))
                .findAny();
        if (optionalPerson.isPresent()) {
            persons.remove(optionalPerson.get());
            persons.add(person);
            return person;
        }
        return null;
    }

    public List<Person> getAll() {
        return persons;
    }

    public void delete(Person person) {
        persons.remove(person);
    }

    private void loadData() {
        Person person1 = new Person(UUID.randomUUID().toString(),"Moldovan", "George","VS12345", "City, street number appartment 1");
        Person person2 = new Person(UUID.randomUUID().toString(),"Florea", "Andreea","VS12346", "City, street number appartment 2");

        persons.add(person1);
        persons.add(person2);
    }
}