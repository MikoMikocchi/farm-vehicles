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

import java.util.ArrayList;
import java.util.List;

@Getter
public class DossierEntityMapped {

    private final DossierEntity dossier;

    private final List<EventEntity> eventHistoryFull;

    public DossierEntityMapped(DossierDTO dto) {
        this.eventHistoryFull = eventHistoryFull(dto);
        this.dossier = dossier(dto);
    }

    private DossierEntity dossier(DossierDTO dto) {
        DossierEntity entity = new DossierEntity();
        entity.setUid(dto.getUid());
        entity.setRegNumber(dto.getRegNumber());
        entity.setReceptionInfo(receptionInfo(dto.getReceptionInfo()));
        entity.setVehicleIdentity(vehicleIdentity(dto.getVehicleIdentity()));
        entity.setTechnicalInfo(technicalInfo(dto.getTechnicalInfo()));
        entity.setSeller(seller(dto.getSeller()));
        entity.setPurchasingInfo(purchasingInfo(dto.getPurchasingInfo()));
        entity.setReceivingInfo(receivingInfo(dto.getReceivingInfo()));
        entity.setWarehouseId(dto.getWarehouseId());
        entity.setBreakdowns(dto.getBreakdowns());
        entity.setSalePermitted(dto.getSalePermitted());
        entity.setSaleRestrictionAdded(dto.getSaleRestrictionAdded());
        entity.setEventHistoryFull(eventHistoryFull);
        return entity;
    }

    private List<EventEntity> eventHistoryFull(DossierDTO dto) {
        List<EventEntity> events = new ArrayList<>();

        for (EventDTO event : dto.getEventHistoryFull()) {
            events.add(event(event, dto.getCurrentState()));
        }

        return events;
    }

    private EventEntity event(EventDTO dto, EventDTO currentState) {
        EventEntity entity = new EventEntity();
        entity.setId(dto.getId());
        entity.setCode(dto.getCode());
        entity.setWhenProcessed(dto.getWhenProcessed());
        entity.setActual(dto.isActual());
        entity.setState(isState(dto, currentState));
        entity.setComment(dto.getComment());
        entity.setCorrelationId(dto.getCorrelationId());
        return entity;
    }

    private boolean isState(EventDTO event, EventDTO currentState) {
        return currentState != null && currentState.getId().equals(event.getId());
    }

    private ReceptionInfoBIO receptionInfo(ReceptionInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        ReceptionInfoBIO bio = new ReceptionInfoBIO();
        bio.setReceptionDate(dto.getReceptionDate());
        bio.setResponsibleEmployeeName(fullName(dto.getResponsibleEmployeeName()));
        bio.setResponsibleEmployeePost(dto.getResponsibleEmployeePost());
        bio.setResponsibleEmployeeId(dto.getResponsibleEmployeeId());
        return bio;
    }

    private FullNameBIO fullName(FullNameDTO dto) {
        if (dto == null) {
            return null;
        }

        FullNameBIO bio = new FullNameBIO();
        bio.setLastName(dto.getLastName());
        bio.setFirstName(dto.getFirstName());
        bio.setMiddleName(dto.getMiddleName());
        return bio;
    }

    private VehicleIdentityBIO vehicleIdentity(VehicleIdentityDTO dto) {
        if (dto == null) {
            return null;
        }

        VehicleIdentityBIO bio = new VehicleIdentityBIO();
        bio.setVehicleType(dto.getVehicleType());
        bio.setVehicleCategory(dto.getVehicleCategory());
        bio.setBrand(dto.getBrand());
        bio.setModel(dto.getModel());
        bio.setModification(dto.getModification());
        bio.setManufactureDate(dto.getManufactureDate());
        bio.setPrintableManufactureDate(dto.getPrintableManufactureDate());
        bio.setManufactureDate_ISO(dto.getManufactureDate_ISO());
        bio.setManufactureDate_ISO_get(dto.getManufactureDate_ISO_get());
        bio.setFactoryNumber(dto.getFactoryNumber());
        bio.setIdentificationNumber(dto.getIdentificationNumber());
        bio.setEngineNumber(dto.getEngineNumber());
        bio.setRegistrationNumber(dto.getRegistrationNumber());
        bio.setPassport(passport(dto.getPassport()));
        return bio;
    }

    private VehiclePassportBIO passport(VehiclePassportDTO dto) {
        if (dto == null) {
            return null;
        }

        VehiclePassportBIO bio = new VehiclePassportBIO();
        bio.setSeries(dto.getSeries());
        bio.setNumber(dto.getNumber());
        bio.setIssueDate(dto.getIssueDate());
        bio.setIssueDate_ISO(dto.getIssueDate_ISO());
        bio.setIssueDate_ISO_get(dto.getIssueDate_ISO_get());
        bio.setExpireDate(dto.getExpireDate());
        bio.setExpireDate_ISO(dto.getExpireDate_ISO());
        bio.setExpireDate_ISO_get(dto.getExpireDate_ISO_get());
        bio.setIssuer(dto.getIssuer());
        return bio;
    }

    private TechnicalInfoBIO technicalInfo(TechnicalInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        TechnicalInfoBIO bio = new TechnicalInfoBIO();
        bio.setEngine(engine(dto.getEngine()));
        bio.setChassis(chassis(dto.getChassis()));
        bio.setTransmissionType(dto.getTransmissionType());
        bio.setFuelType(dto.getFuelType());
        bio.setEnginePower(dto.getEnginePower());
        bio.setEngineDisplacement(dto.getEngineDisplacement());
        bio.setCylinderCount(dto.getCylinderCount());
        bio.setOperatingWeight(dto.getOperatingWeight());
        bio.setMaxSpeed(dto.getMaxSpeed());
        bio.setOperatingHours(dto.getOperatingHours());
        bio.setLoadCapacity(dto.getLoadCapacity());
        bio.setSeatCount(dto.getSeatCount());
        return bio;
    }

    private EngineInfoBIO engine(EngineInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        EngineInfoBIO bio = new EngineInfoBIO();
        bio.setManufacturer(dto.getManufacturer());
        bio.setModel(dto.getModel());
        bio.setSerialNumber(dto.getSerialNumber());
        bio.setManufactureYear(dto.getManufactureYear());
        return bio;
    }

    private ChassisInfoBIO chassis(ChassisInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        ChassisInfoBIO bio = new ChassisInfoBIO();
        bio.setChassisType(dto.getChassisType());
        bio.setWheelCount(dto.getWheelCount());
        bio.setTrackWidth(dto.getTrackWidth());
        bio.setWheelFormula(dto.getWheelFormula());
        return bio;
    }

    private SellerBIO seller(SellerDTO dto) {
        if (dto == null) {
            return null;
        }

        SellerBIO bio = new SellerBIO();
        bio.setPerson(person(dto.getPerson()));
        bio.setOrganization(organization(dto.getOrganization()));
        bio.setDocuments(sellerDocument(dto.getDocuments()));
        bio.setContacts(contacts(dto.getContacts()));
        bio.setAddress(address(dto.getAddress()));
        return bio;
    }

    private PersonIdentityBIO person(PersonIdentityDTO dto) {
        if (dto == null) {
            return null;
        }

        PersonIdentityBIO bio = new PersonIdentityBIO();
        bio.setFullName(fullName(dto.getFullName()));
        bio.setBirthDate(dto.getBirthDate());
        bio.setTaxIdentificationNumber(dto.getTaxIdentificationNumber());
        return bio;
    }

    private OrganizationIdentityBIO organization(OrganizationIdentityDTO dto) {
        if (dto == null) {
            return null;
        }

        OrganizationIdentityBIO bio = new OrganizationIdentityBIO();
        bio.setFullName(dto.getFullName());
        bio.setShortName(dto.getShortName());
        bio.setInn(dto.getInn());
        bio.setOgrn(dto.getOgrn());
        bio.setKpp(dto.getKpp());
        bio.setRegistrationDate(dto.getRegistrationDate());
        return bio;
    }

    private SellerDocumentBIO sellerDocument(SellerDocumentDTO dto) {
        if (dto == null) {
            return null;
        }

        SellerDocumentBIO bio = new SellerDocumentBIO();
        bio.setDocumentType(dto.getDocumentType());
        bio.setDocumentNumber(dto.getDocumentNumber());
        bio.setIssueDate(dto.getIssueDate());
        bio.setIssuingAuthority(dto.getIssuingAuthority());
        return bio;
    }

    private ContactInfoBIO contacts(ContactInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        ContactInfoBIO bio = new ContactInfoBIO();
        bio.setPhoneNumber(dto.getPhoneNumber());
        bio.setEmail(dto.getEmail());
        bio.setAdditionalPhoneNumber(dto.getAdditionalPhoneNumber());
        return bio;
    }

    private AddressBIO address(AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        AddressBIO bio = new AddressBIO();
        bio.setPostalCode(dto.getPostalCode());
        bio.setRegion(dto.getRegion());
        bio.setDistrict(dto.getDistrict());
        bio.setCity(dto.getCity());
        bio.setStreet(dto.getStreet());
        bio.setHouse(dto.getHouse());
        bio.setBuilding(dto.getBuilding());
        bio.setApartment(dto.getApartment());
        return bio;
    }

    private PurchasingInfoBIO purchasingInfo(PurchasingInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        PurchasingInfoBIO bio = new PurchasingInfoBIO();
        bio.setDecisionDate(dto.getDecisionDate());
        bio.setPurchaseDate(dto.getPurchaseDate());
        bio.setPurchaseContractNumber(dto.getPurchaseContractNumber());
        bio.setPurchasePrice(dto.getPurchasePrice());
        bio.setEstimatedMarketValue(dto.getEstimatedMarketValue());
        bio.setPurchaseDecisionComment(dto.getPurchaseDecisionComment());
        return bio;
    }

    private ReceivingInfoBIO receivingInfo(ReceivingInfoDTO dto) {
        if (dto == null) {
            return null;
        }

        ReceivingInfoBIO bio = new ReceivingInfoBIO();
        bio.setReceivingDate(dto.getReceivingDate());
        bio.setWarehouseName(dto.getWarehouseName());
        bio.setWarehouseAddress(dto.getWarehouseAddress());
        bio.setReceivingActNumber(dto.getReceivingActNumber());
        bio.setReceivingCondition(dto.getReceivingCondition());
        bio.setCompletenessDescription(dto.getCompletenessDescription());
        bio.setReceivingComment(dto.getReceivingComment());
        return bio;
    }
}
