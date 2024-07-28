package io.sambell.user.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.sambell.user.entity.app.Address;
import io.sambell.user.entity.app.Telephone;
import io.sambell.user.entity.app.auth.AuthenticationRequest;
import io.sambell.user.entity.app.auth.AuthenticationResponse;
import io.sambell.user.entity.app.auth.RegisterRequest;
import io.sambell.user.entity.app.enums.Country;
import io.sambell.user.entity.metier.User;
import io.sambell.user.repository.UserRepository;
import io.sambell.user.service.AuthenticationService;
import io.sambell.user.service.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var address = Address.builder()
                .country(Country.fromCode(request.getCountry()))
                .city(request.getCity())
                .firstLine(request.getFirstLine())
                .secondLine(request.getSecondLine())
                .build();

        var phone = Telephone.builder()
                .country(Country.fromCode(request.getCountry()))
                .phoneNumber(request.getPhone())
                .build();

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(address)
                .phone(phone)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var user_temp = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user_temp);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("authentication : " + request.getEmail() + " -> " + request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
