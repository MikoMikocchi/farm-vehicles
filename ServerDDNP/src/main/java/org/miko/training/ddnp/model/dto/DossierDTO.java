package org.miko.training.ddnp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DossierDTO {
    private String id;
    private String uid;
    private String regNumber;
    private ReceptionInfoDTO receptionInfo;
    private VehicleIdentityDTO vehicleIdentity;
    private TechnicalInfoDTO technicalInfo;
    private SellerDTO seller;
    private PurchasingInfoDTO purchasinInfo;
    private ReceivingInfoDTO receivingInfo;
    private String warehouseId;
    private Boolean breakdowns;
    private Set<EventDTO> eventHistory;
    private Set<EventDTO> eventHistoryFull;
    private EventDTO currentState;
    private Boolean salePermitted;
    private Boolean saleRestrictionAdded;
}
