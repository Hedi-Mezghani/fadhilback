package tn.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@Table(name="files")
public class File implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ajoutez les champs de votre formulaire ici
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String anciennete;
    private String mail;
    private String telephone;
    private String rib;

    // Relation OneToOne avec l'entité User
    @OneToOne(mappedBy = "file", cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

}
