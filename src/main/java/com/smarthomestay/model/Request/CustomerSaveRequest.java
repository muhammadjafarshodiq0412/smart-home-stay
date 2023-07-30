package com.smarthomestay.model.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerSaveRequest {
    @NotBlank(message = "nik is mandatory")
    private String nik;
    @NotBlank(message = "firstName is mandatory")
    private String firstName;
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    @NotBlank(message = "username is mandatory")
    private String username;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;
}
