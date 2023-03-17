package com.example.company.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "corpName bosh")
    private String corpName;
    @NotNull(message = "directorName bosh")
    private String directorName;
    @NotNull(message = "Address bosh")
    private Integer addressId;
}
