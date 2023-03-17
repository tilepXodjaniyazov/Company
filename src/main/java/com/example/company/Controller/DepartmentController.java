package com.example.company.Controller;

import com.example.company.Dto.DepartmentDto;
import com.example.company.Entity.Department;
import com.example.company.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping
    public ResponseEntity<List<Department>> getDepartment() {
        List<Department> departmentList = departmentService.getDepartment();
        if (!departmentList.isEmpty()) {
            return ResponseEntity.ok().body(departmentList);
        }
        return ResponseEntity.status(204).body(departmentList);
    }
    @PostMapping
    public ResponseEntity<Result> postDepartment(@RequestBody DepartmentDto departmentDto) {
        Result result = departmentService.postDepartment(departmentDto);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(204).body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result> putDepartment(@PathVariable Integer id, @RequestBody DepartmentDto departmentDto) {
        Result result = departmentService.putDepartment(id,departmentDto);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(204).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteDepartment(@PathVariable Integer id) {
        Result result = departmentService.deleteDepartment(id);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(204).body(result);
    }
}
