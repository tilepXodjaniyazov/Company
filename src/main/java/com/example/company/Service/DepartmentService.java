package com.example.company.Service;

import com.example.company.Controller.Result;
import com.example.company.Dto.DepartmentDto;
import com.example.company.Entity.Company;
import com.example.company.Entity.Department;
import com.example.company.Repository.CompanyRepository;
import com.example.company.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    ;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public List<Department> getDepartment() {
        return departmentRepository.findAll();
    }

    public Result postDepartment(DepartmentDto departmentDto) {
        try {
            if (departmentRepository.existsByName(departmentDto.getName())) {
                return new Result("Bunday Department mavjud", false);
            }
            Optional<Company> byId = companyRepository.findById(departmentDto.getCompanyId());
            if (!byId.isPresent()) {
                return new Result("Bunday companya topilmadi", false);
            }
            Department department = new Department();
            department.setName(departmentDto.getName());
            department.setCompany(byId.get());
            departmentRepository.save(department);
            return new Result("Saqlandi", true);
        } catch (Exception e) {
            return new Result("Error", false);
        }
    }

    public Result putDepartment(Integer id, DepartmentDto departmentDto) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (byId.isPresent()) {
            Department department = byId.get();
            Optional<Company> byIdc = companyRepository.findById(departmentDto.getCompanyId());
            if (!byIdc.isPresent()) {
                return new Result("Bunday companya topilmadi", false);
            }
            department.setName(departmentDto.getName());
            department.setCompany(byIdc.get());
            departmentRepository.save(department);
            return new Result("Ozgartirildi", true);
        }
        return new Result("Bunday Department", false);
    }

    public Result deleteDepartment(Integer id) {
        try {
            if (!departmentRepository.existsById(id)) {
                return new Result("BUnday Department topilmadi", false);
            }
            departmentRepository.deleteById(id);
            return new Result("Ochirildi", true);
        } catch (Exception e) {
            return new Result("Error", false);
        }
    }
}
