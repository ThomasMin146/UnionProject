package com.union.insurance.controller;

import com.union.insurance.dto.InsuredDto;
import com.union.insurance.dto.InsuredWithContractsDto;
import com.union.insurance.mapper.InsuredMapper;
import com.union.insurance.service.InsuredService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class InsuredController implements InsuredApi{

    private final InsuredService insuredService;

    public InsuredController(InsuredService insuredService) {
        this.insuredService = insuredService;
    }

    public List<InsuredDto> getAllInsured() {
        log.info("Entering getAllInsured");

        List<InsuredDto> list = insuredService.getAllSorted().stream()
                .map(InsuredMapper::toDto)
                .toList();

        log.info("Leaving getAllInsured");
        return list;
    }

    public ResponseEntity<Long> createInsured(@Valid @RequestBody InsuredWithContractsDto dto) {
        log.info("Entering createInsured with data: {}", dto);

        Long id = insuredService.save(InsuredMapper.toEntity(dto));

        ResponseEntity<Long> body = ResponseEntity.status(HttpStatus.CREATED).body(id);

        log.info("Leaving createInsured");
        return body;
    }

    public ResponseEntity<InsuredWithContractsDto> getInsuredDetail(@PathVariable Long id) {
        log.info("Entering getInsuredDetail with id: {}", id);

        ResponseEntity<InsuredWithContractsDto> insuredResponseEntity = insuredService.getById(id)
                .map(InsuredMapper::toContractDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        log.info("Leaving getInsuredDetail");
        return insuredResponseEntity;
    }
}

