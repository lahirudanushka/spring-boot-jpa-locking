package com.open.jpalocking.controller.pessimistic;


import com.open.jpalocking.service.OptimisticService;
import com.open.jpalocking.service.PessimisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 JPA recommends using the OPTIMISTIC lock mode. When a transaction attempts to read a record,
 it aims to ensure that the version attribute has not been changed by another transaction.
 If the version attribute is not altered, it will be updated. However, if the version attributes
 do not match, it indicates a potential collision. In such a situation, the transaction can decide
 how to handle it either by throwing an exception or by retrying.
 */
@RestController
@RequestMapping("/optimistic")
public class OptimisticController {

    @Autowired
    private OptimisticService optimisticService;


    @PostMapping("/read-balance/{id}")
    ResponseEntity<Integer> readBalanceOptimistic(@PathVariable int id) throws InterruptedException {
        return ResponseEntity.ok(optimisticService.readBalanceOptimistic(id));
    }

    @PostMapping("/deduct-balance/{id}/{amount}")
    ResponseEntity<Integer> deductBalanceOptimistic(@PathVariable  int id,@PathVariable  int amount) throws InterruptedException {
        return ResponseEntity.ok(optimisticService.deductBalanceOptimistic(id,amount));
    }
}
