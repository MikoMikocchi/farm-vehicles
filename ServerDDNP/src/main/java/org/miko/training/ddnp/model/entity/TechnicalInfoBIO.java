package org.miko.training.ddnp.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.miko.training.ddnp.model.enumeration.FuelType;
import org.miko.training.ddnp.model.enumeration.TransmissionType;

@Getter
@Setter
@Embeddable
public class TechnicalInfoBIO {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "manufacturer", column = @Column(name = "engine_manufacturer")),
            @AttributeOverride(name = "model", column = @Column(name = "engine_model")),
            @AttributeOverride(name = "serialNumber", column = @Column(name = "engine_serial_number")),
            @AttributeOverride(name = "manufactureYear", column = @Column(name = "engine_manufacture_year"))
    })
    private EngineInfoBIO engine;

    @Embedded
    private ChassisInfoBIO chassis;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "transmission_type")
    private TransmissionType transmissionType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Column(name = "engine_power")
    private Integer enginePower;

    @Column(name = "engine_displacement")
    private Integer engineDisplacement;

    @Column(name = "cylinder_count")
    private Integer cylinderCount;

    @Column(name = "operating_weight")
    private Integer operatingWeight;

    @Column(name = "max_speed")
    private Integer maxSpeed;

    @Column(name = "operating_hours")
    private Integer operatingHours;

    @Column(name = "load_capacity")
    private Integer loadCapacity;

    @Column(name = "seat_count")
    private Integer seatCount;
}
