package org.miko.training.ddnp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.miko.training.ddnp.model.enumeration.RegistrationStatus;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRegistrationDTO {

    /** Государственный регистрационный знак */
    private StateRegistrationPlateDTO registrationPlate;

    /** Свидетельство о государственной регистрации */
    private RegistrationCertificateDTO certificate;

    /** Сведения о зарегистрированном владельце */
    private RegisteredOwnerDTO owner;

    /** Орган Гостехнадзора, осуществивший регистрацию */
    private String registrationAuthority;

    /** Дата государственной регистрации */
    private LocalDate registrationDate;

    /** Дата снятия с государственного учета */
    private LocalDate deregistrationDate;

    /** Основание регистрации */
    private String registrationBasis;

    /** Текущий регистрационный статус */
    private RegistrationStatus status;

    /** История замены основных компонентов */
    private List<VehicleComponentReplacementDTO> componentReplacements;
}