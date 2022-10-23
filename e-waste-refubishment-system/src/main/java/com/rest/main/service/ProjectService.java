package com.rest.main.service;

import java.util.List;

import com.rest.main.model.Project;

public interface ProjectService {
	
	Project saveProject(Project project);
	
	List<Project> getAllProjects();
	
	Project getProjectById(long projectId);
	
	void deleteProject(long projectId);

}
