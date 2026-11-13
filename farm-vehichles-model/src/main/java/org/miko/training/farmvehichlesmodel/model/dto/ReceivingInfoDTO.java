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
public class ReceivingInfoDTO {

    private LocalDate receivingDate;
    private String warehouseName;
    private String warehouseAddress;
    private String receivingActNumber;
    private String receivingCondition;
    private String completenessDescription;
    private String receivingComment;
}