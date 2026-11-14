package org.miko.training.ddnp.service;

import lombok.RequiredArgsConstructor;
import org.miko.training.ddnp.mapper.DossierDTOMapped;
import org.miko.training.ddnp.model.dto.DossierDTO;
import org.miko.training.ddnp.model.entity.DossierEntity;
import org.miko.training.ddnp.repository.DossierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DossierService {

    private final DossierRepository dossierRepository;

    @Transactional(readOnly = true)
    public DossierDTO getDossier(String uid) {
        DossierEntity entity = dossierRepository.findByUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("Object reference not set to an instance of an object"));

        return new DossierDTOMapped(entity).getDossier();
    }
}
