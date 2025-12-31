package org.kidoni.sixdegrees.tmdb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.kidoni.sixdegrees.SixDegreesTestContainers;
import org.kidoni.sixdegrees.tmdb.model.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.neo4j.test.autoconfigure.DataNeo4jTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataNeo4jTest
public class PersonDetailsRepositoryITest implements SixDegreesTestContainers {
    @Autowired
    private PersonDetailsRepository personDetailsRepository;

    @Test
    public void testSaveAndFindPerson() {
        PersonDetails personDetails = new PersonDetails();
        personDetails.setId(1);
        personDetails.setName("John Doe");
        var result = personDetailsRepository.save(personDetails);
        assertEquals(1, result.getId());
        Optional<PersonDetails> find = personDetailsRepository.findById(Objects.requireNonNull(personDetails.getId()));
        assertTrue(find.isPresent());
        var person = find.get();
        assertEquals(1, person.getId());
    }

    @Test
    void testSavePersonWithNullableFields() {
        // Arrange
        PersonDetails person = new PersonDetails();
        person.setId(2);
        person.setName("Jane Smith");
        person.setBirthday("1985-05-15");
        person.setDeathday(null); // Still alive
        person.setBiography("An acclaimed actress");
        person.setPlaceOfBirth("Los Angeles, California");
        person.setPopularity(new BigDecimal("75.3"));

        // Act
        PersonDetails saved = personDetailsRepository.save(person);

        // Assert
        Optional<PersonDetails> found = personDetailsRepository.findById(2);
        assertTrue(found.isPresent());
        PersonDetails foundPerson = found.get();
        assertEquals("Jane Smith", foundPerson.getName());
        assertEquals("1985-05-15", foundPerson.getBirthday());
        assertNull(foundPerson.getDeathday());
        assertEquals("An acclaimed actress", foundPerson.getBiography());
        assertEquals("Los Angeles, California", foundPerson.getPlaceOfBirth());
        assertEquals(new BigDecimal("75.3"), foundPerson.getPopularity());
    }

    @Test
    void testUpdatePerson() {
        // Arrange
        PersonDetails person = new PersonDetails();
        person.setId(3);
        person.setName("Original Name");
        person.setBiography("Original bio");
        personDetailsRepository.save(person);

        // Act - Update
        person.setName("Updated Name");
        person.setBiography("Updated biography");
        person.setBirthday("1990-01-01");
        PersonDetails updated = personDetailsRepository.save(person);

        // Assert
        Optional<PersonDetails> found = personDetailsRepository.findById(3);
        assertTrue(found.isPresent());
        PersonDetails foundPerson = found.get();
        assertEquals("Updated Name", foundPerson.getName());
        assertEquals("Updated biography", foundPerson.getBiography());
        assertEquals("1990-01-01", foundPerson.getBirthday());
    }

    @Test
    void testDeletePerson() {
        // Arrange
        PersonDetails person = new PersonDetails();
        person.setId(4);
        person.setName("Person to Delete");
        personDetailsRepository.save(person);

        // Verify it exists
        assertTrue(personDetailsRepository.findById(4).isPresent());

        // Act
        personDetailsRepository.deleteById(4);

        // Assert
        Optional<PersonDetails> found = personDetailsRepository.findById(4);
        assertFalse(found.isPresent());
    }

    @Test
    void testFindAll() {
        // Arrange
        PersonDetails person1 = new PersonDetails();
        person1.setId(10);
        person1.setName("Actor One");

        PersonDetails person2 = new PersonDetails();
        person2.setId(11);
        person2.setName("Actor Two");

        PersonDetails person3 = new PersonDetails();
        person3.setId(12);
        person3.setName("Actor Three");

        // Act
        personDetailsRepository.save(person1);
        personDetailsRepository.save(person2);
        personDetailsRepository.save(person3);

        // Assert
        Iterable<PersonDetails> all = personDetailsRepository.findAll();
        assertNotNull(all);

        List<PersonDetails> personList = new ArrayList<>();
        all.forEach(personList::add);

        assertTrue(personList.size() >= 3, "Should have at least 3 persons");
        assertTrue(personList.stream().anyMatch(p -> "Actor One".equals(p.getName())));
        assertTrue(personList.stream().anyMatch(p -> "Actor Two".equals(p.getName())));
        assertTrue(personList.stream().anyMatch(p -> "Actor Three".equals(p.getName())));
    }
}
