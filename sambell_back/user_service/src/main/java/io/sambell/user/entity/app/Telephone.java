package io.sambell.user.entity.app;

import io.sambell.user.entity.app.enums.Country;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "telephone_table")
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;
}
