package com.glwa.customerservice.repositories;

import com.glwa.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query("select c from Customer c where c.name like :kw or c.firstname like :kw")
    List<Customer> search(@Param("kw") String keywords);

    @Query("select case when count(c)>0 then true else false END from Customer c where c.cin=?1")
    Boolean checkIfCinExists(String cin);

    @Query("select case when count(c)>0 then true else false END from Customer c where c.email=?1")
    Boolean checkIfEmailExists(String email);

    @Query("select case when count(c)>0 then true else false END from Customer c where c.phone=?1")
    Boolean checkIfPhoneExists(String phone);

    Customer findByCin(String cin);
}
