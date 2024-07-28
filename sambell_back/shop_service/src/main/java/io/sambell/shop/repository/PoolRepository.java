package io.sambell.shop.repository;

import io.sambell.shop.entity.app.Pool;
import io.sambell.shop.entity.app.enums.PoolState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PoolRepository extends JpaRepository<Pool, UUID> {

    @Query("select p from Pool p where p.creatorUser.lastName like %?1%")
    List<Pool> findByCreatorUser_LastName(String lastName);

    @Query("select p from Pool p where p.creatorUser.firstName like %?1%")
    List<Pool> findByCreatorUser_FirstName(String firstName);

    @Query("select p from Pool p where p.state like %?1%")
    List<Pool> findByState(PoolState state);
}
