package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.miko.training.ddnp.model.enumeration.ChassisType;

@Getter
@Setter
@Embeddable
public class ChassisInfoBIO {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "chassis_type")
    private ChassisType chassisType;

    @Column(name = "wheel_count")
    private Integer wheelCount;

    @Column(name = "track_width")
    private Integer trackWidth;

    @Column(name = "wheel_formula")
    private String wheelFormula;
}
