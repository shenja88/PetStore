package com.example.petstore.aspect;

import com.example.petstore.entity.Pet;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PetLogAspect {

    private final Logger logger = LoggerFactory.getLogger(PetLogAspect.class.getSimpleName());

    @Pointcut("execution(public * com.example.petstore.controller.PetController.addPet(..)) && args(pet)")
    public void savePet(Pet pet){
    }

    @Pointcut("execution(public * com.example.petstore.controller.PetController.update(..)) && args(*, id)")
    public void updPet(long id){
    }

    @Pointcut("execution(public * com.example.petstore.controller.PetController.get(..)) && args(petId)")
    public void getPet(long petId){
    }

    @Pointcut("execution(public * com.example.petstore.controller.PetController.delete(..)) && args(petId)")
    public void remove(long petId){
    }

    @Pointcut("execution(public * com.example.petstore.controller.PetController.findByStatus(..)) && args(status)")
    public void getPetsByStatus(String status){
    }

    @After(value = "savePet(pet)", argNames = "pet")
    public void addPet(Pet pet){
        logger.info("Add new pet with name - {}", pet.getName());
    }

    @After(value = "updPet(id)", argNames = "id")
    public void updatePet(long id){
        logger.info("Update pet with id - {}", id);
    }

    @After(value = "getPet(petId)", argNames = "petId")
    public void getById(long petId){
        logger.info("Request to pet with id - {}", petId);
    }

    @After(value = "remove(petId)", argNames = "petId")
    public void delete(long petId){
        logger.info("Remove pet with id - {}", petId);
    }

    @After(value = "getPetsByStatus(status)", argNames = "status")
    public void getAllPetsByStatus(String status){
        logger.info("Request to pets with status - {}", status);
    }
}
