package org.miko.training.ddnp.generator;

import org.miko.training.ddnp.model.dto.ReceptionInfoDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class ReceptionInfoGenerator {

    private static final List<String> POSTS = List.of(
            "Менеджер по закупкам",
            "Специалист по приёмке техники",
            "Логист",
            "Инженер-механик",
            "Руководитель отдела закупок",
            "Товаровед");

    private final FullNameGenerator fullNameGenerator;

    public ReceptionInfoGenerator(FullNameGenerator fullNameGenerator) {
        this.fullNameGenerator = fullNameGenerator;
    }

    public ReceptionInfoDTO generate(LocalDate receptionDate) {
        return ReceptionInfoDTO.builder()
                .receptionDate(receptionDate)
                .responsibleEmployeeName(fullNameGenerator.randomFullName())
                .responsibleEmployeePost(Randoms.randomElement(POSTS))
                .responsibleEmployeeId(UUID.randomUUID().toString())
                .build();
    }
}
