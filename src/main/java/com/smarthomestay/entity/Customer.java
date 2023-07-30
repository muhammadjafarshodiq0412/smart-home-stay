package com.smarthomestay.entity;

import com.smarthomestay.model.Request.CustomerSaveRequest;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{
    private String nik;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String status;

    public Customer(CustomerSaveRequest request, String status) {
        this.nik = request.getNik();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.username = request.getUsername();
        this.password = request.getPassword();
        this.phoneNumber = request.getPhoneNumber();
        this.email = request.getEmail();
        this.status = status;
    }
}
