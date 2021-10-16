package com.example.petstore.service;

import com.example.petstore.dao.PetDao;
import com.example.petstore.entity.Pet;
import com.example.petstore.entity.PetSaleStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetService {
    private final PetDao petDao;

    public boolean addPet(Pet pet){
        if(!petDao.isExistById(pet.getId())){
            petDao.save(pet);
            return true;
        }
        return false;
    }

    public boolean updatePet(Pet pet, long petId){
        if(petDao.isExistById(petId)){
            petDao.update(pet, petId);
            return true;
        }
        return false;
    }

    public List<Pet> getAllByStatus(String status){
        return petDao.getBySaleStatus(PetSaleStatus.valueOf(status.toUpperCase(Locale.ENGLISH)));
    }

    public Optional<Pet> getById(long petId){
        return petDao.getById(petId);
    }

    public boolean delete(long petId){
        if(petDao.isExistById(petId)){
            petDao.delete(petId);
            return true;
        }
        return false;
    }
}
