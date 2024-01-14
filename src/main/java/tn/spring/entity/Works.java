package tn.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.Optional;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Works implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre_heur")
	private int nombreHeure;
	
	@Temporal(TemporalType.DATE)
	private Date dateJour;
	@Column(name="description")
	private String description;
	@JsonIgnore
	@ManyToOne
	private Project project;
	@JsonIgnore
	@ManyToOne
	private User user;
	

}
