package tn.spring.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import tn.spring.dto.ProjectDto;
import tn.spring.entity.Project;
@Service
public class ProjectMapper {
	
	public ProjectDto fromProject(Project Project) {
		ProjectDto ProjectDto = new ProjectDto();
		BeanUtils.copyProperties(Project, ProjectDto);
		return ProjectDto;
	}
	public Project fromProjectDto(ProjectDto ProjectDto) {
		Project Project = new Project();
		BeanUtils.copyProperties(ProjectDto,Project );
		return Project;
	}
	

}
