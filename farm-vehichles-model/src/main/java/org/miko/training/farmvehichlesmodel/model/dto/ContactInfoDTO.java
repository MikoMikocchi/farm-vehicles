package org.miko.training.farmvehichlesmodel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDTO {
    private String phoneNumber;
    private String email;
    private String additionalPhoneNumber;
}
