package com.open.jpalocking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalancePessimistic {
    @Id
    private Integer id;

    private Integer balance;

    /** Not required for PESSIMISTIC_READ and PESSIMISTIC_WRITE **/
    @Version
    private Long version;
}
