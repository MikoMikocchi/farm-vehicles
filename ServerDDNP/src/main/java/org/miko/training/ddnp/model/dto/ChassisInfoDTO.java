package org.miko.training.ddnp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.miko.training.ddnp.model.enumeration.ChassisType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChassisInfoDTO {
    private ChassisType chassisType;
    private Integer wheelCount;
    private Integer trackWidth;
    private String wheelFormula;
}
