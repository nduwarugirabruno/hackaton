package io.sambell.shop.controller;

import io.sambell.shop.entity.app.Pool;
import io.sambell.shop.service.PoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/pool")
@RequiredArgsConstructor
public class PoolController {

    private final PoolService poolService;

    @PostMapping("create/{id}")
    public ResponseEntity<Pool> create(@RequestBody Pool pool, @PathVariable UUID id) {

        try {
            Pool pool1 = poolService.create(pool, id);
            if (pool1 != null)
                return ResponseEntity.ok(pool1);
            else return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // renvoie un code de statut 500
        }
    }


}
