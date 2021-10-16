package com.example.petstore.dao;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.PetSaleStatus;
import com.example.petstore.entity.User;

import java.util.List;
import java.util.Optional;

public interface PetDao {

    void save(Pet pet);

    void update(Pet pet, long petId);

    List<Pet> getBySaleStatus(PetSaleStatus status);

    Optional<Pet> getById(long petId);

    void delete(long petId);

    boolean isExistById(long petId);
}
