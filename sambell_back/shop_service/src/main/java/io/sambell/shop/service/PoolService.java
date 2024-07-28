package io.sambell.shop.service;

import io.sambell.shop.entity.app.Pool;
import io.sambell.shop.entity.app.enums.PoolState;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PoolService {
    Pool create(Pool pool, UUID userID);
    Pool update(UUID id, Pool pool);
    String delete(UUID id);
    Optional<Pool> getPoolById(UUID id);
    List<Pool> getAllPools();
    List<Pool> getPoolsByCreatorFirstName(String firstName);
    List<Pool> getPoolsByCreatorLastName(String lastName);
    List<Pool> getPoolsByState(PoolState state);
}
