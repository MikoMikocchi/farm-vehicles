package org.miko.training.farmvehichlesmodel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String postalCode;
    private String region;
    private String district;
    private String city;
    private String street;
    private String house;
    private String building;
    private String apartment;
}
