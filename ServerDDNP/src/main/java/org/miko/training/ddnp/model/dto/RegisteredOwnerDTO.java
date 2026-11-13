package org.miko.training.ddnp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.miko.training.ddnp.model.enumeration.OwnerType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredOwnerDTO {

    /** Тип владельца */
    private OwnerType ownerType;

    /** ФИО или наименование организации */
    private String name;

    /** ИНН владельца, если применимо */
    private String inn;

    /** Адрес регистрации или местонахождения */
    private String address;
}
