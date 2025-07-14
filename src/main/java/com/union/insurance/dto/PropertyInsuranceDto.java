package com.union.insurance.dto;

import com.union.insurance.enums.PropertyType;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyInsuranceDto extends ContractDto {
    private PropertyType propertyType;
    @Valid
    private AddressDto propertyAddress;
    private BigDecimal propertyValue;
}
