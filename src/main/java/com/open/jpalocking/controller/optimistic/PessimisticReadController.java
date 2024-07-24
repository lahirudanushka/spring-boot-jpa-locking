package com.open.jpalocking.controller.optimistic;

import com.open.jpalocking.service.PessimisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This method provides a shared lock, allowing multiple transactions to read the same record simultaneously.
 * However, it prevents any transaction from acquiring a write lock on the same record.
 *
 * In summary:
 * - Multiple transactions can read the record at the same time.
 * - No updates or deletes are allowed while the shared lock is held.
 * - The record will be blocked from modification until the lock is released.
 *
 * Note:
 * - You must start a transaction in the service or method annotated with @Transactional.
 * - Failing to do so will result in an exception.
 *
 * Potential Issues:
 * -Deadlock: If another transaction is also trying to read the same record with PESSIMISTIC_READ and then
 * attempts to update it, it could lead to deadlock if both transactions end up waiting for each other’s locks.
 * -Lock Contention: While PESSIMISTIC_READ prevents other transactions from modifying the data, it doesn’t block
 * concurrent reads. However, if there’s an update operation, PESSIMISTIC_READ alone may not prevent conflicts.
 */
@RestController
@RequestMapping("/pessimistic-read")
public class PessimisticReadController {

    @Autowired
    private PessimisticService pessimisticService;


    @PostMapping("/read-balance/{id}")
    ResponseEntity<Integer> readBalance(@PathVariable  int id) throws InterruptedException {
       return ResponseEntity.ok(pessimisticService.readBalancePessimisticRead(id));
    }

    @PostMapping("/deduct-balance/{id}/{amount}")
    ResponseEntity<Integer> deductBalance(@PathVariable  int id,@PathVariable  int amount) throws InterruptedException {
        return ResponseEntity.ok(pessimisticService.deductBalancePessimisticRead(id,amount));
    }


}
