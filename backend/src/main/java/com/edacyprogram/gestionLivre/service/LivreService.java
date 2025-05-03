package com.edacyprogram.gestionLivre.service;

import com.edacyprogram.gestionLivre.entity.Livre;
import com.edacyprogram.gestionLivre.repository.LivreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {
    private final LivreRepository livreRepository;
    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();

    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Livre non trouvé"));

    }
    public Livre addLivre(Livre livre) {
        return livreRepository.save(livre);
    }



    public Livre updateLivre(Long id,Livre livre) {
        Livre existLivre = getLivreById(id);
        existLivre.setTitre(livre.getTitre());
        existLivre.setAuteur(livre.getAuteur());
        existLivre.setDescription(livre.getDescription());
        existLivre.setIsbn(livre.getIsbn());
        existLivre.setDatePublication(livre.getDatePublication());
        existLivre.setUrlImage(livre.getUrlImage());
        existLivre.setGenre(livre.getGenre());
        return livreRepository.save(existLivre);

    }

    public void deleteLivre(Long id) {
        Livre existLivre = getLivreById(id);
        livreRepository.delete(existLivre);

    }
}
