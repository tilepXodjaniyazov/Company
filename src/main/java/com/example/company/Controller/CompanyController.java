package com.example.company.Controller;

import com.example.company.Dto.CompanyDto;
import com.example.company.Entity.Company;
import com.example.company.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompany() {
        List<Company> companyList = companyService.getCompany();
        if (companyList != null) return ResponseEntity.ok().body(companyList);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Result> postCompany(@RequestBody CompanyDto companyDto) {
        Result result = companyService.postCompany(companyDto);
       if (result.getActive()) {
           return  ResponseEntity.ok().body(result);
       }
       return ResponseEntity.status(406).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> putCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto) {
        Result result = companyService.putCompany(id,companyDto);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(404).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteCompany(@PathVariable Integer id) {
        Result result = companyService.deleteCompany(id);
        if (result.getActive()) {
            ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(404).body(result);
    }

}
