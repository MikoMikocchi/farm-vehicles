package org.miko.training.ddnp.generator;

import org.miko.training.ddnp.model.dto.AddressDTO;
import org.miko.training.ddnp.model.dto.ContactInfoDTO;
import org.miko.training.ddnp.model.dto.FullNameDTO;
import org.miko.training.ddnp.model.dto.OrganizationIdentityDTO;
import org.miko.training.ddnp.model.dto.PersonIdentityDTO;
import org.miko.training.ddnp.model.dto.SellerDTO;
import org.miko.training.ddnp.model.dto.SellerDocumentDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class SellerGenerator {

    private static final List<String> REGIONS = List.of(
            "Московская область",
            "Ленинградская область",
            "Краснодарский край",
            "Ростовская область",
            "Воронежская область",
            "Свердловская область",
            "Новосибирская область",
            "Республика Татарстан",
            "Саратовская область",
            "Ставропольский край",
            "Омская область",
            "Курская область");

    private static final List<String> DISTRICTS = List.of(
            "Серпуховский район",
            "Гатчинский район",
            "Динской район",
            "Азовский район",
            "Семилукский район",
            "Сысертский район",
            "Мошковский район",
            "Лаишевский район",
            "Энгельсский район",
            "Шпаковский район",
            "Омский район",
            "Курский район");

    private static final List<String> STREETS = List.of(
            "Ленина",
            "Советская",
            "Мира",
            "Центральная",
            "Молодёжная",
            "Садовая",
            "Школьная",
            "Заводская",
            "Полевая",
            "Луговая");

    private static final List<String> EMAILS = List.of(
            "info@agro-seller.ru",
            "seller2020@mail.ru",
            "petrov.a@yandex.ru",
            "farm-tech@mail.ru",
            "tractor.parts@yandex.ru",
            "mikhailov@inbox.ru",
            "agro.holding@mail.ru",
            "sidorov.ivan@gmail.com",
            "tehnika.prodazha@mail.ru",
            "kuznetsov.agro@yandex.ru");

    private static final List<String> DOCUMENT_TYPES = List.of(
            "российский паспорт",
            "российский заграничный паспорт",
            "водительское удостоверение");

    private static final List<String> ISSUING_AUTHORITIES = List.of(
            "ФМС",
            "МИД",
            "МВД",
            "ГИБДД");

    private final FullNameGenerator fullNameGenerator;

    public SellerGenerator(FullNameGenerator fullNameGenerator) {
        this.fullNameGenerator = fullNameGenerator;
    }

    public SellerDTO generate() {
        boolean isOrganization = Randoms.randomBoolean();

        return SellerDTO.builder()
                .person(isOrganization ? null : person())
                .organization(isOrganization ? organization() : null)
                .documents(documents())
                .contacts(contacts())
                .address(address())
                .build();
    }

    private PersonIdentityDTO person() {
        return PersonIdentityDTO.builder()
                .fullName(fullNameGenerator.randomFullName())
                .birthDate(Randoms.randomDate(LocalDate.of(1970, 1, 1), LocalDate.of(2003, 12, 31)))
                .taxIdentificationNumber(Randoms.randomDigitSequence(20))
                .build();
    }

    private OrganizationIdentityDTO organization() {
        FullNameDTO name = fullNameGenerator.randomFullName();

        return OrganizationIdentityDTO.builder()
                .fullName(name.getLastName() + " " + name.getFirstName() + " " + name.getMiddleName())
                .shortName(null)
                .inn(Randoms.randomDigitSequence(20))
                .ogrn(Randoms.randomDigitSequence(18))
                .kpp(Randoms.randomDigitSequence(24))
                .registrationDate(Randoms.randomDate(LocalDate.of(1993, 1, 1), LocalDate.of(2026, 12, 31)))
                .build();
    }

    private SellerDocumentDTO documents() {
        return SellerDocumentDTO.builder()
                .documentType(Randoms.randomElement(DOCUMENT_TYPES))
                .documentNumber(Randoms.randomDigitSequence(10))
                .issueDate(Randoms.randomDate(LocalDate.of(2018, 1, 1), LocalDate.of(2026, 12, 31)))
                .issuingAuthority(Randoms.randomElement(ISSUING_AUTHORITIES))
                .build();
    }

    private ContactInfoDTO contacts() {
        return ContactInfoDTO.builder()
                .phoneNumber(Randoms.randomPhoneNumber())
                .email(Randoms.randomElement(EMAILS))
                .additionalPhoneNumber(Randoms.randomBoolean() ? Randoms.randomPhoneNumber() : null)
                .build();
    }

    private AddressDTO address() {
        int location = Randoms.randomInt(0, REGIONS.size() - 1);

        return AddressDTO.builder()
                .postalCode(Randoms.randomDigits(6))
                .region(REGIONS.get(location))
                .district(DISTRICTS.get(location))
                .city(Cities.CITIES.get(location))
                .street(Randoms.randomElement(STREETS))
                .house(String.valueOf(Randoms.randomInt(1, 140)))
                .building(String.valueOf(Randoms.randomInt(0, 3)))
                .apartment(String.valueOf(Randoms.randomInt(1, 600)))
                .build();
    }
}
