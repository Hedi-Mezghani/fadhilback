package tn.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.spring.dao.ProjectDao;
import tn.spring.dto.ProjectDto;
import tn.spring.entity.Project;
import tn.spring.exceptions.ProjectNotFoundExeption;
import tn.spring.mapper.ProjectMapper;
@AllArgsConstructor
@Transactional
@Service
@Slf4j
public class PojectServiceImp implements ProjectService {
	
	private ProjectDao ProjectDao;
	
	private ProjectMapper dtoMapper;
	
	

	@Override
	public ProjectDto saveProject(ProjectDto ProjectDto) {
		
		log.info("saving new Project");
		Project Project = dtoMapper.fromProjectDto(ProjectDto);
		Project saveProject = ProjectDao.save(Project);
		return dtoMapper.fromProject(saveProject);
	}

	@Override
	public List<ProjectDto> ListProjects() {
		List<Project> Project= ProjectDao.findAll();
		List<ProjectDto>ProjectDtos = Project.stream().map(cat -> dtoMapper.fromProject(cat)).collect(Collectors.toList());
		return ProjectDtos;
	}

	@Override
	public ProjectDto getProject(Long ProjectId) throws ProjectNotFoundExeption {
		Project project=ProjectDao.findById(ProjectId).orElseThrow(() -> new ProjectNotFoundExeption("project not found"));
		return dtoMapper.fromProject(project);

	}

	@Override
	public ProjectDto updateProject(ProjectDto ProjectDto) {
		
		log.info("Update Project");
		Project Project = dtoMapper.fromProjectDto(ProjectDto);
		Project saveProject = ProjectDao.save(Project);
		return dtoMapper.fromProject(saveProject);
	}

	@Override
	public void deleteProject(Long ProjectId) {
		ProjectDao.deleteById(ProjectId);
		
	}

}
