package org.miko.training.farmvehichlesmodel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationCertificateDTO {

    /** Серия свидетельства */
    private String series;

    /** Номер свидетельства */
    private String number;

    /** Дата выдачи */
    private LocalDate issueDate;

    /** Орган, выдавший свидетельство */
    private String issuer;
}
