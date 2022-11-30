package com.inscription.plateform.dto;

import com.inscription.plateform.entity.FormFile;
import com.inscription.plateform.entity.Genre;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
public class FormeDto {

    private Long id;
    private String adresse;
    private String cnie;
    private String numTelephone;
    private Date dateNaissance;
    private boolean actif;
    private String image;
    private String file;
    private Genre genre;



}
