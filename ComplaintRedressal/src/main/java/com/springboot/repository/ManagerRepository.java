package com.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.model.Managers;

public interface ManagerRepository extends CrudRepository<Managers, String> {

}
