package com.union.insurance.entity;

import com.union.insurance.enums.PropertyType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyInsurance extends Contract {
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    @Embedded
    @Valid
    private Address propertyAddress;
    @NotNull
    private BigDecimal propertyValue;
}
