package com.example.petstore.repositiry;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.PetSaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> getAllByStatus(PetSaleStatus status);
}
