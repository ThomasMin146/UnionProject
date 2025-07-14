package com.union.insurance.mapper;

import com.union.insurance.dto.AddressDto;
import com.union.insurance.entity.Address;

public class AddressMapper {

    public static Address toEntity(AddressDto addressDto){
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setPostalCode(addressDto.getPostalCode());

        return address;
    }

    public static AddressDto toDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setHouseNumber(address.getHouseNumber());
        addressDto.setPostalCode(address.getPostalCode());

        return addressDto;
    }
}
