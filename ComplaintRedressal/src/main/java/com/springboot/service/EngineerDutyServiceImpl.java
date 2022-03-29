package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.EngineerDuty;
import com.springboot.repository.EngineerDutyRepository;

@Service
public class EngineerDutyServiceImpl implements EngineerDutyService {

	@Autowired
	EngineerDutyRepository engineerDutyRepository;

	@Override
	public void saveEngineerDuty(EngineerDuty engineerDuty) {
		engineerDutyRepository.save(engineerDuty);
	}

	public List<EngineerDuty> findEngineerDutyByEmail(String engineerEmail) {

		return engineerDutyRepository.findEngineerDutyByEngineerEmail(engineerEmail);
	}

	public EngineerDuty getEngineerAssignedByTicketId(int ticketId) {

		return engineerDutyRepository.findEngineerDutyByTicketId(ticketId);
	}

}
