package io.sambell.payment.entity.app;

import io.sambell.payment.entity.app.enums.PoolState;
import io.sambell.payment.entity.metier.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pool_table")
public class Pool {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User creatorUser;

    @OneToMany(cascade = CascadeType.DETACH)
    private List<User> participantList;

    @Enumerated(EnumType.STRING)
    private PoolState state;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
