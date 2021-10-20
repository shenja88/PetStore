package com.example.petstore.service_for_srpingDB;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.PetCategory;
import com.example.petstore.entity.PetSaleStatus;
import com.example.petstore.entity.Tag;
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
    private static final List<Pet> pets = new ArrayList<>();

    @Autowired
    public PetServTest(PetServ petServ) {
        this.petServ = petServ;
    }

    @BeforeAll
    void initPetsForTests() {
        pets.add(Pet.builder().id(1).name("Mike").tag(Tag.builder().id(1).name("Nice").build()).status(PetSaleStatus.AVAILABLE).petCategory(PetCategory.builder().id(1).name("Dog").build()).build());
        pets.add(Pet.builder().id(2).name("Ronny").tag(Tag.builder().id(2).name("Hungry").build()).status(PetSaleStatus.SOLD).petCategory(PetCategory.builder().id(2).name("Cat").build()).build());
        pets.add(Pet.builder().id(3).name("Honey").tag(Tag.builder().id(3).name("Angry").build()).status(PetSaleStatus.PENDING).petCategory(PetCategory.builder().id(1).name("Dog").build()).build());
        pets.add(Pet.builder().id(4).name("Jack").tag(Tag.builder().id(4).name("Super").build()).status(PetSaleStatus.AVAILABLE).petCategory(PetCategory.builder().id(3).name("Bird").build()).build());
    }


    @Test
    public void addPet_and_getById_test() {
        petServ.addPet(pets.get(0));
        assertEquals(pets.get(0), petServ.getById(pets.get(0).getId()).get());
    }

    @Test
    public void update_and_getById_Test() {
        petServ.addPet(pets.get(0));
        petServ.updatePet(pets.get(1), pets.get(0).getId());
        assertEquals(pets.get(1), petServ.getById(1).get());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    public void deleteTest(int id) {
        petServ.addPet(pets.get(id - 1));
        petServ.delete(id);
        assertFalse(petServ.getById(id).isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"AVAILABLE", "PENDING", "SOLD"})
    public void getAllByStatusTest(String status){
        petServ.addPet(pets.get(0));
        petServ.addPet(pets.get(1));
        petServ.addPet(pets.get(2));
        petServ.addPet(pets.get(3));

        List<Pet> actualList = pets.stream().filter(p -> p.getStatus().toString().equals(status)).collect(Collectors.toList());
        List<Pet> expectedList = petServ.getAllByStatus(status);
        actualList.removeAll(expectedList);
        boolean flag = actualList.isEmpty();
        assertTrue(flag);
    }
}