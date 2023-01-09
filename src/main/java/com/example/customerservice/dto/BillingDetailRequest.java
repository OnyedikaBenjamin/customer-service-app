package com.example.customerservice.dto;

import com.example.customerservice.config.PriceConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class BillingDetailRequest {

    @NotBlank(message = "Billing Detail cannot be blank")
    @NotNull(message = "Billing Detail must have a customer")
    private Long customerId;

    @Pattern(regexp = "[0-9]{10}", message = "Account number can only be numbers")
    @Size(min = 10, max = 10, message = "Account number can only be of length ten")
    @NotNull(message = "Account number cannot be null")
    private String accountNumber;

    @JsonSerialize(using = PriceConfig.class)
    private BigDecimal tariff;
}
