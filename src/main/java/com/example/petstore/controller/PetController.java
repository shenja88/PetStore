package com.example.petstore.controller;

import com.example.petstore.entity.Pet;
import com.example.petstore.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;

    @PostMapping("/addPet")
    public ResponseEntity<Pet> addPet(@Valid @RequestBody Pet pet) {
        if (petService.addPet(pet)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }

    @PutMapping("/addPet")
    public ResponseEntity<Pet> update(@Valid @RequestBody Pet pet) {
        if (petService.updatePet(pet, pet.getId())) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> get(@PathVariable long petId) {
        Optional<Pet> optionalPet = petService.getById(petId);
        if (optionalPet.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalPet.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{petId}")
    public ResponseEntity<Pet> delete(@PathVariable long petId) {
        if (petService.delete(petId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<Pet>> findByStatus(@RequestParam("status") String status){
        List<Pet> petList = petService.getAllByStatus(status);
        if(petList.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
