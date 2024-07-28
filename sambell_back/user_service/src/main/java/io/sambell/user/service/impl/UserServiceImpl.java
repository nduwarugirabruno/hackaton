package io.sambell.user.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.sambell.user.entity.metier.User;
import io.sambell.user.repository.UserRepository;
import io.sambell.user.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User update(UUID id, User user) {
        Optional<User> uses = getUserById(id);
        if (uses.isEmpty()) return null;

        if (user.getEmail() != null && !uses.get().getFirstName().equals(user.getFirstName()))
            uses.get().setFirstName(user.getFirstName());

        if (user.getEmail() != null && !uses.get().getLastName().equals(user.getLastName()))
            uses.get().setLastName(user.getLastName());

        if (user.getEmail() != null && !uses.get().getAddress().equals(user.getAddress()))
            uses.get().setAddress(user.getAddress());

        if (user.getEmail() != null && !uses.get().getPhone().equals(user.getPhone()))
            uses.get().setPhone(user.getPhone());

        if (user.getEmail() != null && !uses.get().getEmail().equals(user.getEmail()))
            uses.get().setEmail(user.getEmail());

        return userRepository.save(uses.get());
    }

    @Override
    public String delete(UUID id) {
        if (getUserById(id).isPresent()) {
            userRepository.deleteById(id);
            return "User deleted";
        } else
            return "User doesn't exist";
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public List<User> getUsersByPhone(Long phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public List<User> getUsersByAddressCity(String city) {
        return userRepository.findByAddressCity(city);
    }

    @Override
    public List<User> getUsersByAddressFirstLine(String firstLine) {
        return userRepository.findByAddressFirstLine(firstLine);
    }

    @Override
    public List<User> getUsersByAddressSecondLine(String secondLine) {
        return userRepository.findByAddressSecondLine(secondLine);
    }

    @Override
    public List<User> getUsersByCodePromo(String codePromo) {
        return userRepository.findByCodePromo(codePromo);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
