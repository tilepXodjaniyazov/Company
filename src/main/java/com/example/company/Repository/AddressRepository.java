package com.example.company.Repository;

import com.example.company.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    boolean existsByHomeNumberOrStreet(String homeNumber, String street);
    boolean findByIdIsNull();
}
