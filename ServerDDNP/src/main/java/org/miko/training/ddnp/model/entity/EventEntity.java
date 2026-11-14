package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.miko.training.ddnp.model.enumeration.EventType;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class EventEntity {

    @Column(name = "event_id")
    private String id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "code")
    private EventType code;

    @Column(name = "when_processed")
    private LocalDateTime whenProcessed;

    @Column(name = "is_actual")
    private boolean isActual;

    @Column(name = "is_state")
    private boolean isState;

    @Column(name = "comment")
    private String comment;

    @Column(name = "correlation_id")
    private String correlationId;
}
