package com.union.insurance.mapper;

import com.union.insurance.dto.ContractDto;
import com.union.insurance.dto.PropertyInsuranceDto;
import com.union.insurance.dto.TravelInsuranceDto;
import com.union.insurance.entity.Contract;
import com.union.insurance.entity.PropertyInsurance;
import com.union.insurance.entity.TravelInsurance;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ContractMapper {

    public static Contract toEntity(ContractDto contractDto){
        if (contractDto instanceof TravelInsuranceDto travelInsuranceDto) {
            return toTravelInsurance(travelInsuranceDto);
        } else if (contractDto instanceof PropertyInsuranceDto propertyInsuranceDto){
            return toPropertyInsurance(propertyInsuranceDto);
        }
        throw new IllegalArgumentException("Unknown contract type: " + contractDto.getClass());
    }

    public static ContractDto toDto(Contract contract){
        if (contract instanceof TravelInsurance travelInsurance) {
            return toTravelInsuranceDto(travelInsurance);
        } else if (contract instanceof PropertyInsurance propertyInsurance){
            return toPropertyInsuranceDto(propertyInsurance);
        }
        throw new IllegalArgumentException("Unknown contract type: " + contract.getClass());
    }

    public static PropertyInsurance toPropertyInsurance(PropertyInsuranceDto dto) {
        PropertyInsurance entity = new PropertyInsurance();
        entity.setContractNumber(dto.getContractNumber());
        entity.setStartDate(dto.getStartDate());
        entity.setPropertyType(dto.getPropertyType());
        entity.setPropertyValue(dto.getPropertyValue());
        entity.setPropertyAddress(AddressMapper.toEntity(dto.getPropertyAddress()));
        return entity;
    }

    public static TravelInsurance toTravelInsurance(TravelInsuranceDto dto) {
        TravelInsurance entity = new TravelInsurance();
        entity.setContractNumber(dto.getContractNumber());
        entity.setStartDate(dto.getStartDate());
        entity.setInsuranceFrom(dto.getInsuranceFrom());
        entity.setInsuranceTo(dto.getInsuranceTo());
        entity.setAccidentInsurance(dto.getAccidentInsurance());
        entity.setLiabilityInsurance(dto.getLiabilityInsurance());
        return entity;
    }

    public static PropertyInsuranceDto toPropertyInsuranceDto(PropertyInsurance entity) {
        PropertyInsuranceDto dto = new PropertyInsuranceDto();
        dto.setContractNumber(entity.getContractNumber());
        dto.setStartDate(entity.getStartDate());
        dto.setPropertyType(entity.getPropertyType());
        dto.setPropertyValue(entity.getPropertyValue());
        dto.setPropertyAddress(AddressMapper.toDto(entity.getPropertyAddress()));
        return dto;
    }

    public static TravelInsuranceDto toTravelInsuranceDto(TravelInsurance entity) {
        TravelInsuranceDto dto = new TravelInsuranceDto();
        dto.setContractNumber(entity.getContractNumber());
        dto.setStartDate(entity.getStartDate());
        dto.setInsuranceFrom(entity.getInsuranceFrom());
        dto.setInsuranceTo(entity.getInsuranceTo());
        dto.setAccidentInsurance(entity.getAccidentInsurance());
        dto.setLiabilityInsurance(entity.getLiabilityInsurance());
        return dto;
    }

    public static Set<Contract> toEntitySet(Set<ContractDto> dtos) {
        return dtos == null ? Collections.emptySet() :
                dtos.stream().map(ContractMapper::toEntity).collect(Collectors.toSet());
    }

    public static Set<ContractDto> toDtoSet(Set<Contract> entities) {
        return entities == null ? Collections.emptySet() :
                entities.stream().map(ContractMapper::toDto).collect(Collectors.toSet());
    }
}
