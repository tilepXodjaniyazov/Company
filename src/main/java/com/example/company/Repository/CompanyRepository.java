package com.example.company.Repository;

import com.example.company.Entity.Address;
import com.example.company.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpNameOrAddressOrDirectorName(String corpName, Address address, String directorName);
    boolean existsById(Integer id);
}
