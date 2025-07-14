package com.union.insurance.service;

import com.union.insurance.entity.Insured;

import java.util.List;
import java.util.Optional;

public interface InsuredService {

    List<Insured> getAllSorted();

    Long save(Insured insured);

    Optional<Insured> getById(Long id);
}
