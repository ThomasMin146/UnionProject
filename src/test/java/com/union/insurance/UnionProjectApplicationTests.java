package com.union.insurance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.union.insurance.dto.AddressDto;
import com.union.insurance.dto.InsuredWithContractsDto;
import com.union.insurance.dto.PropertyInsuranceDto;
import com.union.insurance.enums.PropertyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UnionProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void whenCreatingInsuredWithContracts_thenContractsShouldBePersisted() throws Exception {
		InsuredWithContractsDto dto = new InsuredWithContractsDto();
		dto.setFirstName("Anna");
		dto.setLastName("Smith");
		dto.setPersonalNumber("987654321");
		dto.setEmail("anna.smith@example.com");

		AddressDto address = new AddressDto("12345", "City", "Street", "99");
		dto.setPermanentAddress(address);

		PropertyInsuranceDto property = new PropertyInsuranceDto();
		property.setContractNumber("PROP-001");
		property.setStartDate(LocalDate.now());
		property.setPropertyType(PropertyType.BYT);
		property.setPropertyAddress(address);
		property.setPropertyValue(BigDecimal.valueOf(100_000));

		dto.setContracts(Set.of(property));

		String json = objectMapper.writeValueAsString(dto);

		MvcResult result = mockMvc.perform(post("/insured")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isCreated())
				.andReturn();

		Long id = objectMapper.readValue(result.getResponse().getContentAsString(), Long.class);

		MvcResult getResult = mockMvc.perform(get("/insured/" + id))
				.andExpect(status().isOk())
				.andReturn();

		InsuredWithContractsDto retrieved = objectMapper.readValue(getResult.getResponse().getContentAsString(), InsuredWithContractsDto.class);

		Assertions.assertFalse(retrieved.getContracts().isEmpty(), "Contracts should be persisted and retrieved");
	}

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

		MvcResult postResult = mockMvc.perform(post("/insured")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andExpect(status().isCreated())
				.andReturn();

		Long id = objectMapper.readValue(postResult.getResponse().getContentAsString(), Long.class);

		MvcResult getResult = mockMvc.perform(get("/insured/{id}", id))
				.andExpect(status().isOk())
				.andReturn();

		String responseJson = getResult.getResponse().getContentAsString();
		InsuredWithContractsDto responseDto = objectMapper.readValue(responseJson, InsuredWithContractsDto.class);

		Assertions.assertEquals(responseDto.getPermanentAddress(), responseDto.getCorrespondenceAddress(),
				"Correspondence address should match permanent address when omitted");
	}

}
