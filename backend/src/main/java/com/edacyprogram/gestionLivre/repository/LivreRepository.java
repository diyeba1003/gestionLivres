package com.edacyprogram.gestionLivre.repository;

import com.edacyprogram.gestionLivre.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}
