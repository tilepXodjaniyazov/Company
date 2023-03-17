package com.example.company.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDto {
    @NotNull(message = "name bosh")
    private String name;
    @NotNull(message = "phoneNumber bosh")
    private String phoneNumber;
    @NotNull(message = "street bosh")
    private String street;
    @NotNull(message = "homeNumber bosh")
    private String homeNumber;
    @NotNull(message = "departmentId bosh")
    private Integer departmentId;
}
