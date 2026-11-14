package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class VehiclePassportBIO {

    private String series;

    private String number;

    private LocalDate issueDate;

    private String issueDate_ISO;

    private String issueDate_ISO_get;

    private LocalDate expireDate;

    private String expireDate_ISO;

    private String expireDate_ISO_get;

    private String issuer;
}
