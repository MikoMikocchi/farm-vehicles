package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ContactInfoBIO {

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "additional_phone_number")
    private String additionalPhoneNumber;
}
