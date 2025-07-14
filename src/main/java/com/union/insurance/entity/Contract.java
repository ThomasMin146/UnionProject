package com.union.insurance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Inheritance
@Getter
@Setter
@ToString(exclude = {"insured"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String contractNumber;
    @NotNull
    private LocalDate startDate;
    @ManyToOne
    @JoinColumn(name = "insured_id", referencedColumnName = "id")
    private Insured insured;
}
