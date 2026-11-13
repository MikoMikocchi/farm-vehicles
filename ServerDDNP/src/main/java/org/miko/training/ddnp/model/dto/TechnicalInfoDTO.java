package org.miko.training.ddnp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.miko.training.ddnp.model.enumeration.FuelType;
import org.miko.training.ddnp.model.enumeration.TransmissionType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalInfoDTO {

    /** Двигатель */
    private EngineInfoDTO engine;

    /** Ходовая часть */
    private ChassisInfoDTO chassis;

    /** Тип трансмиссии */
    private TransmissionType transmissionType;

    /** Тип топлива */
    private FuelType fuelType;

    /** Мощность двигателя, л.с. */
    private Integer enginePower;

    /** Объём двигателя, см³ */
    private Integer engineDisplacement;

    /** Количество цилиндров */
    private Integer cylinderCount;

    /** Эксплуатационная масса, кг */
    private Integer operatingWeight;

    /** Максимальная скорость, км/ч */
    private Integer maxSpeed;

    /** Количество моточасов */
    private Integer operatingHours;

    /** Грузоподъёмность, кг */
    private Integer loadCapacity;

    /** Количество мест */
    private Integer seatCount;
}
