package com.union.insurance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"contracts"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Insured {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String personalNumber;
    @Email
    @NotBlank
    private String email;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "postalCode", column = @Column(name = "permanent_postal_code")),
            @AttributeOverride(name = "city", column = @Column(name = "permanent_city")),
            @AttributeOverride(name = "street", column = @Column(name = "permanent_street")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "permanent_house_number"))
    })
    private Address permanentAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "postalCode", column = @Column(name = "correspondence_postal_code")),
            @AttributeOverride(name = "city", column = @Column(name = "correspondence_city")),
            @AttributeOverride(name = "street", column = @Column(name = "correspondence_street")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "correspondence_house_number"))
    })
    private Address correspondenceAddress;

    @OneToMany(mappedBy = "insured", cascade = CascadeType.ALL)
    private Set<Contract> contracts = new HashSet<>();

    @PrePersist
    public void fillCorrespondenceAddressIfMissing() {
        if (correspondenceAddress == null) {
            correspondenceAddress = permanentAddress;
        }
    }
}

