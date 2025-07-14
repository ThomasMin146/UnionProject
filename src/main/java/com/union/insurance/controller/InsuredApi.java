package com.union.insurance.controller;

import com.union.insurance.dto.InsuredDto;
import com.union.insurance.dto.InsuredWithContractsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/insured")
public interface InsuredApi {

    @GetMapping
    List<InsuredDto> getAllInsured();

    @PostMapping
    ResponseEntity<Long> createInsured(InsuredWithContractsDto dto);

    @GetMapping("/{id}")
    ResponseEntity<InsuredWithContractsDto> getInsuredDetail(Long id);
}
