package com.union.insurance.repositories;

import com.union.insurance.entity.Insured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuredRepository extends JpaRepository<Insured, Long> {
    List<Insured> findAllByOrderByLastNameAsc();
}
