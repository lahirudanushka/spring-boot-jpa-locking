package com.open.jpalocking.service;

import com.open.jpalocking.model.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OptimisticService {

    @Autowired
    private AccountBalanceOptimisticRepository optimisticRepository;

    @Autowired
    private AccountBalanceOptimisticForceIncrementRepository optimisticForceIncrementRepository;



    @Transactional
    public Integer readBalanceOptimistic(int id) throws InterruptedException {
        log.debug("AccountBalanceOptimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = optimisticRepository.findById(id);
        if (record.isPresent()) {
            log.debug("AccountBalanceOptimistic | READ ENDED");
            return record.get().getBalance();
        }
        return null;
    }

    @Transactional
    public Integer deductBalanceOptimistic(int id, int amount) throws InterruptedException {
        log.debug("AccountBalanceOptimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = optimisticRepository.findById(id);
        log.debug("AccountBalanceOptimistic | READ ENDED");
        if (record.isPresent()) {
            log.debug("AccountBalanceOptimistic | MAP STARTED");
            AccountBalancePessimistic updated = record.get();
            updated.setBalance(updated.getBalance() - amount);
            log.debug("AccountBalanceOptimistic | MAP ENDED");
            log.debug("AccountBalanceOptimistic | WRITE STARTED");
            Integer balance = optimisticRepository.save(updated).getBalance();
            log.debug("AccountBalanceOptimistic | WRITE ENDED");
            /** Adding Delay For Testing Purpose **/
            Thread.sleep(10000);
            return balance;
        }
        return null;
    }

    @Transactional
    public Integer readBalanceOptimisticForceIncrement(int id) {
        log.debug("AccountBalanceOptimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = optimisticForceIncrementRepository.findById(id);
        if (record.isPresent()) {
            log.debug("AccountBalanceOptimistic | READ ENDED");
            return record.get().getBalance();
        }
        return null;
    }


    @Transactional
    public Integer deductBalanceOptimisticForceIncrement(int id, int amount) throws InterruptedException {
        log.debug("AccountBalanceOptimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = optimisticForceIncrementRepository.findById(id);
        log.debug("AccountBalanceOptimistic | READ ENDED");
        if (record.isPresent()) {
            log.debug("AccountBalanceOptimistic | MAP STARTED");
            AccountBalancePessimistic updated = record.get();
            updated.setBalance(updated.getBalance() - amount);
            log.debug("AccountBalanceOptimistic | MAP ENDED");
            log.debug("AccountBalanceOptimistic | WRITE STARTED");
            Integer balance = optimisticForceIncrementRepository.save(updated).getBalance();
            log.debug("AccountBalanceOptimistic | WRITE ENDED");
            /** Adding Delay For Testing Purpose **/
            Thread.sleep(10000);
            return balance;
        }
        return null;
    }
}
