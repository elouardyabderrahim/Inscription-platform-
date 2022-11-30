package com.inscription.plateform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inscription.plateform.service.FileService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="files")

public class FormFile {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;




    public FormFile(String name, String type) {
        this.name = name;
        this.type = type;

    }

   // @ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
   // private Form form;






}
