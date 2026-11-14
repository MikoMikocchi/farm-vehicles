package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class OrganizationIdentityBIO {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "short_name")
    private String shortName;

    private String inn;

    private String ogrn;

    private String kpp;

    @Column(name = "registration_date")
    private LocalDate registrationDate;
}
