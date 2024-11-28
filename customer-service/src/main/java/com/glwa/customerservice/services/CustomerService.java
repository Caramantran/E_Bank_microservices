package com.glwa.customerservice.services;

import com.glwa.customerservice.dto.CustomerDTO;
import com.glwa.customerservice.exceptions.CommandRejectedException;
import com.glwa.customerservice.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO) throws CommandRejectedException;
    CustomerDTO updateCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException;
    List<CustomerDTO> getAllCustomers();
    List<CustomerDTO> searchCustomers(String keyword);
    CustomerDTO getCustomerById(String id) throws CustomerNotFoundException;
    CustomerDTO getCustomerByCin(String cin) throws CustomerNotFoundException;
    void deleteCustomerById(String id);
}
