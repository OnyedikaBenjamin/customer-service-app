package com.example.customerservice.model;

import com.example.customerservice.config.PriceConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountNumber;

    @JsonSerialize(using = PriceConfig.class)
    private BigDecimal tariff;

    @ManyToOne
    private Customer customer;

    @PrePersist
    public void updateAccountNumber(){
        this.accountNumber = accountNumber + "-01";
    }
}
