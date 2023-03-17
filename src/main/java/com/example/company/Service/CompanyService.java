package com.example.company.Service;

import com.example.company.Controller.Result;
import com.example.company.Dto.CompanyDto;
import com.example.company.Entity.Address;
import com.example.company.Entity.Company;
import com.example.company.Repository.AddressRepository;
import com.example.company.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Company> getCompany() {
        return companyRepository.findAll();
    }

    public Result putCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> byId = companyRepository.findById(id);
        if (byId.isPresent()) {
            Company company = byId.get();
            company.setDirectorName(companyDto.getDirectorName());
            company.setCorpName(companyDto.getCorpName());
            Optional<Address> byId1 = addressRepository.findById(companyDto.getAddressId());
            if (!byId.isPresent()) {
                return new Result("Bunday address topilmadi", false);
            }
            company.setAddress(byId.get().getAddress());
            companyRepository.save(company);
            return new Result("Ozagardi", true);
        }
        return new Result("Bunday companya topilmadi", false);
    }

    public Result deleteCompany(Integer id) {
        try {
            if (!companyRepository.existsById(id)) {
                return new Result("Bunday campanya yoq",false);
            }
            companyRepository.deleteById(id);
            return new Result("ochirildi", true);
        } catch (Exception e) {
            return new Result("Error", false);

        }
    }

    public Result postCompany(CompanyDto companyDto) {
        try {
            Address byId = addressRepository.findById(companyDto.getAddressId()).get();
            boolean b = companyRepository.existsByCorpNameOrAddressOrDirectorName(companyDto.getCorpName(), byId, companyDto.getDirectorName());
            if (b) {
                return new Result("Bunday companya mavjud", false);
            }
            Company company = new Company(null, companyDto.getCorpName(), companyDto.getDirectorName(), byId);
            Company save = companyRepository.save(company);
            return new Result("Saqlandi", true);
        } catch (Exception e) {
            return new Result("Error", false);
        }
    }
}
