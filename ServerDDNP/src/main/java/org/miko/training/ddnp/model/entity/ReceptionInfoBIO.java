package org.miko.training.ddnp.model.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class ReceptionInfoBIO {

    @Column(name = "reception_date")
    private LocalDate receptionDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lastName", column = @Column(name = "responsible_employee_last_name")),
            @AttributeOverride(name = "firstName", column = @Column(name = "responsible_employee_first_name")),
            @AttributeOverride(name = "middleName", column = @Column(name = "responsible_employee_middle_name"))
    })
    private FullNameBIO responsibleEmployeeName;

    @Column(name = "responsible_employee_post")
    private String responsibleEmployeePost;

    @Column(name = "responsible_employee_id")
    private String responsibleEmployeeId;
}
