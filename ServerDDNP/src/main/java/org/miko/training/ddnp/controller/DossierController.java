package org.miko.training.ddnp.controller;

import lombok.RequiredArgsConstructor;
import org.miko.training.ddnp.model.dto.DossierDTO;
import org.miko.training.ddnp.service.DossierService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DossierController {

    private final DossierService dossierService;

    @GetMapping("/getDossier/{uid}")
    public DossierDTO getDossier(@PathVariable String uid) {
        return dossierService.getDossier(uid);
    }
}
