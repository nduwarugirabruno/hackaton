package io.sambell.user.service;

import io.sambell.user.entity.app.auth.AuthenticationRequest;
import io.sambell.user.entity.app.auth.AuthenticationResponse;
import io.sambell.user.entity.app.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
