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
public class ReceptionInfoDTO {
    private LocalDate receptionDate;
    private FullNameDTO responsibleEmployeeName;
    private String responsibleEmployeePost;
    private String responsibleEmployeeId;
}
