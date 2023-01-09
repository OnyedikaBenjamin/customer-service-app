package com.example.customerservice.service;

import com.example.customerservice.dto.ApiResponse;
import com.example.customerservice.dto.CustomerRequest;
import com.example.customerservice.model.BillingDetail;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.BillingDetailRepository;
import com.example.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BillingDetailRepository billingDetailRepository;

    private Customer customer1;

    @BeforeEach
    void setUp() {
        billingDetailRepository.deleteAll();
        customerRepository.deleteAll();

        customer1 = customerRepository.save(Customer.builder().firstName("Kelvin").lastName("Okoro").email("kelvin.okoro@gmail.com").build());
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

    @Test
    void findAll() {
        assertThat(customerRepository.findAll()).hasSize(5);
        ApiResponse apiResponse = customerService.findAll(1, 5);

        assertThat(apiResponse).isNotNull();

        assertThat((List<?>) apiResponse.getData().get("customers")).hasSize(5);
        assertThat((int) apiResponse.getData().get("results")).isEqualTo(5);
        assertThat((long) apiResponse.getData().get("TotalNumberOfCustomers")).isEqualTo(5);
        assertThat((int) apiResponse.getData().get("pageNumber")).isEqualTo(1);
        assertThat((int) apiResponse.getData().get("TotalPages")).isEqualTo(1);
    }

    @Test
    void save() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("First Name");
        customerRequest.setLastName("Last Name");
        customerRequest.setEmail("first@gmail.com");

        // given
        assertThat(customerRepository.findAll()).hasSize(5);

        // when
        customerService.save(customerRequest);

        // assert
        assertThat(customerRepository.findAll()).hasSize(6);
    }

    @Test
    void saveThrowsError() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("");
        customerRequest.setLastName("Last Name");
        customerRequest.setEmail("first@gmail.com");

        assertThrows(ConstraintViolationException.class, () -> customerService.save(customerRequest));
    }

    @Test
    void saveThrowsOnInvalidEmail() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setFirstName("First Name");
        customerRequest.setLastName("Last Name");
        customerRequest.setEmail("email");

        assertThrows(ConstraintViolationException.class, () -> customerService.save(customerRequest));
    }

    @Test
    void findById() {
        ApiResponse apiResponse = customerService.findById(customer1.getId());

        assertThat(apiResponse).isNotNull();
        assertThat((Customer) apiResponse.getData().get("customer")).isNotNull();
        assertThat(((Customer) apiResponse.getData().get("customer")).getEmail()).isEqualTo(customer1.getEmail());
    }


}