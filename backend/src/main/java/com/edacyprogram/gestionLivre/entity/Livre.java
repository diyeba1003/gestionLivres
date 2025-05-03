package com.edacyprogram.gestionLivre.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur;
    private String description;
    private String genre;
    private String isbn;
    private String urlImage;

    @Temporal(TemporalType.DATE)
    private Date datePublication;

}
