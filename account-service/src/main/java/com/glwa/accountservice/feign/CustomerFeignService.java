package com.glwa.accountservice.feign;

import com.glwa.accountservice.dtos.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerFeignService {

    @GetMapping("/customer/get/{id}")
    CustomerDTO getCustomerById(@PathVariable  String id);

    @GetMapping("/customer/find/{cin}")
    CustomerDTO getCustomerByCin(@PathVariable  String cin);
}
