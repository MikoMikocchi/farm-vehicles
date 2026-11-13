package org.miko.training.ddnp.generator;

import lombok.extern.slf4j.Slf4j;
import org.miko.training.ddnp.model.dto.DossierDTO;
import org.miko.training.ddnp.model.dto.EventDTO;
import org.miko.training.ddnp.model.enumeration.EventType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class DossierGenerator {

    private static final int STAGE_MONTHS = 2;

    private final EventHistoryGenerator eventHistoryGenerator;
    private final ReceptionInfoGenerator receptionInfoGenerator;
    private final VehicleIdentityGenerator vehicleIdentityGenerator;

    public DossierGenerator(EventHistoryGenerator eventHistoryGenerator,
                            ReceptionInfoGenerator receptionInfoGenerator,
                            VehicleIdentityGenerator vehicleIdentityGenerator) {
        this.eventHistoryGenerator = eventHistoryGenerator;
        this.receptionInfoGenerator = receptionInfoGenerator;
        this.vehicleIdentityGenerator = vehicleIdentityGenerator;
    }

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

        DossierDTO dossier = DossierDTO.builder()
                .id(UUID.randomUUID().toString())
                .uid(UUID.randomUUID().toString())
                .receptionInfo(receptionInfoGenerator.generate(receptionDate))
                .vehicleIdentity(vehicleIdentityGenerator.generate())
                .currentState(currentState)
                .eventHistory(new LinkedHashSet<>(eventHistory))
                .eventHistoryFull(new LinkedHashSet<>(eventHistoryFull))
                .build();

        save(dossier);
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
