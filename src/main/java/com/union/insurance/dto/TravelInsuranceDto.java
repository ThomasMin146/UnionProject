package com.union.insurance.dto;

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
    private LocalDate insuranceFrom;
    private LocalDate insuranceTo;
    private boolean liabilityInsurance;
    private boolean accidentInsurance;
}
