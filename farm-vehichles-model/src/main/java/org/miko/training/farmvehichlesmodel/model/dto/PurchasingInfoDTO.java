package org.miko.training.farmvehichlesmodel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasingInfoDTO {

    private LocalDate decisionDate;
    private LocalDate purchaseDate;
    private String purchaseContractNumber;
    private BigDecimal purchasePrice;
    private BigDecimal estimatedMarketValue;
    private String purchaseDecisionComment;
}
