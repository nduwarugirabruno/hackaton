package io.sambell.user.controller;

import io.sambell.user.entity.metier.User;
import io.sambell.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("update/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody User user) {

        try {
            User user1 = userService.update(id, user);
            if (user1 != null)
                return ResponseEntity.ok(user1);
            else return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // renvoie un code de statut 500
        }
    }

    @PutMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            String response = userService.delete(id);
            if (response.equals("Product deleted"))
                return ResponseEntity.ok(response);
            else return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // renvoie un code de statut 500
        }
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("get/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/firstName/{firstName}")
    public ResponseEntity<List<User>> getUsersByFirstName(@PathVariable String firstName) {
        List<User> users = userService.getUsersByFirstName(firstName);
        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/lastName/{lastName}")
    public ResponseEntity<List<User>> getUsersByLastName(@PathVariable String lastName) {
        List<User> users = userService.getUsersByLastName(lastName);
        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/phone/{phone}")
    public ResponseEntity<List<User>> getUsersByPhone(@PathVariable Long phone) {
        List<User> users = userService.getUsersByPhone(phone);
        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/city/{city}")
    public ResponseEntity<List<User>> getUsersByAddressCity(@PathVariable String city) {
        List<User> users = userService.getUsersByAddressCity(city);
        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/firstLine/{firstLine}")
    public ResponseEntity<List<User>> getUsersByAddressFirstLine(@PathVariable String firstLine) {
        List<User> users = userService.getUsersByAddressFirstLine(firstLine);
        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/secondLine/{secondLine}")
    public ResponseEntity<List<User>> getUsersByAddressSecondLine(@PathVariable String secondLine) {
        List<User> users = userService.getUsersByAddressSecondLine(secondLine);
        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/codePromo/{codePromo}")
    public ResponseEntity<List<User>> getUsersByCodePromo(@PathVariable String codePromo) {
        List<User> users = userService.getUsersByCodePromo(codePromo);
        if (!users.isEmpty())
            return ResponseEntity.ok(users);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("get/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> use = userService.getUserByEmail(email);
        return use.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
