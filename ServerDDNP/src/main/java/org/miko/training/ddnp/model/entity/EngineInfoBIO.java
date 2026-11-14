package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class EngineInfoBIO {

    private String manufacturer;

    private String model;

    private String serialNumber;

    private Integer manufactureYear;
}
