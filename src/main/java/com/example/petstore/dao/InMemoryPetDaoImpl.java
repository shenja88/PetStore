package com.example.petstore.dao;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.PetSaleStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InMemoryPetDaoImpl implements PetDao {
    private final List<Pet> pets = new ArrayList<>();


    @Override
    public void save(Pet pet) {
        pets.add(pet);
    }

    @Override
    public void update(Pet pet, long petId) {
        for (Pet p : pets) {
            if (p.getId() == petId) {
                pets.set(pets.indexOf(p), pet);
            }
        }
    }

    @Override
    public List<Pet> getBySaleStatus(PetSaleStatus status) {
        return pets.stream()
                .filter(p -> p.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pet> getById(long petId) {
        return pets.stream().filter(pet -> pet.getId() == petId).findFirst();
    }

    @Override
    public void delete(long petId) {
        pets.removeIf(p -> p.getId() == petId);
    }

    @Override
    public boolean isExistById(long petId) {
        return pets.stream().anyMatch(p -> p.getId() == petId);
    }
}
