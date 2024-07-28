package io.sambell.user.entity.app.auth;

import io.sambell.user.entity.app.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String firstLine;
    private String secondLine;
    private Long phone;
    private String email;
    private String password;
    private Role role;
}
