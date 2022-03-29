package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Engineers;

public interface EngineerRepository extends JpaRepository<Engineers, String> {

}
