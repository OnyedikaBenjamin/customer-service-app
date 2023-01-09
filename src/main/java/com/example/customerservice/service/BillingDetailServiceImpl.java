package com.example.customerservice.service;

import com.example.customerservice.dto.ApiResponse;
import com.example.customerservice.dto.BillingDetailRequest;
import com.example.customerservice.model.BillingDetail;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repository.BillingDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingDetailServiceImpl implements BillingDetailService{

    private final CustomerService customerService;
    private final BillingDetailRepository billingDetailRepository;

    @Override
    public ApiResponse save(BillingDetailRequest billingDetailRequest) {
        Customer customer = (Customer) customerService.findById(billingDetailRequest.getCustomerId()).getData().get("data");

        BillingDetail billingDetail = BillingDetail
                .builder()
                .customer(customer)
                .accountNumber(billingDetailRequest.getAccountNumber())
                .tariff(billingDetailRequest.getTariff())
                .build();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("success");
        apiResponse.getData().put("billing-detail", billingDetailRepository.save(billingDetail));
        return apiResponse;
    }

    @Override
    public ApiResponse findByCustomerId(Long customerId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("success");
        List<BillingDetail> billingDetails = billingDetailRepository.findAllByCustomerId(customerId);
        apiResponse.getData().put("results",billingDetails.size());
        apiResponse.getData().put("Billing Details",billingDetails);
        return apiResponse;
    }
}
