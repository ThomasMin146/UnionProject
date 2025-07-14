package com.union.insurance.mapper;

import com.union.insurance.dto.InsuredDto;
import com.union.insurance.dto.InsuredWithContractsDto;
import com.union.insurance.entity.Insured;

public class InsuredMapper {

    public static InsuredDto toDto(Insured entity) {
        InsuredDto dto = new InsuredDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPersonalNumber(entity.getPersonalNumber());
        dto.setEmail(entity.getEmail());
        dto.setPermanentAddress(AddressMapper.toDto(entity.getPermanentAddress()));
        if (entity.getCorrespondenceAddress() != null) dto.setCorrespondenceAddress(AddressMapper.toDto(entity.getCorrespondenceAddress()));

        return dto;
    }

    public static Insured toEntity(InsuredWithContractsDto dto) {
        Insured entity = new Insured();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPersonalNumber(dto.getPersonalNumber());
        entity.setEmail(dto.getEmail());
        entity.setPermanentAddress(AddressMapper.toEntity(dto.getPermanentAddress()));
        if (dto.getCorrespondenceAddress() != null) entity.setCorrespondenceAddress(AddressMapper.toEntity(dto.getCorrespondenceAddress()));
        entity.setContracts(ContractMapper.toEntitySet(dto.getContracts()));

        return entity;
    }

    public static InsuredWithContractsDto toContractDto(Insured entity) {
        InsuredWithContractsDto dto = new InsuredWithContractsDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPersonalNumber(entity.getPersonalNumber());
        dto.setEmail(entity.getEmail());
        dto.setPermanentAddress(AddressMapper.toDto(entity.getPermanentAddress()));
        if (entity.getCorrespondenceAddress() != null) dto.setCorrespondenceAddress(AddressMapper.toDto(entity.getCorrespondenceAddress()));
        dto.setContracts(ContractMapper.toDtoSet(entity.getContracts()));

        return dto;
    }
}
