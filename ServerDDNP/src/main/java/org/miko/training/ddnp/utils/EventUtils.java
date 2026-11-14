package org.miko.training.ddnp.utils;

import org.miko.training.ddnp.model.dto.EventDTO;
import org.miko.training.ddnp.model.enumeration.EventType;

import java.util.List;

public final class EventUtils {

    private EventUtils() {
    }

    public static boolean isActualEventPresent(List<EventDTO> eventHistoryFull, EventType eventType) {
        for (EventDTO event : eventHistoryFull) {
            if (event.getCode() == eventType) {
                return true;
            }
        }

        return false;
    }
}
