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
public class OrganizationIdentityDTO {
    private String fullName;
    private String shortName;
    private String inn;
    private String ogrn;
    private String kpp;
    private LocalDate registrationDate;
}
