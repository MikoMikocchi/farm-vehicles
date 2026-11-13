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
public class PersonIdentityDTO {

    private FullNameDTO fullName;

    private LocalDate birthDate;

    private String taxIdentificationNumber; // типо ИНН, но для человека
}
