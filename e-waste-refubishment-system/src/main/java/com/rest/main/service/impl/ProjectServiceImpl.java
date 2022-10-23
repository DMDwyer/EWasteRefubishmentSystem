package com.rest.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rest.main.exception.ResourceNotFoundException;
import com.rest.main.model.Project;
import com.rest.main.repository.ProjectRepository;
import com.rest.main.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	private ProjectRepository projectRepository;
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}
	
	@Override
	public Project saveProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	@Override
	public Project getProjectById(long projectId) {
		
		Optional<Project> project = projectRepository.findById(projectId);
		
		if(project.isPresent()) {
			return project.get();
		}else {
			throw new ResourceNotFoundException("Device", "id", projectId);
		}
	}

	@Override
	public void deleteProject(long projectId) {
		projectRepository.findById(projectId).orElseThrow(() ->
				new ResourceNotFoundException("Project", "Id", projectId));
	}

}
