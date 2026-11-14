package org.miko.training.ddnp.generator;

import org.miko.training.ddnp.model.dto.EventDTO;
import org.miko.training.ddnp.model.dto.ReceivingInfoDTO;
import org.miko.training.ddnp.model.enumeration.EventType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReceivingInfoGenerator {

    private static final List<String> RECEIVING_CONDITIONS = List.of(
            "удовлетворительно",
            "нужен ремонт",
            "на персонализацию",
            "готово к продаже с ограничениями");

    private static final List<String> COMPLETENESS_DESCRIPTIONS = List.of(
            "нарушено",
            "взято с изъяном",
            "под ремонт");

    public ReceivingInfoDTO generate(List<EventDTO> eventHistoryFull) {
        EventDTO received = findEvent(eventHistoryFull, EventType.VEHICLE_RECEIVED);

        return ReceivingInfoDTO.builder()
                .receivingDate(received.getWhenProcessed().toLocalDate())
                .warehouseName(null)
                .warehouseAddress(Randoms.randomElement(Cities.CITIES))
                .receivingActNumber(Randoms.randomAlphanumeric(12))
                .receivingCondition(Randoms.randomElement(RECEIVING_CONDITIONS))
                .completenessDescription(Randoms.randomElement(COMPLETENESS_DESCRIPTIONS))
                .receivingComment(null)
                .build();
    }

    private EventDTO findEvent(List<EventDTO> eventHistoryFull, EventType eventType) {
        for (EventDTO event : eventHistoryFull) {
            if (event.getCode() == eventType) {
                return event;
            }
        }

        throw new IllegalArgumentException("В истории нет события " + eventType);
    }
}
