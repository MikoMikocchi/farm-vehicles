package org.miko.training.ddnp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePassportDTO {
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
