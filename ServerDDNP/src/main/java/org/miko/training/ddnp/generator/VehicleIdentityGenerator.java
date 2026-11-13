package org.miko.training.ddnp.generator;

import org.miko.training.ddnp.model.dto.VehicleIdentityDTO;
import org.miko.training.ddnp.model.dto.VehiclePassportDTO;
import org.miko.training.ddnp.model.enumeration.VehicleCategory;
import org.miko.training.ddnp.model.enumeration.VehicleType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class VehicleIdentityGenerator {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final LocalDate MIN_MANUFACTURE_DATE = LocalDate.of(1980, 1, 1);
    private static final LocalDate MAX_MANUFACTURE_DATE = LocalDate.of(2020, 12, 31);

    private static final List<String> BRANDS = List.of(
            "John Deere",
            "Claas",
            "New Holland",
            "Case IH",
            "Fendt",
            "Massey Ferguson",
            "Rostselmash",
            "Kirovets",
            "Valtra",
            "Deutz-Fahr");

    private static final List<String> MODELS = List.of(
            "Агро-100",
            "Нива",
            "Полесье",
            "Вектор",
            "Дон-1500",
            "Енисей-1200",
            "X9",
            "S7",
            "RX 9000",
            "Tucano 560",
            "Magnum 340",
            "Puma 210",
            "Axion 850",
            "Fendt 936",
            "Кировец К-744",
            "Acros 590",
            "Torum 740",
            "T-150",
            "T-250",
            "AgroMax 200");

    private static final List<String> MODIFICATIONS = List.of(
            "4x4",
            "4x2",
            "Turbo",
            "Turbo Plus",
            "Comfort",
            "Premium",
            "Classic",
            "Standard",
            "HiLine",
            "Eco",
            "Power",
            "Max",
            "Pro",
            "Lux",
            "Base",
            "HD",
            "XL",
            "SE",
            "Agro",
            "Top");

    private static final List<String> ISSUERS = List.of(
            "Инспекция Гостехнадзора Московской области",
            "Управление Гостехнадзора Краснодарского края",
            "Отдел Гостехнадзора Ростовской области",
            "Главное управление Гостехнадзора Республики Татарстан");

    public VehicleIdentityDTO generate() {
        LocalDate manufactureDate = Randoms.randomDate(MIN_MANUFACTURE_DATE, MAX_MANUFACTURE_DATE);

        return VehicleIdentityDTO.builder()
                .vehicleType(Randoms.randomEnum(VehicleType.class))
                .vehicleCategory(Randoms.randomEnum(VehicleCategory.class))
                .brand(Randoms.randomElement(BRANDS))
                .model(Randoms.randomElement(MODELS))
                .modification(Randoms.randomElement(MODIFICATIONS))
                .manufactureDate(manufactureDate)
                .printableManufactureDate(manufactureDate.format(DATE_FORMAT))
                .manufactureDate_ISO(legacyDate(manufactureDate))
                .manufactureDate_ISO_get(legacyDate(manufactureDate))
                .factoryNumber(Randoms.randomDigits(10))
                .identificationNumber(Randoms.randomDigits(12))
                .engineNumber(Randoms.randomAlphanumeric(12))
                .registrationNumber(Randoms.randomDigits(12))
                .passport(passport(manufactureDate))
                .build();
    }

    private VehiclePassportDTO passport(LocalDate manufactureDate) {
        LocalDate issueDate = manufactureDate.plusYears(Randoms.randomInt(0, 8));

        if (issueDate.isAfter(LocalDate.now())) {
            issueDate = LocalDate.now();
        }

        return VehiclePassportDTO.builder()
                .series(Randoms.randomDigits(2))
                .number(Randoms.randomDigits(6))
                .issueDate(issueDate)
                .issueDate_ISO(legacyDate(issueDate))
                .issueDate_ISO_get(legacyDate(issueDate))
                .expireDate(null)
                .expireDate_ISO(null)
                .expireDate_ISO_get(null)
                .issuer(Randoms.randomElement(ISSUERS))
                .build();
    }

    /**
     * Имитация легаси: поле то заполнено датой, то пустое.
     */
    private String legacyDate(LocalDate date) {
        return Randoms.randomBoolean() ? date.format(DATE_FORMAT) : null;
    }
}
