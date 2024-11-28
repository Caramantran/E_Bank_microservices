package com.glwa.customerservice.apis;

import com.glwa.customerservice.dto.CustomerDTO;
import com.glwa.customerservice.exceptions.CommandRejectedException;
import com.glwa.customerservice.exceptions.CustomerNotFoundException;
import com.glwa.customerservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) throws CommandRejectedException{
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("/update")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException{
        return customerService.updateCustomer(customerDTO);
    }

    @GetMapping("/list")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return customerService.searchCustomers("%"+keyword+"%");
    }

    @GetMapping("/get/{id}")
    public CustomerDTO getCustomerById(@PathVariable String id) throws CustomerNotFoundException{
        return customerService.getCustomerById(id);
    }

    @GetMapping("/find/{cin}")
    public CustomerDTO getCustomerByCin(@PathVariable String cin) throws CustomerNotFoundException{
        return customerService.getCustomerByCin(cin);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomerById(@PathVariable String id){
        customerService.deleteCustomerById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }
}
