package com.union.insurance.service;

import com.union.insurance.entity.Contract;
import com.union.insurance.entity.Insured;
import com.union.insurance.exception.DuplicateContractNumberException;
import com.union.insurance.repositories.InsuredRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InsuredServiceImpl implements InsuredService {

    private final InsuredRepository insuredRepository;

    public InsuredServiceImpl(InsuredRepository insuredRepository) {
        this.insuredRepository = insuredRepository;
    }

    public List<Insured> getAllSorted() {
        log.debug("Entering getAllSorted");

        List<Insured> result = insuredRepository.findAllByOrderByLastNameAsc();

        log.debug("Leaving getAllSorted");
        return result;
    }

    @Transactional
    public Long save(Insured insured) {
        try {
            log.debug("Entering save with data: {}", insured);

            if (insured.getContracts() != null) {
                for (Contract contract : insured.getContracts()) {
                    contract.setInsured(insured);
                }
            }
            insured.fillCorrespondenceAddressIfMissing();
            Long id = insuredRepository.save(insured).getId();

            log.debug("Leaving save");
            return id;
        } catch (DataIntegrityViolationException e) {
            log.error("Unique constraint violated: {}", e.getMessage());
            throw new DuplicateContractNumberException("Contract number must be unique.", e);
        }
    }

    public Optional<Insured> getById(Long id) {
        log.debug("Entering getById with id: {}", id);

        if (id == null) throw new IllegalArgumentException("Id cannot be null");

        Optional<Insured> result = insuredRepository.findById(id);

        log.debug("Leaving getById");
        return result;
    }
}
