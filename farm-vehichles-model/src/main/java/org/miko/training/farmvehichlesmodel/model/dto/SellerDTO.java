package org.miko.training.farmvehichlesmodel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO {
    /** Данные физического лица */
    private PersonIdentityDTO person;

    /** Данные организации */
    private OrganizationIdentityDTO organization;

    /** Идентификаторы продавца */
    private SellerDocumentDTO documents;

    /** Контактные данные */
    private ContactInfoDTO contacts;

    /** Адрес продавца */
    private AddressDTO address;
}
