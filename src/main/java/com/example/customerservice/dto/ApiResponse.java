package com.example.customerservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class ApiResponse implements Serializable {
    private String status;
    private Map<String, Object> data = new HashMap<>();
}
