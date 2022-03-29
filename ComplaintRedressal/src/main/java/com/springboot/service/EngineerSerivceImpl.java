package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.model.Engineers;
import com.springboot.repository.EngineerRepository;

@Service
public class EngineerSerivceImpl implements EngineerService {

	@Autowired
	EngineerRepository engineerRepository;

	@Override
	public List<Engineers> fetchAllEngineers() {
		return engineerRepository.findAll();
	}

	@Override
	public void saveEngineer(Engineers engineer) {
		engineerRepository.save(engineer);

	}

	@Override
	public Engineers findEngineerById(String email) {
		Engineers engineer = engineerRepository.findById(email).orElse(null);
		return engineer;
	}

	@Override
	public void deleteEngineer(Engineers engineer) {
		engineerRepository.delete(engineer);

	}

	@Override
	public Boolean validateEngineer(String engineerEmail, String engineerPassword) {
		System.out.println(engineerEmail + " --- " + engineerPassword);
		if (engineerRepository.findById(engineerEmail).isPresent()) {
			Engineers engineer = engineerRepository.findById(engineerEmail).get();
			String dbPassword = engineer.getEngineerPassword().toString();
			if (dbPassword.equals(engineerPassword)) {
				return true;
			}
		}
		return false;
	}

}
