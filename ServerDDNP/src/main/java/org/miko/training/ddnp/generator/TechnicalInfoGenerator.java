package org.miko.training.ddnp.generator;

import org.miko.training.ddnp.model.dto.ChassisInfoDTO;
import org.miko.training.ddnp.model.dto.EngineInfoDTO;
import org.miko.training.ddnp.model.dto.TechnicalInfoDTO;
import org.miko.training.ddnp.model.enumeration.ChassisType;
import org.miko.training.ddnp.model.enumeration.FuelType;
import org.miko.training.ddnp.model.enumeration.TransmissionType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TechnicalInfoGenerator {

    private static final List<String> ENGINE_MANUFACTURERS = List.of(
            "Cummins",
            "Perkins",
            "Deutz",
            "John Deere Power Systems",
            "FPT Industrial",
            "Yanmar",
            "Kubota",
            "Volvo Penta",
            "MTU",
            "Isuzu");

    private static final List<String> ENGINE_MODELS = List.of(
            "B6.7",
            "1104D-44T",
            "TCD 6.1 L4",
            "PowerTech 6068",
            "NEF 67",
            "4TNV98",
            "V2403",
            "D5A",
            "OM 936",
            "4JJ1");

    private static final List<Integer> WHEEL_COUNTS = List.of(4, 6, 8);

    private static final List<String> WHEEL_FORMULAS = List.of(
            "4x2",
            "4x4",
            "6x6",
            "8x8");

    public TechnicalInfoDTO generate(LocalDate manufactureDate) {
        return TechnicalInfoDTO.builder()
                .engine(engine(manufactureDate))
                .chassis(chassis())
                .transmissionType(Randoms.randomEnum(TransmissionType.class))
                .fuelType(Randoms.randomEnum(FuelType.class))
                .enginePower(Randoms.randomInt(200, 1000))
                .engineDisplacement(Randoms.randomInt(150, 500))
                .cylinderCount(Randoms.randomInt(4, 12))
                .operatingWeight(Randoms.randomInt(400, 2000))
                .maxSpeed(Randoms.randomInt(30, 120))
                .operatingHours(Randoms.randomInt(50_000, 1_000_000))
                .loadCapacity(Randoms.randomInt(150, 1000))
                .seatCount(Randoms.randomInt(1, 8))
                .build();
    }

    private EngineInfoDTO engine(LocalDate manufactureDate) {
        return EngineInfoDTO.builder()
                .manufacturer(Randoms.randomElement(ENGINE_MANUFACTURERS))
                .model(Randoms.randomElement(ENGINE_MODELS))
                .serialNumber(Randoms.randomDigits(9))
                .manufactureYear(manufactureDate.getYear())
                .build();
    }

    private ChassisInfoDTO chassis() {
        return ChassisInfoDTO.builder()
                .chassisType(Randoms.randomEnum(ChassisType.class))
                .wheelCount(Randoms.randomElement(WHEEL_COUNTS))
                .trackWidth(Randoms.randomInt(10, 100))
                .wheelFormula(Randoms.randomElement(WHEEL_FORMULAS))
                .build();
    }
}
