package io.sambell.user.repository;

import io.sambell.user.entity.metier.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select u from User u where u.email like %?1%")
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.firstName like %?1%")
    List<User> findByFirstName(String firstName);

    @Query("select u from User u where u.lastName like %?1%")
    List<User> findByLastName(String lastName);

    @Query("select u from User u where u.phone.phoneNumber = ?1")
    List<User> findByPhone(Long phone);

    @Query("select u from User u where u.address.city like %?1%")
    List<User> findByAddressCity(String city);

    @Query("select u from User u where u.address.firstLine like %?1%")
    List<User> findByAddressFirstLine(String firstLine);

    @Query("select u from User u where u.address.secondLine like %?1%")
    List<User> findByAddressSecondLine(String secondLine);

    @Query("select u from User u where u.codePromo like %?1%")
    List<User> findByCodePromo(String codePromo);
}
