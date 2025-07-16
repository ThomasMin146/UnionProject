package com.union.insurance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelInsuranceDto extends ContractDto {
    @NotNull
    private LocalDate insuranceFrom;
    @NotNull
    private LocalDate insuranceTo;
    @NotNull
    private Boolean liabilityInsurance;
    @NotNull
    private Boolean accidentInsurance;
}
