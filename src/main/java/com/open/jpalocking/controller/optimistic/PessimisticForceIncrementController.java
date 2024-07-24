package com.open.jpalocking.controller.optimistic;

import com.open.jpalocking.service.PessimisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is pretty similar to PESSIMISTIC_WRITE. It additionally increments a version attribute of a versioned entity.
 * Most likely this lock mode is being used to provide optimistic locking.
 *
 * Pessimistic Locking is a great option to provide data integrity and consistency however it reduces concurrency.
 * It might impact the performance of large-scale applications
 */
@RestController
@RequestMapping("/pessimistic-force-increment")
public class PessimisticForceIncrementController {

    @Autowired
    private PessimisticService pessimisticService;


    @PostMapping("/read-balance/{id}")
    ResponseEntity<Integer> readBalancePessimisticForceIncrement(@PathVariable int id) throws InterruptedException {
        return ResponseEntity.ok(pessimisticService.readBalancePessimisticForceIncrement(id));
    }

    @PostMapping("/deduct-balance/{id}/{amount}")
    ResponseEntity<Integer> deductBalancePessimisticForceIncrement(@PathVariable  int id,@PathVariable  int amount) throws InterruptedException {
        return ResponseEntity.ok(pessimisticService.deductBalancePessimisticForceIncrement(id,amount));
    }

}
