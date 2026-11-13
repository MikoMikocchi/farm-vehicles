package org.miko.training.ddnp.generator;

import lombok.extern.slf4j.Slf4j;
import org.miko.training.ddnp.model.dto.DossierDTO;
import org.miko.training.ddnp.model.dto.EventDTO;
import org.miko.training.ddnp.model.enumeration.EventType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class DossierGenerator {

    public void generate() {
        distribution().forEach(this::generate);
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

    private void generate(EventType status, int count) {
        for (int i = 0; i < count; i++) {
            generate(status);
        }
        log.info("Generated {} dossiers with status {}", count, status);
    }

    private void generate(EventType status) {
        DossierDTO dossier = DossierDTO.builder()
                .id(UUID.randomUUID().toString())
                .uid(UUID.randomUUID().toString())
                .currentState(currentState(status))
                .build();

        save(dossier);
    }

    private EventDTO currentState(EventType status) {
        return EventDTO.builder()
                .id(UUID.randomUUID().toString())
                .code(status)
                .whenProcessed(LocalDateTime.now())
                .isActual(true)
                .build();
    }

    /**
     * Заглушка: здесь DossierDTO будет преобразовываться в entity и сохраняться.
     */
    private void save(DossierDTO dossier) {
    }
}
