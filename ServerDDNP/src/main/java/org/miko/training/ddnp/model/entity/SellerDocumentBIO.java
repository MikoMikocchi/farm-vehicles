package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class SellerDocumentBIO {

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_number")
    private String documentNumber;

    private LocalDate issueDate;

    @Column(name = "issuing_authority")
    private String issuingAuthority;
}
