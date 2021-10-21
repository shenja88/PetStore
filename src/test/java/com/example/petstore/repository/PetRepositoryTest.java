package com.example.petstore.repository;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.PetCategory;
import com.example.petstore.entity.PetSaleStatus;
import com.example.petstore.entity.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
public class PetRepositoryTest {
    private final PetRepository repository;
    private final List<Pet> pets = new ArrayList<>();

    @Autowired
    public PetRepositoryTest(PetRepository repository) {
        this.repository = repository;
    }

    @BeforeAll
    public void initDB(){
        pets.add(Pet.builder().id(1).name("Mike").tag(Tag.builder().id(1).name("Nice").build()).status(PetSaleStatus.AVAILABLE).petCategory(PetCategory.builder().id(1).name("Dog").build()).build());
        pets.add(Pet.builder().id(2).name("Ronny").tag(Tag.builder().id(2).name("Hungry").build()).status(PetSaleStatus.SOLD).petCategory(PetCategory.builder().id(2).name("Cat").build()).build());
        pets.add(Pet.builder().id(3).name("Honey").tag(Tag.builder().id(3).name("Angry").build()).status(PetSaleStatus.PENDING).petCategory(PetCategory.builder().id(3).name("Dog").build()).build());
        pets.add(Pet.builder().id(4).name("Jack").tag(Tag.builder().id(4).name("Super").build()).status(PetSaleStatus.AVAILABLE).petCategory(PetCategory.builder().id(4).name("Bird").build()).build());
        repository.saveAll(pets);
    }

    @ParameterizedTest
    @ValueSource(strings = {"AVAILABLE", "PENDING", "SOLD"})
    public void getAllByStatusTest(String status){
        List<Pet> actualList = pets.stream().filter(p -> p.getStatus().toString().equals(status)).collect(Collectors.toList());
        List<Pet> expectedList = repository.getAllByStatus(PetSaleStatus.valueOf(status));
        actualList.removeAll(expectedList);
        boolean flag = actualList.isEmpty();
        assertTrue(flag);
    }

    @Test
    public void addPet_and_updPetTest(){
        Pet pet = Pet.builder().id(4).name("Son").tag(Tag.builder().id(5).name("Cool").build()).status(PetSaleStatus.SOLD).petCategory(PetCategory.builder().id(5).name("Horse").build()).build();
        repository.save(pet);
        assertEquals(pets.get(3), repository.getById(pets.get(3).getId()));
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4})
    public void getById_and_deletePetTest(long id) {
        Pet pet = repository.getById(id);
        repository.delete(pet);
        assertFalse(repository.existsById(id));
    }
}
