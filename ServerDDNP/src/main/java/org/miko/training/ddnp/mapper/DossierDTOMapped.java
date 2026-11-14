package org.miko.training.ddnp.mapper;

import lombok.Getter;
import org.miko.training.ddnp.model.dto.AddressDTO;
import org.miko.training.ddnp.model.dto.ChassisInfoDTO;
import org.miko.training.ddnp.model.dto.ContactInfoDTO;
import org.miko.training.ddnp.model.dto.DossierDTO;
import org.miko.training.ddnp.model.dto.EngineInfoDTO;
import org.miko.training.ddnp.model.dto.EventDTO;
import org.miko.training.ddnp.model.dto.FullNameDTO;
import org.miko.training.ddnp.model.dto.OrganizationIdentityDTO;
import org.miko.training.ddnp.model.dto.PersonIdentityDTO;
import org.miko.training.ddnp.model.dto.PurchasingInfoDTO;
import org.miko.training.ddnp.model.dto.ReceivingInfoDTO;
import org.miko.training.ddnp.model.dto.ReceptionInfoDTO;
import org.miko.training.ddnp.model.dto.SellerDTO;
import org.miko.training.ddnp.model.dto.SellerDocumentDTO;
import org.miko.training.ddnp.model.dto.TechnicalInfoDTO;
import org.miko.training.ddnp.model.dto.VehicleIdentityDTO;
import org.miko.training.ddnp.model.dto.VehiclePassportDTO;
import org.miko.training.ddnp.model.entity.AddressBIO;
import org.miko.training.ddnp.model.entity.ChassisInfoBIO;
import org.miko.training.ddnp.model.entity.ContactInfoBIO;
import org.miko.training.ddnp.model.entity.DossierEntity;
import org.miko.training.ddnp.model.entity.EngineInfoBIO;
import org.miko.training.ddnp.model.entity.EventEntity;
import org.miko.training.ddnp.model.entity.FullNameBIO;
import org.miko.training.ddnp.model.entity.OrganizationIdentityBIO;
import org.miko.training.ddnp.model.entity.PersonIdentityBIO;
import org.miko.training.ddnp.model.entity.PurchasingInfoBIO;
import org.miko.training.ddnp.model.entity.ReceivingInfoBIO;
import org.miko.training.ddnp.model.entity.ReceptionInfoBIO;
import org.miko.training.ddnp.model.entity.SellerBIO;
import org.miko.training.ddnp.model.entity.SellerDocumentBIO;
import org.miko.training.ddnp.model.entity.TechnicalInfoBIO;
import org.miko.training.ddnp.model.entity.VehicleIdentityBIO;
import org.miko.training.ddnp.model.entity.VehiclePassportBIO;
import org.miko.training.ddnp.model.enumeration.EventType;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Getter
public class DossierDTOMapped {

    /**
     * События, которые попадают в сокращённую историю: статусы досье и приёмка техники.
     */
    private static final Set<EventType> SHORT_HISTORY_EVENTS = EnumSet.of(
            EventType.DOSSIER_CREATED,
            EventType.VEHICLE_REGISTERED,
            EventType.VEHICLE_DATA_VERIFICATION_COMPLETED,
            EventType.VEHICLE_VALUATION_COMPLETED,
            EventType.PURCHASE_DECISION_MADE,
            EventType.VEHICLE_PURCHASED,
            EventType.VEHICLE_LISTED_FOR_SALE,
            EventType.VEHICLE_SOLD,
            EventType.VEHICLE_RECEIVED);

    private final DossierDTO dossier;

    private final List<EventDTO> eventHistoryFull;

    public DossierDTOMapped(DossierEntity entity) {
        this.eventHistoryFull = eventHistoryFull(entity);
        this.dossier = dossier(entity);
    }

    private DossierDTO dossier(DossierEntity entity) {
        return DossierDTO.builder()
                .pk(entity.getPk())
                .uid(entity.getUid())
                .regNumber(entity.getRegNumber())
                .receptionInfo(receptionInfo(entity.getReceptionInfo()))
                .vehicleIdentity(vehicleIdentity(entity.getVehicleIdentity()))
                .technicalInfo(technicalInfo(entity.getTechnicalInfo()))
                .seller(seller(entity.getSeller()))
                .purchasingInfo(purchasingInfo(entity.getPurchasingInfo()))
                .receivingInfo(receivingInfo(entity.getReceivingInfo()))
                .warehouseId(entity.getWarehouseId())
                .breakdowns(entity.getBreakdowns())
                .salePermitted(entity.getSalePermitted())
                .saleRestrictionAdded(entity.getSaleRestrictionAdded())
                .eventHistory(eventHistory(eventHistoryFull))
                .eventHistoryFull(eventHistoryFull)
                .currentState(currentState(entity))
                .build();
    }

    private List<EventDTO> eventHistory(List<EventDTO> eventHistoryFull) {
        List<EventDTO> events = new ArrayList<>();

        for (EventDTO event : eventHistoryFull) {
            if (SHORT_HISTORY_EVENTS.contains(event.getCode())) {
                events.add(event);
            }
        }

        return events;
    }

    private List<EventDTO> eventHistoryFull(DossierEntity entity) {
        List<EventDTO> events = new ArrayList<>();

        for (EventEntity event : entity.getEventHistoryFull()) {
            events.add(event(event));
        }

        return events;
    }

    private EventDTO currentState(DossierEntity entity) {
        for (EventEntity event : entity.getEventHistoryFull()) {
            if (event.isState()) {
                return event(event);
            }
        }

        return null;
    }

    private EventDTO event(EventEntity entity) {
        return EventDTO.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .whenProcessed(entity.getWhenProcessed())
                .isActual(entity.isActual())
                .comment(entity.getComment())
                .correlationId(entity.getCorrelationId())
                .build();
    }

    private ReceptionInfoDTO receptionInfo(ReceptionInfoBIO bio) {
        if (bio == null) {
            return null;
        }

        return ReceptionInfoDTO.builder()
                .receptionDate(bio.getReceptionDate())
                .responsibleEmployeeName(fullName(bio.getResponsibleEmployeeName()))
                .responsibleEmployeePost(bio.getResponsibleEmployeePost())
                .responsibleEmployeeId(bio.getResponsibleEmployeeId())
                .build();
    }

    private FullNameDTO fullName(FullNameBIO bio) {
        if (bio == null) {
            return null;
        }

        return FullNameDTO.builder()
                .lastName(bio.getLastName())
                .firstName(bio.getFirstName())
                .middleName(bio.getMiddleName())
                .build();
    }

    private VehicleIdentityDTO vehicleIdentity(VehicleIdentityBIO bio) {
        if (bio == null) {
            return null;
        }

        return VehicleIdentityDTO.builder()
                .vehicleType(bio.getVehicleType())
                .vehicleCategory(bio.getVehicleCategory())
                .brand(bio.getBrand())
                .model(bio.getModel())
                .modification(bio.getModification())
                .manufactureDate(bio.getManufactureDate())
                .printableManufactureDate(bio.getPrintableManufactureDate())
                .manufactureDate_ISO(bio.getManufactureDate_ISO())
                .manufactureDate_ISO_get(bio.getManufactureDate_ISO_get())
                .factoryNumber(bio.getFactoryNumber())
                .identificationNumber(bio.getIdentificationNumber())
                .engineNumber(bio.getEngineNumber())
                .registrationNumber(bio.getRegistrationNumber())
                .passport(passport(bio.getPassport()))
                .build();
    }

    private VehiclePassportDTO passport(VehiclePassportBIO bio) {
        if (bio == null) {
            return null;
        }

        return VehiclePassportDTO.builder()
                .series(bio.getSeries())
                .number(bio.getNumber())
                .issueDate(bio.getIssueDate())
                .issueDate_ISO(bio.getIssueDate_ISO())
                .issueDate_ISO_get(bio.getIssueDate_ISO_get())
                .expireDate(bio.getExpireDate())
                .expireDate_ISO(bio.getExpireDate_ISO())
                .expireDate_ISO_get(bio.getExpireDate_ISO_get())
                .issuer(bio.getIssuer())
                .build();
    }

    private TechnicalInfoDTO technicalInfo(TechnicalInfoBIO bio) {
        if (bio == null) {
            return null;
        }

        return TechnicalInfoDTO.builder()
                .engine(engine(bio.getEngine()))
                .chassis(chassis(bio.getChassis()))
                .transmissionType(bio.getTransmissionType())
                .fuelType(bio.getFuelType())
                .enginePower(bio.getEnginePower())
                .engineDisplacement(bio.getEngineDisplacement())
                .cylinderCount(bio.getCylinderCount())
                .operatingWeight(bio.getOperatingWeight())
                .maxSpeed(bio.getMaxSpeed())
                .operatingHours(bio.getOperatingHours())
                .loadCapacity(bio.getLoadCapacity())
                .seatCount(bio.getSeatCount())
                .build();
    }

    private EngineInfoDTO engine(EngineInfoBIO bio) {
        if (bio == null) {
            return null;
        }

        return EngineInfoDTO.builder()
                .manufacturer(bio.getManufacturer())
                .model(bio.getModel())
                .serialNumber(bio.getSerialNumber())
                .manufactureYear(bio.getManufactureYear())
                .build();
    }

    private ChassisInfoDTO chassis(ChassisInfoBIO bio) {
        if (bio == null) {
            return null;
        }

        return ChassisInfoDTO.builder()
                .chassisType(bio.getChassisType())
                .wheelCount(bio.getWheelCount())
                .trackWidth(bio.getTrackWidth())
                .wheelFormula(bio.getWheelFormula())
                .build();
    }

    private SellerDTO seller(SellerBIO bio) {
        if (bio == null) {
            return null;
        }

        return SellerDTO.builder()
                .person(person(bio.getPerson()))
                .organization(organization(bio.getOrganization()))
                .documents(sellerDocument(bio.getDocuments()))
                .contacts(contacts(bio.getContacts()))
                .address(address(bio.getAddress()))
                .build();
    }

    private PersonIdentityDTO person(PersonIdentityBIO bio) {
        if (bio == null) {
            return null;
        }

        return PersonIdentityDTO.builder()
                .fullName(fullName(bio.getFullName()))
                .birthDate(bio.getBirthDate())
                .taxIdentificationNumber(bio.getTaxIdentificationNumber())
                .build();
    }

    private OrganizationIdentityDTO organization(OrganizationIdentityBIO bio) {
        if (bio == null) {
            return null;
        }

        return OrganizationIdentityDTO.builder()
                .fullName(bio.getFullName())
                .shortName(bio.getShortName())
                .inn(bio.getInn())
                .ogrn(bio.getOgrn())
                .kpp(bio.getKpp())
                .registrationDate(bio.getRegistrationDate())
                .build();
    }

    private SellerDocumentDTO sellerDocument(SellerDocumentBIO bio) {
        if (bio == null) {
            return null;
        }

        return SellerDocumentDTO.builder()
                .documentType(bio.getDocumentType())
                .documentNumber(bio.getDocumentNumber())
                .issueDate(bio.getIssueDate())
                .issuingAuthority(bio.getIssuingAuthority())
                .build();
    }

    private ContactInfoDTO contacts(ContactInfoBIO bio) {
        if (bio == null) {
            return null;
        }

        return ContactInfoDTO.builder()
                .phoneNumber(bio.getPhoneNumber())
                .email(bio.getEmail())
                .additionalPhoneNumber(bio.getAdditionalPhoneNumber())
                .build();
    }

    private AddressDTO address(AddressBIO bio) {
        if (bio == null) {
            return null;
        }

        return AddressDTO.builder()
                .postalCode(bio.getPostalCode())
                .region(bio.getRegion())
                .district(bio.getDistrict())
                .city(bio.getCity())
                .street(bio.getStreet())
                .house(bio.getHouse())
                .building(bio.getBuilding())
                .apartment(bio.getApartment())
                .build();
    }

    private PurchasingInfoDTO purchasingInfo(PurchasingInfoBIO bio) {
        if (bio == null) {
            return null;
        }

        return PurchasingInfoDTO.builder()
                .decisionDate(bio.getDecisionDate())
                .purchaseDate(bio.getPurchaseDate())
                .purchaseContractNumber(bio.getPurchaseContractNumber())
                .purchasePrice(bio.getPurchasePrice())
                .estimatedMarketValue(bio.getEstimatedMarketValue())
                .purchaseDecisionComment(bio.getPurchaseDecisionComment())
                .build();
    }

    private ReceivingInfoDTO receivingInfo(ReceivingInfoBIO bio) {
        if (bio == null) {
            return null;
        }

        return ReceivingInfoDTO.builder()
                .receivingDate(bio.getReceivingDate())
                .warehouseName(bio.getWarehouseName())
                .warehouseAddress(bio.getWarehouseAddress())
                .receivingActNumber(bio.getReceivingActNumber())
                .receivingCondition(bio.getReceivingCondition())
                .completenessDescription(bio.getCompletenessDescription())
                .receivingComment(bio.getReceivingComment())
                .build();
    }
}
