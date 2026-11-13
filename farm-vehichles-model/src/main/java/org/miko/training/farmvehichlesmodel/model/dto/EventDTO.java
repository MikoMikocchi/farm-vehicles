package org.miko.training.farmvehichlesmodel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.miko.training.farmvehichlesmodel.model.enumeration.EventType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private String id;
    private EventType code;
    private LocalDateTime whenProcessed;
    private boolean isActual;
    private String comment;
    private String correlationId;
}
