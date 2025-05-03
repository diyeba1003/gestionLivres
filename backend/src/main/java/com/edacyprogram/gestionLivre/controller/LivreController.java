package com.edacyprogram.gestionLivre.controller;

import com.edacyprogram.gestionLivre.entity.Livre;
import com.edacyprogram.gestionLivre.service.LivreService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livre")
public class LivreController {
    private final LivreService livreService;
    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @PostMapping("/addLivre")

    public ResponseEntity<?> addLivre(@RequestBody Livre livre) {
        Livre createdLivre = livreService.addLivre(livre);
        return ResponseEntity.ok(createdLivre);

    }

@GetMapping("/listeLivre")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Livre>> getAllLivre() {
        List<Livre> livres = livreService.getAllLivres();
        return new ResponseEntity<>(livres, HttpStatus.OK);

}
@GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable("id") Long id) {
        try{

        Livre existLivre = livreService.getLivreById(id);
        return new ResponseEntity<>(existLivre, HttpStatus.OK);
}
   catch(EntityNotFoundException e) {
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
    }

@PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable("id") Long id, @RequestBody Livre livre) {
        Livre updatedLivre = livreService.updateLivre(id, livre);
        try{
            return new ResponseEntity<>(updatedLivre, HttpStatus.OK);
        }
        catch(EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}

@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLivre(@PathVariable("id") Long id) {
        try {
            livreService.deleteLivre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


}
}
