package org.miko.training.ddnp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.miko.training.ddnp.model.enumeration.RegistrationPlateType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateRegistrationPlateDTO {

    /** Тип государственного регистрационного знака */
    private RegistrationPlateType type;

    /** Код региона */
    private String regionCode;

    /** Серия знака */
    private String series;

    /** Номер знака */
    private String number;
}
