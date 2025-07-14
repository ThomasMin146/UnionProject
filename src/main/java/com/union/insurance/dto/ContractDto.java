package com.union.insurance.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PropertyInsuranceDto.class, name = "PROPERTY"),
        @JsonSubTypes.Type(value = TravelInsuranceDto.class, name = "TRAVEL")
})
@Data
public abstract class ContractDto {
    private String contractNumber;
    private LocalDate startDate;
}