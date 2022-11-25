package com.inscription.plateform.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String adresse;
    private String email;
    private String cnie;
    private String numTelephone;
    private Date dateNaissance;
    private boolean actif;
    private String image;
    private String file;
    private Genre genre;
    @OneToOne(mappedBy = "form")
    private User user;

}
