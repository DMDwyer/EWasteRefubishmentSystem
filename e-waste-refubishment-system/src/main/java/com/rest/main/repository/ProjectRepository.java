package com.rest.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.main.model.Project;


public interface ProjectRepository extends JpaRepository<Project, Long>{

}
