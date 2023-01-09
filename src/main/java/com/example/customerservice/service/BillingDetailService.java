package com.example.customerservice.service;

import com.example.customerservice.dto.ApiResponse;
import com.example.customerservice.dto.BillingDetailRequest;
import com.example.customerservice.model.BillingDetail;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Service
public interface BillingDetailService {
    ApiResponse save(@Valid BillingDetailRequest billingDetailRequest);
    ApiResponse findByCustomerId(Long customerId);
}
