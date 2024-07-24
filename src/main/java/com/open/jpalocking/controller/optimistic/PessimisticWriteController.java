package com.open.jpalocking.controller.optimistic;


import com.open.jpalocking.service.PessimisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * It provides an exclusive lock, meaning that only one transaction can read or write at a time. No other transaction is
 * allowed to perform read or write operations concurrently. This offers the highest level of isolation for records. However,
 * it reduces concurrent access.
 */
@RestController
@RequestMapping("/pessimistic-write")
public class PessimisticWriteController {

    @Autowired
    private PessimisticService pessimisticService;


    @PostMapping("/read-balance/{id}")
    ResponseEntity<Integer> readBalancePessimisticWrite(@PathVariable int id) throws InterruptedException {
        return ResponseEntity.ok(pessimisticService.readBalancePessimisticWrite(id));
    }

    @PostMapping("/deduct-balance/{id}/{amount}")
    ResponseEntity<Integer> deductBalancePessimisticWrite(@PathVariable  int id,@PathVariable  int amount) throws InterruptedException {
        return ResponseEntity.ok(pessimisticService.deductBalancePessimisticWrite(id,amount));
    }
}
