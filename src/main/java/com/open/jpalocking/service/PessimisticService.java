package com.open.jpalocking.service;

import com.open.jpalocking.model.AccountBalancePessimistic;
import com.open.jpalocking.model.AccountBalancePessimisticForceIncrementRepository;
import com.open.jpalocking.model.AccountBalancePessimisticReadRepository;
import com.open.jpalocking.model.AccountBalancePessimisticWriteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PessimisticService {

    @Autowired
    private AccountBalancePessimisticReadRepository repositoryPRead;

    @Autowired
    private AccountBalancePessimisticWriteRepository repositoryPWrite;

    @Autowired
    private AccountBalancePessimisticForceIncrementRepository repositoryPForceIncrement;

    @Transactional
    public Integer readBalancePessimisticRead(int id) throws InterruptedException {
        log.debug("AccountBalancePessimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = repositoryPRead.findById(id);
        if (record.isPresent()) {
            log.debug("AccountBalancePessimistic | READ ENDED");
            return record.get().getBalance();
        }
        return null;
    }

    @Transactional
    public Integer deductBalancePessimisticRead(int id, int amount) throws InterruptedException {
        log.debug("AccountBalancePessimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = repositoryPRead.findById(id);
        log.debug("AccountBalancePessimistic | READ ENDED");
        if (record.isPresent()) {
            log.debug("AccountBalancePessimistic | MAP STARTED");
            AccountBalancePessimistic updated = record.get();
            updated.setBalance(updated.getBalance() - amount);
            log.debug("AccountBalancePessimistic | MAP ENDED");
            log.debug("AccountBalancePessimistic | WRITE STARTED");
            Integer balance = repositoryPRead.save(updated).getBalance();
            log.debug("AccountBalancePessimistic | WRITE ENDED");
            /** Adding Delay For Testing Purpose **/
            Thread.sleep(10000);
            return balance;
        }
        return null;
    }

    @Transactional
    public Integer readBalancePessimisticWrite(int id) {
        log.debug("AccountBalancePessimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = repositoryPWrite.findById(id);
        if (record.isPresent()) {
            log.debug("AccountBalancePessimistic | READ ENDED");
            return record.get().getBalance();
        }
        return null;
    }

    @Transactional
    public Integer deductBalancePessimisticWrite(int id, int amount) throws InterruptedException {
        log.debug("AccountBalancePessimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = repositoryPWrite.findById(id);
        log.debug("AccountBalancePessimistic | READ ENDED");
        if (record.isPresent()) {
            log.debug("AccountBalancePessimistic | MAP STARTED");
            AccountBalancePessimistic updated = record.get();
            updated.setBalance(updated.getBalance() - amount);
            log.debug("AccountBalancePessimistic | MAP ENDED");
            log.debug("AccountBalancePessimistic | WRITE STARTED");
            Thread.sleep(10000);

            Integer balance = repositoryPWrite.save(updated).getBalance();
            log.debug("AccountBalancePessimistic | WRITE ENDED");
            /** Adding Delay For Testing Purpose **/

            return balance;
        }
        return null;
    }

    @Transactional
    public Integer readBalancePessimisticForceIncrement(int id) {
        log.debug("AccountBalancePessimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = repositoryPForceIncrement.findById(id);
        if (record.isPresent()) {
            log.debug("AccountBalancePessimistic | READ ENDED");
            return record.get().getBalance();
        }
        return null;
    }

    @Transactional
    public Integer deductBalancePessimisticForceIncrement(int id, int amount) throws InterruptedException {
        log.debug("AccountBalancePessimistic | READ STARTED");
        Optional<AccountBalancePessimistic> record = repositoryPForceIncrement.findById(id);

        log.debug("AccountBalancePessimistic | READ ENDED");
        if (record.isPresent()) {
            log.debug("AccountBalancePessimistic | MAP STARTED");
            AccountBalancePessimistic updated = record.get();
            updated.setBalance(updated.getBalance() - amount);
            log.debug("AccountBalancePessimistic | MAP ENDED");
            log.debug("AccountBalancePessimistic | WRITE STARTED");
            Thread.sleep(10000);

            Integer balance = repositoryPForceIncrement.save(updated).getBalance();
            log.debug("AccountBalancePessimistic | WRITE ENDED");
            /** Adding Delay For Testing Purpose **/

            return balance;
        }
        return null;
    }
}
