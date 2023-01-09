package com.example.customerservice.config;

import com.example.customerservice.model.BillingDetail;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.BillingDetailRepository;
import com.example.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class InitializeDatabase {

    private final CustomerRepository customerRepository;
    private final BillingDetailRepository billingDetailRepository;

    @PostConstruct
    public void initializeData() {
        Customer customer1 = customerRepository.save(Customer.builder().firstName("Kelvin").lastName("Okoro").email("kelvin.okoro@gmail.com").build());
        Customer customer2 = customerRepository.save(Customer.builder().firstName("Kelvin").lastName("Okoro").email("k.okoro@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Test1").lastName("Test1").email("test1@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Test2").lastName("Test2").email("test2@gmail.com").build());
        customerRepository.save(Customer.builder().firstName("Test3").lastName("Test3").email("test3@gmail.com").build());


        billingDetailRepository.save(BillingDetail.builder().customer(customer1).accountNumber("0123456789").tariff(new BigDecimal(200)).build());
        billingDetailRepository.save(BillingDetail.builder().customer(customer1).accountNumber("1234567890").tariff(new BigDecimal(200)).build());
        billingDetailRepository.save(BillingDetail.builder().customer(customer1).accountNumber("2345678901").tariff(new BigDecimal(200)).build());
        billingDetailRepository.save(BillingDetail.builder().customer(customer2).accountNumber("3456789012").tariff(new BigDecimal(200)).build());
        billingDetailRepository.save(BillingDetail.builder().customer(customer2).accountNumber("4567890123").tariff(new BigDecimal(200)).build());
    }
}