package com.open.jpalocking.model;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountBalancePessimisticReadRepository extends JpaRepository<AccountBalancePessimistic,Integer> {

    @Override
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    Optional<AccountBalancePessimistic>  findById(Integer id);
}
