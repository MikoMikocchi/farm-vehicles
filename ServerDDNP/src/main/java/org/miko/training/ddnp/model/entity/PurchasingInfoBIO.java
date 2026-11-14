package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class PurchasingInfoBIO {

    @Column(name = "decision_date")
    private LocalDate decisionDate;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "purchase_contract_number")
    private String purchaseContractNumber;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @Column(name = "estimated_market_value")
    private BigDecimal estimatedMarketValue;

    @Column(name = "purchase_decision_comment")
    private String purchaseDecisionComment;
}
