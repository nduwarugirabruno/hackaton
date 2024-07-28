package io.sambell.user.service;

import io.sambell.user.entity.metier.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User update(UUID id, User user);
    String delete(UUID id);
    Optional<User> getUserById(UUID userId);
    List<User> getAllUsers();
    List<User> getUsersByFirstName(String firstName);
    List<User> getUsersByLastName(String lastName);
    List<User> getUsersByPhone(Long phone);
    List<User> getUsersByAddressCity(String city);
    List<User> getUsersByAddressFirstLine(String firstLine);
    List<User> getUsersByAddressSecondLine(String secondLine);
    List<User> getUsersByCodePromo(String codePromo);
    Optional<User> getUserByEmail(String email);
}
