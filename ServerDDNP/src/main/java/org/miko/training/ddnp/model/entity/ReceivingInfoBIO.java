package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class ReceivingInfoBIO {

    @Column(name = "receiving_date")
    private LocalDate receivingDate;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "warehouse_address")
    private String warehouseAddress;

    @Column(name = "receiving_act_number")
    private String receivingActNumber;

    @Column(name = "receiving_condition")
    private String receivingCondition;

    @Column(name = "completeness_description")
    private String completenessDescription;

    @Column(name = "receiving_comment")
    private String receivingComment;
}
