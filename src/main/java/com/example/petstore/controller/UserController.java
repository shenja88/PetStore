package com.example.petstore.controller;

import com.example.petstore.entity.User;
import com.example.petstore.service_for_srpingDB.UserServ;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserServ userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        if (userService.addUser(user)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getByUserName(@PathVariable String userName) {
        Optional<User> userOptional = userService.getUser(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<User> update(@Valid @RequestBody User newUser, @PathVariable String userName) {
        Optional<User> userOptional = userService.getUser(userName);
        if (userOptional.isPresent()) {
            User userForUpd = userOptional.get();
            if (userService.updateUser(userForUpd, newUser)) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteById(@PathVariable long userId) {
        if (userService.delete(userId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/addUsers")
    public ResponseEntity<User> addUsers(@Valid @RequestBody List<User> users) {
        userService.addUserList(users);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
