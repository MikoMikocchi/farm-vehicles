package org.miko.training.ddnp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DossierDTO {
    private String pk;
    private String uid;
    private String regNumber;
    private ReceptionInfoDTO receptionInfo;
    private VehicleIdentityDTO vehicleIdentity;
    private TechnicalInfoDTO technicalInfo;
    private SellerDTO seller;
    private PurchasingInfoDTO purchasingInfo;
    private ReceivingInfoDTO receivingInfo;
    private String warehouseId;
    private Boolean breakdowns;
    private List<EventDTO> eventHistory;
    private List<EventDTO> eventHistoryFull;
    private EventDTO currentState;
    private Boolean salePermitted;
    private Boolean saleRestrictionAdded;
}
