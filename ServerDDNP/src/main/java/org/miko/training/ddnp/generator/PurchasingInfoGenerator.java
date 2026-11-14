package org.miko.training.ddnp.generator;

import org.miko.training.ddnp.model.dto.EventDTO;
import org.miko.training.ddnp.model.dto.PurchasingInfoDTO;
import org.miko.training.ddnp.model.enumeration.EventType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class PurchasingInfoGenerator {

    public PurchasingInfoDTO generate(List<EventDTO> eventHistoryFull) {
        EventDTO purchased = findEvent(eventHistoryFull, EventType.VEHICLE_PURCHASED);

        LocalDate purchaseDate = purchased.getWhenProcessed().toLocalDate();

        BigDecimal purchasePrice = Randoms.randomAmount(500_000, 1_500_000);
        BigDecimal deviation = Randoms.randomAmount(0, 200_000);
        BigDecimal estimatedMarketValue = Randoms.randomBoolean()
                ? purchasePrice.add(deviation)
                : purchasePrice.subtract(deviation);

        return PurchasingInfoDTO.builder()
                .decisionDate(purchaseDate.minusDays(1))
                .purchaseDate(purchaseDate)
                .purchaseContractNumber(Randoms.randomAlphanumeric(10))
                .purchasePrice(purchasePrice)
                .estimatedMarketValue(estimatedMarketValue)
                .purchaseDecisionComment(null)
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
