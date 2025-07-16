package com.union.insurance.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelInsurance extends Contract {
    @NotNull
    private LocalDate insuranceFrom;
    @NotNull
    private LocalDate insuranceTo;
    @NotNull
    private Boolean liabilityInsurance;
    @NotNull
    private Boolean accidentInsurance;
}