package com.union.insurance.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuredWithContractsDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String personalNumber;
    @Email
    @NotBlank
    private String email;
    @NotNull
    @Valid
    private AddressDto permanentAddress;
    @Valid
    private AddressDto correspondenceAddress;
    @Valid
    private Set<ContractDto> contracts = new HashSet<>();
}
