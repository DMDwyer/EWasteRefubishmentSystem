package com.rest.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.main.model.Project;
import com.rest.main.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	
	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}
	
	//build create PC REST API
	@PostMapping("/add")
	public ResponseEntity<Project> saveProject(@RequestBody Project project){
		return new ResponseEntity<Project>(projectService.saveProject(project), HttpStatus.CREATED);
	}
	
	// build get all Project's REST API
	@GetMapping("/all")
	public List<Project> getAllProjects(){
		return projectService.getAllProjects();
	}
	
	// build get Project by id REST API
	// http://localhost:8080/api/projects/1
	@GetMapping("{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable("id") long Projectid){
		return new ResponseEntity<Project>(projectService.getProjectById(Projectid), HttpStatus.OK);
	}
	
	// build delete PC REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProject(@PathVariable("id") long id){
		
		// delete PC from DB
		projectService.deleteProject(id);
		
		return new ResponseEntity<String>("Project deleted successfully!", HttpStatus.OK);
	}

}
