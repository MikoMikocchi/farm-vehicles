package org.miko.training.ddnp.repository;

import org.miko.training.ddnp.model.entity.DossierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<DossierEntity, String> {

    Optional<DossierEntity> findByUid(String uid);
}
