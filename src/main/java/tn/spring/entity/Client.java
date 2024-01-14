package tn.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "name_client")
    String nom;
    @Column(name = "code_client")
    String code_client;
    String address;
    @Column(name = "code_postal")
    Integer code_postal;
    String pays;
    String department;
    String email;
    String web;
    Integer telephone;
}
