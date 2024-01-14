package tn.spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.spring.dto.ProjectDto;
import tn.spring.exceptions.ProjectNotFoundExeption;
import tn.spring.service.PojectServiceImp;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor

public class ProjectController {
	private PojectServiceImp projectservice;
	
	@GetMapping("/")
	public List<ProjectDto> listProject(){
		return projectservice.ListProjects();
	}
	
	@GetMapping("/{id}")
	public ProjectDto getProject(@PathVariable(name="id")Long id) throws ProjectNotFoundExeption{
		return projectservice.getProject(id);
	}
	@PostMapping("/")
	public ProjectDto saveProject(@RequestBody ProjectDto projectDto) {
		return projectservice.saveProject(projectDto);
	}
	@PutMapping("/{projectid}")
	public ProjectDto updateProject(@PathVariable Long projectid , @RequestBody ProjectDto projectDto ) {
		projectDto.setId(projectid);
		return projectservice.updateProject(projectDto);
	}
	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable Long id) {
		projectservice.deleteProject(id);
	}
	

}
