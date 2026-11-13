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
public class VehicleComponentReplacementDTO {

    /** Наименование заменённого компонента */
    private String componentType;

    /** Номер компонента */
    private String componentNumber;

    /** Дата замены */
    private LocalDate replacementDate;

    /** Примечание */
    private String comment;
}
