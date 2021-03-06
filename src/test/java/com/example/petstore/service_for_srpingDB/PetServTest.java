package com.example.petstore.service_for_srpingDB;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.PetCategory;
import com.example.petstore.entity.PetSaleStatus;
import com.example.petstore.entity.Tag;
import com.example.petstore.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class PetServTest {
    private final PetServ petServ;
    private final PetRepository petRepository;
    private static final List<Pet> pets = new ArrayList<>();

    @Autowired
    public PetServTest(PetServ petServ, PetRepository petRepository) {
        this.petServ = petServ;
        this.petRepository = petRepository;
    }

    @BeforeAll
    void initPetsForTests() {
        pets.add(Pet.builder().id(1).name("Mike").tag(Tag.builder().id(1).name("Nice").build()).status(PetSaleStatus.AVAILABLE).petCategory(PetCategory.builder().id(1).name("Dog").build()).build());
        pets.add(Pet.builder().id(2).name("Ronny").tag(Tag.builder().id(2).name("Hungry").build()).status(PetSaleStatus.SOLD).petCategory(PetCategory.builder().id(2).name("Cat").build()).build());
        pets.add(Pet.builder().id(3).name("Honey").tag(Tag.builder().id(3).name("Angry").build()).status(PetSaleStatus.PENDING).petCategory(PetCategory.builder().id(3).name("Dog").build()).build());
        pets.add(Pet.builder().id(4).name("Jack").tag(Tag.builder().id(4).name("Super").build()).status(PetSaleStatus.AVAILABLE).petCategory(PetCategory.builder().id(4).name("Bird").build()).build());
        petRepository.saveAll(pets);
    }

    @Test
    public void addPet_and_getById_test() {
        assertEquals(pets.get(0), petServ.getById(pets.get(0).getId()).get());
    }

    @Test
    public void update_and_getById_Test() {
        Pet updPet = Pet.builder().id(5).name("John").tag(Tag.builder().id(5).name("Long").build()).status(PetSaleStatus.SOLD).petCategory(PetCategory.builder().id(5).name("Shark").build()).build();
        petServ.updatePet(updPet, updPet.getId());
        assertNotEquals(pets.get(1), petServ.getById(1).get());
    }

    @ParameterizedTest
    @ValueSource(strings = {"AVAILABLE", "PENDING", "SOLD"})
    public void getAllByStatusTest(String status){
        List<Pet> actualList = pets.stream().filter(p -> p.getStatus().toString().equals(status)).collect(Collectors.toList());
        List<Pet> expectedList = petServ.getAllByStatus(status);
        actualList.removeAll(expectedList);
        boolean flag = actualList.isEmpty();
        assertTrue(flag);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    public void deleteTest(int id) {
        petServ.delete(id);
        assertFalse(petServ.getById(id).isPresent());
    }
}