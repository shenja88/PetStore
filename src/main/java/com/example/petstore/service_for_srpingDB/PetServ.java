package com.example.petstore.service_for_srpingDB;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.PetSaleStatus;
import com.example.petstore.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetServ {
    private final PetRepository repository;

    public boolean addPet(Pet pet) {
        if (!repository.existsById(pet.getId())) {
            repository.save(pet);
            return true;
        }
        return false;
    }

    public boolean updatePet(Pet pet, long petId) {
        if (repository.existsById(petId)) {
            pet.setId(petId);
            repository.save(pet);
            return true;
        }
        return false;
    }

    public List<Pet> getAllByStatus(String status) {
        return repository.getAllByStatus(PetSaleStatus.valueOf(status.toUpperCase(Locale.ENGLISH)));
    }

    public Optional<Pet> getById(long petId) {
        return repository.findById(petId);
    }

    public boolean delete(long petId) {
        if (repository.existsById(petId)) {
            repository.deleteById(petId);
            return true;
        }
        return false;
    }
}

