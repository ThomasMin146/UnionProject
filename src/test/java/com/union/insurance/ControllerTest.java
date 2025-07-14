package com.union.insurance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.union.insurance.dto.AddressDto;
import com.union.insurance.dto.InsuredWithContractsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCorrespondenceAddressOmitted_thenItDefaultsToPermanentAddress() throws Exception {
        InsuredWithContractsDto dto = new InsuredWithContractsDto();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setPersonalNumber("123456789");
        dto.setEmail("john.doe@example.com");

        AddressDto address = new AddressDto();
        address.setPostalCode("12345");
        address.setCity("Cityville");
        address.setStreet("Main St");
        address.setHouseNumber("42");

        dto.setPermanentAddress(address);

        String requestJson = objectMapper.writeValueAsString(dto);

        // perform post
        MvcResult postResult = mockMvc.perform(post("/insured")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();

        Long id = objectMapper.readValue(postResult.getResponse().getContentAsString(), Long.class);

        // perform get
        MvcResult getResult = mockMvc.perform(get("/insured/{id}", id))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = getResult.getResponse().getContentAsString();
        InsuredWithContractsDto responseDto = objectMapper.readValue(responseJson, InsuredWithContractsDto.class);

        // Verify correspondenceAddress is same as permanentAddress
        Assertions.assertEquals(responseDto.getPermanentAddress(), responseDto.getCorrespondenceAddress(),
                "Correspondence address should match permanent address when omitted");
    }
}
