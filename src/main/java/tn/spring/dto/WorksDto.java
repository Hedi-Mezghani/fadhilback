package tn.spring.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.spring.entity.Project;
import tn.spring.entity.User;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class WorksDto {
	@Include
	private Long id;
	
	private Date dateJour;
	private int nombreHeure;
	private String description;
	
	private User user;
	private Project project;


}
