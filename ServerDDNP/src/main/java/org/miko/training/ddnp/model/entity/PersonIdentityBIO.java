package org.miko.training.ddnp.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class PersonIdentityBIO {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lastName", column = @Column(name = "person_last_name")),
            @AttributeOverride(name = "firstName", column = @Column(name = "person_first_name")),
            @AttributeOverride(name = "middleName", column = @Column(name = "person_middle_name"))
    })
    private FullNameBIO fullName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "tax_identification_number")
    private String taxIdentificationNumber;
}
