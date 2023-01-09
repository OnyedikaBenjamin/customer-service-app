package com.example.customerservice.repository;

import com.example.customerservice.model.BillingDetail;
import com.example.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillingDetailRepository extends JpaRepository<BillingDetail, Long> {

    @Query("select b from BillingDetail b where b.customer.id = ?1")
    List<BillingDetail> findAllByCustomerId(Long customerId);
}
