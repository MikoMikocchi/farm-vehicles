package org.miko.training.ddnp.generator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.miko.training.ddnp.model.dto.DossierDTO;
import org.miko.training.ddnp.model.dto.EventDTO;
import org.miko.training.ddnp.model.dto.PurchasingInfoDTO;
import org.miko.training.ddnp.model.dto.ReceivingInfoDTO;
import org.miko.training.ddnp.model.dto.VehicleIdentityDTO;
import org.miko.training.ddnp.model.enumeration.EventType;
import org.miko.training.ddnp.utils.EventUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class DossierGenerator {

    private static final int STAGE_MONTHS = 2;

    private static final DateTimeFormatter UID_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");

    private int dossierCounter = 0;

    private final EventHistoryGenerator eventHistoryGenerator;
    private final ReceptionInfoGenerator receptionInfoGenerator;
    private final VehicleIdentityGenerator vehicleIdentityGenerator;
    private final TechnicalInfoGenerator technicalInfoGenerator;
    private final SellerGenerator sellerGenerator;
    private final PurchasingInfoGenerator purchasingInfoGenerator;
    private final ReceivingInfoGenerator receivingInfoGenerator;

    public void generate() {
        int stage = 0;
        for (Map.Entry<EventType, Integer> entry : distribution().entrySet()) {
            generate(entry.getKey(), entry.getValue(), stage++);
        }
    }

    private Map<EventType, Integer> distribution() {
        Map<EventType, Integer> distribution = new LinkedHashMap<>();
        distribution.put(EventType.DOSSIER_CREATED, 20);
        distribution.put(EventType.VEHICLE_REGISTERED, 230);
        distribution.put(EventType.VEHICLE_DATA_VERIFICATION_COMPLETED, 30);
        distribution.put(EventType.VEHICLE_VALUATION_COMPLETED, 10);
        distribution.put(EventType.PURCHASE_DECISION_MADE, 10);
        distribution.put(EventType.VEHICLE_PURCHASED, 10);
        distribution.put(EventType.VEHICLE_LISTED_FOR_SALE, 40);
        distribution.put(EventType.VEHICLE_SOLD, 150);
        return distribution;
    }

    private void generate(EventType status, int count, int stage) {
        for (int i = 0; i < count; i++) {
            generate(status, stage);
        }
        log.info("Generated {} dossiers with status {}", count, status);
    }

    private void generate(EventType status, int stage) {
        List<EventDTO> eventHistoryFull = eventHistoryGenerator.generateHistory(status, statusTime(stage));
        List<EventDTO> eventHistory = eventHistoryGenerator.generateEventHistory(eventHistoryFull);

        // Последнее событие полной истории — это событие статуса, оно же записывается в currentState.
        EventDTO currentState = eventHistoryFull.get(eventHistoryFull.size() - 1);

        // Дата приёмки — дата первого события досье.
        LocalDate receptionDate = eventHistoryFull.get(0).getWhenProcessed().toLocalDate();

        VehicleIdentityDTO vehicleIdentity = vehicleIdentityGenerator.generate();

        // Данные о приобретении есть только в досье, где было событие VEHICLE_PURCHASED.
        PurchasingInfoDTO purchasingInfo = EventUtils.isActualEventPresent(eventHistoryFull, EventType.VEHICLE_PURCHASED)
                ? purchasingInfoGenerator.generate(eventHistoryFull)
                : null;

        // Данные о приёмке есть только в досье, где было событие VEHICLE_RECEIVED.
        ReceivingInfoDTO receivingInfo = EventUtils.isActualEventPresent(eventHistoryFull, EventType.VEHICLE_RECEIVED)
                ? receivingInfoGenerator.generate(eventHistoryFull)
                : null;

        DossierDTO dossier = DossierDTO.builder()
                .pk(UUID.randomUUID().toString())
                .uid(uid(receptionDate))
                .receptionInfo(receptionInfoGenerator.generate(receptionDate))
                .vehicleIdentity(vehicleIdentity)
                .technicalInfo(technicalInfoGenerator.generate(vehicleIdentity.getManufactureDate()))
                .seller(sellerGenerator.generate())
                .purchasingInfo(purchasingInfo)
                .receivingInfo(receivingInfo)
                .currentState(currentState)
                .eventHistory(eventHistory)
                .eventHistoryFull(eventHistoryFull)
                .salePermitted(Randoms.randomBoolean())
                .saleRestrictionAdded(Randoms.randomBoolean())
                .breakdowns(Randoms.randomBoolean())
                .warehouseId(UUID.randomUUID().toString())
                .build();

        save(dossier);
    }

    /**
     * uid = "1000" + ddMMyyyy даты приёмки + порядковый номер досье (6 знаков, с ведущими нулями).
     */
    private String uid(LocalDate receptionDate) {
        dossierCounter++;
        return "1000" + receptionDate.format(UID_DATE_FORMAT) + String.format("%06d", dossierCounter);
    }

    private LocalDateTime statusTime(int stage) {
        LocalDate now = LocalDate.now();
        LocalDate whenProcessed = Randoms.randomDate(
                now.minusMonths((long) STAGE_MONTHS * (stage + 1)),
                now.minusMonths((long) STAGE_MONTHS * stage));

        return whenProcessed.atStartOfDay();
    }

    /**
     * Заглушка: здесь DossierDTO будет преобразовываться в entity и сохраняться.
     */
    private void save(DossierDTO dossier) {
    }
}
