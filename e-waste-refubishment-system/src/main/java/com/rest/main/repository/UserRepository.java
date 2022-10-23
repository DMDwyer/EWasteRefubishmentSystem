package com.rest.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.main.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
