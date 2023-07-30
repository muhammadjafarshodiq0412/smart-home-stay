package com.smarthomestay.service;

import com.smarthomestay.constant.GeneralConstant;
import com.smarthomestay.entity.Customer;
import com.smarthomestay.model.Request.CustomerSaveRequest;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public GeneralResponse<Object> save(CustomerSaveRequest request) {
        log.info("Start process saving customer");
        try {
            Customer customer = new Customer(request, GeneralConstant.STATUS_UNACTIVED);
            customer = customerRepository.save(customer);
            log.info("Success process saving customer");
            return new GeneralResponse<>(HttpStatus.OK.value(), "Success", "Success save data", customer);
        } catch (Exception e) {
            log.error("Error process saving customer with message : {}", e.getMessage());
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
    }
}
