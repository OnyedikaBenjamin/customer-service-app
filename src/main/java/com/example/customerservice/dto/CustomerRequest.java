package com.example.customerservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CustomerRequest implements Serializable {
    @NotBlank(message = "First name cannot be blank")
    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email, Please enter a valid email")
    private String email;
}
