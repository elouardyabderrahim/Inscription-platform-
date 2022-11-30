package com.inscription.plateform.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forms")

@JsonIdentityInfo(scope = Form.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private String cnie;
    private String numTelephone;
    private Date dateNaissance;
    private boolean actif;
    private String image;
    private String file;

    private Genre genre;

    @OneToOne(mappedBy = "form")
    private User user;



   /* @OneToMany(
            mappedBy = "form",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FormFile> files = new ArrayList<>();

*/





}
