package io.sambell.shop.service.impl;

import io.sambell.shop.entity.app.Pool;
import io.sambell.shop.entity.app.Product;
import io.sambell.shop.entity.app.enums.PoolState;
import io.sambell.shop.entity.metier.User;
import io.sambell.shop.service.PoolService;
import io.sambell.shop.service.ProductService;
import io.sambell.shop.repository.PoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PoolServiceImpl implements PoolService {

    private PoolRepository poolRepository;
    private ProductService productService;
    private WebClient.Builder webClientBuilder;

    @Autowired
    public PoolServiceImpl(PoolRepository poolRepository, ProductService productService, WebClient.Builder webClientBuilder) {
        this.poolRepository = poolRepository;
        this.productService = productService;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Pool create(Pool pool, UUID userID) {
        User user = webClientBuilder.build()
                .get()
                .uri("http://user-service/api/v1/user/get/id/{id}", userID)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        System.out.println("user: " + user);
        Product product = productService.create(pool.getProduct());
        System.out.println("product: " + product);

        if (user != null) {
            var pool1 = Pool.builder()
                    .product(product)
                    .quantity(pool.getQuantity())
                    .creatorUser(user)
                    .state(PoolState.CREATED)
                    .build();

            return poolRepository.save(pool1);
        }
        else return null;
    }

    @Override
    public Pool update(UUID id, Pool pool) {
        return null;
    }

    @Override
    public String delete(UUID id) {
        if (getPoolById(id).isPresent()) {
            poolRepository.deleteById(id);
            return "Product deleted";
        } else
            return "Product doesn't exist";
    }

    @Override
    public Optional<Pool> getPoolById(UUID id) {
        return poolRepository.findById(id);
    }

    @Override
    public List<Pool> getAllPools() {
        return poolRepository.findAll();
    }

    @Override
    public List<Pool> getPoolsByCreatorFirstName(String firstName) {
        return poolRepository.findByCreatorUser_FirstName(firstName);
    }

    @Override
    public List<Pool> getPoolsByCreatorLastName(String lastName) {
        return poolRepository.findByCreatorUser_LastName(lastName);
    }

    @Override
    public List<Pool> getPoolsByState(PoolState state) {
        return poolRepository.findByState(state);
    }
}
