package org.miko.training.ddnp.model.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "dossier", schema = "public")
public class DossierEntity {

    @Id
    @Column(name = "pk", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String pk;

    @Column(name = "uid")
    private String uid;

    @Column(name = "reg_number")
    private String regNumber;

    @Embedded
    private ReceptionInfoBIO receptionInfo;

    @Embedded
    private VehicleIdentityBIO vehicleIdentity;

    @Embedded
    private TechnicalInfoBIO technicalInfo;

    @Embedded
    private SellerBIO seller;

    @Embedded
    private PurchasingInfoBIO purchasingInfo;

    @Embedded
    private ReceivingInfoBIO receivingInfo;

    @Column(name = "warehouse_id")
    private String warehouseId;

    @Column(name = "breakdowns")
    private Boolean breakdowns;

    @Column(name = "sale_permitted")
    private Boolean salePermitted;

    @Column(name = "sale_restriction_added")
    private Boolean saleRestrictionAdded;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "dossier_events", schema = "public",
            joinColumns = @JoinColumn(name = "fk"))
    private List<EventEntity> eventHistoryFull = new ArrayList<>();
}
