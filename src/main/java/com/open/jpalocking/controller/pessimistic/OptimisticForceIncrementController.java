package com.open.jpalocking.controller.pessimistic;


import com.open.jpalocking.service.OptimisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 It is similar to OPTIMISTIC. It not only controls version collisions but also increments the version attribute.
 Even if the record does not change, it increments the version attribute to prevent potential collisions when
 performing a read operation by another transaction on the same record

 This lock mode will increment version attribute for read and modification
 */
@RestController
@RequestMapping("/optimistic-force-increment")
public class OptimisticForceIncrementController {

    @Autowired
    private OptimisticService optimisticService;


    @PostMapping("/read-balance/{id}")
    ResponseEntity<Integer> readBalanceOptimisticForceIncrement(@PathVariable int id) throws InterruptedException {
        return ResponseEntity.ok(optimisticService.readBalanceOptimisticForceIncrement(id));
    }

    @PostMapping("/deduct-balance/{id}/{amount}")
    ResponseEntity<Integer> deductBalanceOptimisticForceIncrement(@PathVariable  int id,@PathVariable  int amount) throws InterruptedException {
        return ResponseEntity.ok(optimisticService.deductBalanceOptimisticForceIncrement(id,amount));
    }
}
