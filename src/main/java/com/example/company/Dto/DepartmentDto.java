package com.example.company.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {
    @NotNull(message = "name bosh")
    private String name;
    @NotNull(message = "company bosh")
    private Integer companyId;
}
