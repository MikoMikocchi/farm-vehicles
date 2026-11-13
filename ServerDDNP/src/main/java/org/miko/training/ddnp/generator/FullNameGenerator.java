package org.miko.training.ddnp.generator;

import org.miko.training.ddnp.model.dto.FullNameDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FullNameGenerator {

    private static final List<String> LAST_NAMES = List.of(
            "Иванов",
            "Смирнов",
            "Кузнецов",
            "Попов",
            "Васильев",
            "Петров",
            "Соколов",
            "Михайлов",
            "Новиков",
            "Фёдоров",
            "Морозов",
            "Волков",
            "Алексеев",
            "Лебедев",
            "Семёнов");

    private static final List<String> FIRST_NAMES = List.of(
            "Александр",
            "Дмитрий",
            "Максим",
            "Сергей",
            "Андрей",
            "Алексей",
            "Артём",
            "Илья",
            "Иван",
            "Михаил",
            "Никита",
            "Даниил",
            "Егор",
            "Павел",
            "Роман");

    private static final List<String> MIDDLE_NAMES = List.of(
            "Александрович",
            "Дмитриевич",
            "Сергеевич",
            "Андреевич",
            "Алексеевич",
            "Артёмович",
            "Ильич",
            "Иванович",
            "Михайлович",
            "Никитич",
            "Данилович",
            "Егорович",
            "Павлович",
            "Романович",
            "Петрович");

    public FullNameDTO randomFullName() {
        return FullNameDTO.builder()
                .lastName(Randoms.randomElement(LAST_NAMES))
                .firstName(Randoms.randomElement(FIRST_NAMES))
                .middleName(Randoms.randomElement(MIDDLE_NAMES))
                .build();
    }
}
