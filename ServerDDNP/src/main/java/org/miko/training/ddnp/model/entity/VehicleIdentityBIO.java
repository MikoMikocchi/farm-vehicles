package org.miko.training.ddnp.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.miko.training.ddnp.model.enumeration.VehicleCategory;
import org.miko.training.ddnp.model.enumeration.VehicleType;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class VehicleIdentityBIO {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vehicle_category")
    private VehicleCategory vehicleCategory;

    private String brand;

    private String model;

    private String modification;

    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;

    @Column(name = "printable_manufacture_date")
    private String printableManufactureDate;

    @Column(name = "manufacture_date_iso")
    private String manufactureDate_ISO;

    @Column(name = "manufacture_date_iso_get")
    private String manufactureDate_ISO_get;

    @Column(name = "factory_number")
    private String factoryNumber;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "engine_number")
    private String engineNumber;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "series", column = @Column(name = "passport_series")),
            @AttributeOverride(name = "number", column = @Column(name = "passport_number")),
            @AttributeOverride(name = "issueDate", column = @Column(name = "passport_issue_date")),
            @AttributeOverride(name = "issueDate_ISO", column = @Column(name = "passport_issue_date_iso")),
            @AttributeOverride(name = "issueDate_ISO_get", column = @Column(name = "passport_issue_date_iso_get")),
            @AttributeOverride(name = "expireDate", column = @Column(name = "passport_expire_date")),
            @AttributeOverride(name = "expireDate_ISO", column = @Column(name = "passport_expire_date_iso")),
            @AttributeOverride(name = "expireDate_ISO_get", column = @Column(name = "passport_expire_date_iso_get")),
            @AttributeOverride(name = "issuer", column = @Column(name = "passport_issuer"))
    })
    private VehiclePassportBIO passport;
}
