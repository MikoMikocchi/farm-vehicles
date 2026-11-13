package org.miko.training.farmvehichlesmodel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.miko.training.farmvehichlesmodel.model.enumeration.VehicleCategory;
import org.miko.training.farmvehichlesmodel.model.enumeration.VehicleType;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleIdentityDTO {
    private VehicleType vehicleType;
    private VehicleCategory vehicleCategory;
    private String brand;
    private String model;
    private String modification;
    private LocalDate manufactureDate;
    private String printableManufactureDate;
    private String manufactureDate_ISO;
    private String manufactureDate_ISO_get;
    private String factoryNumber;
    private String identificationNumber;
    private String engineNumber;
    private String registrationNumber;
    private VehiclePassportDTO passport;
}
