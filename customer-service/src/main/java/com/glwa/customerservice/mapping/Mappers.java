package com.glwa.customerservice.mapping;

import com.glwa.customerservice.dto.CustomerDTO;
import com.glwa.customerservice.entities.Customer;

import java.util.List;

public interface Mappers {
    Customer fromCustomerDTO(CustomerDTO customerDTO);
    CustomerDTO fromCustomer(Customer customer);
    List<CustomerDTO> fromListOfCustomers(List<Customer> customers);
}
