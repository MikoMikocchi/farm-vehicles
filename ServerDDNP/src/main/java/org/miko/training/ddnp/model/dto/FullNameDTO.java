package org.miko.training.ddnp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullNameDTO {
    private String lastName;
    private String firstName;
    private String middleName;
}
