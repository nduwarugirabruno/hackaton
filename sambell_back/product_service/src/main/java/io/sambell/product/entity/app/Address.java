package io.sambell.product.entity.app;

import io.sambell.product.entity.app.enums.Country;
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
@Table(name = "address_table")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "first_line", nullable = false)
    private String firstLine;

    @Column(name = "second_line")
    private String secondLine;
}
