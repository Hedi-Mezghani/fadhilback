package tn.spring.service;

import java.util.List;

import tn.spring.dto.ProjectDto;
import tn.spring.exceptions.ProjectNotFoundExeption;

public interface ProjectService {
	
	ProjectDto saveProject(ProjectDto ProjectDto);
	
	List<ProjectDto>ListProjects();
	
	ProjectDto getProject(Long ProjectId ) throws ProjectNotFoundExeption;
	
	ProjectDto updateProject(ProjectDto ProjectDto);
	
	void deleteProject(Long ProjectId);
	

	
	
	

}
