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
public class SellerDocumentDTO {
    private String documentType;
    private String documentNumber;
    private LocalDate issueDate;
    private String issuingAuthority;
}
