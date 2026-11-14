package org.miko.training.ddnp.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class SellerBIO {

    /** Данные физического лица */
    @Embedded
    private PersonIdentityBIO person;

    /** Данные организации */
    @Embedded
    private OrganizationIdentityBIO organization;

    /** Идентификаторы продавца */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "issueDate", column = @Column(name = "document_issue_date"))
    })
    private SellerDocumentBIO documents;

    /** Контактные данные */
    @Embedded
    private ContactInfoBIO contacts;

    /** Адрес продавца */
    @Embedded
    private AddressBIO address;
}
