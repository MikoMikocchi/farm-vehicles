package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AddressBIO {

    @Column(name = "postal_code")
    private String postalCode;

    private String region;

    private String district;

    private String city;

    private String street;

    private String house;

    private String building;

    private String apartment;
}
