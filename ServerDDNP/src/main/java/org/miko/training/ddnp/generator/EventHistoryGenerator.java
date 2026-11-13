package org.miko.training.ddnp.generator;

import org.miko.training.ddnp.model.dto.EventDTO;
import org.miko.training.ddnp.model.enumeration.EventType;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class EventHistoryGenerator {

    /**
     * Статусы досье по порядку. Индекс в списке (+1) — "уровень" статуса.
     */
    private static final List<EventType> STATUS_LEVELS = List.of(
            EventType.DOSSIER_CREATED,
            EventType.VEHICLE_REGISTERED,
            EventType.VEHICLE_DATA_VERIFICATION_COMPLETED,
            EventType.VEHICLE_VALUATION_COMPLETED,
            EventType.PURCHASE_DECISION_MADE,
            EventType.VEHICLE_PURCHASED,
            EventType.VEHICLE_LISTED_FOR_SALE,
            EventType.VEHICLE_SOLD);

    /**
     * Фиксированный шаг для каждого вида события: сколько времени проходит от этого события до следующего.
     * Таймауты проверок в историю не попадают, поэтому шагов для них нет.
     */
    private final Map<EventType, Duration> steps = steps();

    /**
     * Строит историю событий досье до указанного статуса включительно.
     * Последнее событие в списке — это событие статуса: его же нужно записывать и в currentState.
     *
     * @param status     статус досье (последнее событие истории)
     * @param statusTime время, когда наступил статус
     * @return события в хронологическом порядке, последнее из них — событие статуса
     */
    public List<EventDTO> generateHistory(EventType status, LocalDateTime statusTime) {
        int level = levelOf(status);

        List<EventDTO> events = new ArrayList<>();
        LocalDateTime time = statusTime;

        time = createUpToDossierCreated(events, time);
        if (level >= 2) {
            time = createUpToVehicleRegistered(events, time);
        }
        if (level >= 3) {
            time = createUpToVerificationCompleted(events, time);
        }
        if (level >= 4) {
            time = createUpToValuationCompleted(events, time);
        }
        if (level >= 5) {
            time = createUpToPurchaseDecisionMade(events, time);
        }
        if (level >= 6) {
            time = createUpToVehiclePurchased(events, time);
        }
        if (level >= 7) {
            time = createUpToListedForSale(events, time);
        }
        if (level >= 8) {
            time = createUpToSold(events, time);
        }

        /*
         * События построены "вперёд" от времени статуса, то есть все они оказались в будущем.
         * Сдвигаем весь список в прошлое: последнее событие в списке — это событие статуса,
         * оно должно попасть ровно на заданное время статуса.
         */
        shiftToStatusTime(events, statusTime);

        return events;
    }

    /**
     * Строит "короткую" историю событий: каждое событие полной истории случайно оставляется или выбрасывается.
     * Событие статуса (последнее) не выбрасывается никогда.
     */
    public List<EventDTO> generateEventHistory(List<EventDTO> eventHistoryFull) {
        List<EventDTO> eventHistory = new ArrayList<>();
        int lastIndex = eventHistoryFull.size() - 1;

        for (int i = 0; i <= lastIndex; i++) {
            EventDTO event = eventHistoryFull.get(i);

            if (i == lastIndex || Randoms.randomBoolean()) {
                eventHistory.add(event);
            }
        }

        return eventHistory;
    }

    // DOSSIER_CREATED
    private LocalDateTime createUpToDossierCreated(List<EventDTO> events, LocalDateTime time) {
        time = createEvent(events, EventType.DOSSIER_CREATED, time);
        return time;
    }

    // DOSSIER_CREATED -> VEHICLE_REGISTERED
    private LocalDateTime createUpToVehicleRegistered(List<EventDTO> events, LocalDateTime time) {
        time = createEvent(events, EventType.DOSSIER_DATA_UPDATED, time);
        time = createEvent(events, EventType.SELLER_DATA_RECEIVED, time);
        time = createEvent(events, EventType.VEHICLE_DATA_RECEIVED, time);
        time = createEvent(events, EventType.VEHICLE_DOCUMENTS_RECEIVED, time);
        time = createEvent(events, EventType.VEHICLE_REGISTERED, time);
        return time;
    }

    // VEHICLE_REGISTERED -> VEHICLE_DATA_VERIFICATION_COMPLETED
    private LocalDateTime createUpToVerificationCompleted(List<EventDTO> events, LocalDateTime time) {
        time = createEvent(events, EventType.SELLER_CHECK_STARTED, time);
        time = createEvent(events, EventType.SELLER_CHECK_COMPLETED, time);
        time = createEvent(events, EventType.ENCUMBRANCE_CHECK_STARTED, time);
        time = createEvent(events, EventType.ENCUMBRANCE_CHECK_COMPLETED, time);
        time = createEvent(events, EventType.VEHICLE_REGISTRY_CHECK_STARTED, time);
        time = createEvent(events, EventType.VEHICLE_REGISTRY_CHECK_COMPLETED, time);
        time = createEvent(events, EventType.VEHICLE_DATA_VERIFICATION_STARTED, time);
        time = createEvent(events, EventType.VEHICLE_DATA_VERIFICATION_COMPLETED, time);
        return time;
    }

    // VEHICLE_DATA_VERIFICATION_COMPLETED -> VEHICLE_VALUATION_COMPLETED
    private LocalDateTime createUpToValuationCompleted(List<EventDTO> events, LocalDateTime time) {
        time = createEvent(events, EventType.VEHICLE_VALUATION_STARTED, time);
        time = createEvent(events, EventType.VEHICLE_VALUATION_COMPLETED, time);
        return time;
    }

    // VEHICLE_VALUATION_COMPLETED -> PURCHASE_DECISION_MADE
    private LocalDateTime createUpToPurchaseDecisionMade(List<EventDTO> events, LocalDateTime time) {
        time = createEvent(events, EventType.PURCHASE_DECISION_MADE, time);
        return time;
    }

    // PURCHASE_DECISION_MADE -> VEHICLE_PURCHASED
    private LocalDateTime createUpToVehiclePurchased(List<EventDTO> events, LocalDateTime time) {
        time = createEvent(events, EventType.PURCHASE_APPROVED, time);
        time = createEvent(events, EventType.PURCHASE_REJECTED, time);
        time = createEvent(events, EventType.PURCHASE_CONTRACT_SIGNED, time);
        time = createEvent(events, EventType.VEHICLE_PURCHASED, time);
        return time;
    }

    // VEHICLE_PURCHASED -> VEHICLE_LISTED_FOR_SALE
    private LocalDateTime createUpToListedForSale(List<EventDTO> events, LocalDateTime time) {
        time = createEvent(events, EventType.VEHICLE_RECEIVED, time);
        time = createEvent(events, EventType.VEHICLE_DIAGNOSTICS_STARTED, time);
        time = createEvent(events, EventType.VEHICLE_DIAGNOSTICS_COMPLETED, time);
        time = createEvent(events, EventType.VEHICLE_REPAIR_STARTED, time);
        time = createEvent(events, EventType.VEHICLE_REPAIR_COMPLETED, time);
        time = createEvent(events, EventType.VEHICLE_LISTED_FOR_SALE, time);
        return time;
    }

    // VEHICLE_LISTED_FOR_SALE -> VEHICLE_SOLD
    private LocalDateTime createUpToSold(List<EventDTO> events, LocalDateTime time) {
        time = createEvent(events, EventType.VEHICLE_SOLD, time);
        return time;
    }

    /**
     * Создаёт событие на указанном времени и возвращает время следующего события.
     */
    private LocalDateTime createEvent(List<EventDTO> events, EventType type, LocalDateTime time) {
        events.add(event(type, time));
        return time.plus(steps.get(type));
    }

    private EventDTO event(EventType type, LocalDateTime whenProcessed) {
        return EventDTO.builder()
                .id(UUID.randomUUID().toString())
                .code(type)
                .whenProcessed(whenProcessed)
                .build();
    }

    private void shiftToStatusTime(List<EventDTO> events, LocalDateTime statusTime) {
        LocalDateTime lastTime = events.get(events.size() - 1).getWhenProcessed();
        Duration shift = Duration.between(statusTime, lastTime);

        for (EventDTO event : events) {
            event.setWhenProcessed(event.getWhenProcessed().minus(shift));
        }
    }

    private int levelOf(EventType status) {
        int level = STATUS_LEVELS.indexOf(status) + 1;

        if (level == 0) {
            throw new IllegalArgumentException("Событие " + status + " не является статусом досье");
        }

        return level;
    }

    private Map<EventType, Duration> steps() {
        Map<EventType, Duration> steps = new LinkedHashMap<>();
        steps.put(EventType.DOSSIER_CREATED, Duration.ofDays(2));
        steps.put(EventType.DOSSIER_DATA_UPDATED, Duration.ofDays(1));
        steps.put(EventType.SELLER_DATA_RECEIVED, Duration.ofDays(2));
        steps.put(EventType.VEHICLE_DATA_RECEIVED, Duration.ofHours(12));
        steps.put(EventType.VEHICLE_DOCUMENTS_RECEIVED, Duration.ofDays(1));
        steps.put(EventType.VEHICLE_REGISTERED, Duration.ofHours(6));

        steps.put(EventType.SELLER_CHECK_STARTED, Duration.ofHours(2));
        steps.put(EventType.SELLER_CHECK_COMPLETED, Duration.ofDays(1));
        steps.put(EventType.ENCUMBRANCE_CHECK_STARTED, Duration.ofHours(4));
        steps.put(EventType.ENCUMBRANCE_CHECK_COMPLETED, Duration.ofDays(1));
        steps.put(EventType.VEHICLE_REGISTRY_CHECK_STARTED, Duration.ofHours(2));
        steps.put(EventType.VEHICLE_REGISTRY_CHECK_COMPLETED, Duration.ofDays(1));
        steps.put(EventType.VEHICLE_DATA_VERIFICATION_STARTED, Duration.ofHours(4));
        steps.put(EventType.VEHICLE_DATA_VERIFICATION_COMPLETED, Duration.ofDays(1));

        steps.put(EventType.VEHICLE_VALUATION_STARTED, Duration.ofHours(2));
        steps.put(EventType.VEHICLE_VALUATION_COMPLETED, Duration.ofDays(2));

        steps.put(EventType.PURCHASE_DECISION_MADE, Duration.ofDays(1));
        steps.put(EventType.PURCHASE_APPROVED, Duration.ofHours(2));
        steps.put(EventType.PURCHASE_REJECTED, Duration.ofHours(2));
        steps.put(EventType.PURCHASE_CONTRACT_SIGNED, Duration.ofDays(1));
        steps.put(EventType.VEHICLE_PURCHASED, Duration.ofDays(1));

        steps.put(EventType.VEHICLE_RECEIVED, Duration.ofDays(2));
        steps.put(EventType.VEHICLE_DIAGNOSTICS_STARTED, Duration.ofHours(2));
        steps.put(EventType.VEHICLE_DIAGNOSTICS_COMPLETED, Duration.ofDays(2));
        steps.put(EventType.VEHICLE_REPAIR_STARTED, Duration.ofHours(4));
        steps.put(EventType.VEHICLE_REPAIR_COMPLETED, Duration.ofDays(2));

        steps.put(EventType.VEHICLE_LISTED_FOR_SALE, Duration.ofHours(12));
        steps.put(EventType.VEHICLE_SOLD, Duration.ofDays(1));
        return steps;
    }
}
