package org.miko.training.ddnp.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class FullNameBIO {

    private String lastName;

    private String firstName;

    private String middleName;
}
