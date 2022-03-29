package com.springboot.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Complaints;
import com.springboot.model.EngineerDuty;
import com.springboot.model.Engineers;
import com.springboot.service.EngineerDutyServiceImpl;
import com.springboot.service.EngineerService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/engineers")
public class EngineerController {

	@Autowired
	EngineerService engineerService;

	@Autowired
	EngineerDutyServiceImpl engineerDutyServiceImpl;

	@GetMapping("/getAllEngineers")
	public List<Engineers> getAllEngineers() {
		List<Engineers> engineers = (List<Engineers>) engineerService.fetchAllEngineers();
		return engineers;
	}

	@GetMapping("/getAllEngineerMails")
	public List<String> getAllEngineerMails() {
		List<Engineers> engineers = (List<Engineers>) engineerService.fetchAllEngineers();
		List<String> engineerMails = new ArrayList<String>();
		for (int i = 0; i < engineers.size(); i++) {
			engineerMails.add(engineers.get(i).getEngineerEmail());
		}
		return engineerMails;
	}

	@PostMapping("/login")
	public List<Integer> validateEngineer(@RequestBody Object loginDetails) throws NoSuchFieldException {

		String engineerEmail = (String) ((LinkedHashMap) loginDetails).get("engineerEmail");
		String engineerPassword = (String) ((LinkedHashMap) loginDetails).get("engineerPassword");
		
		List<Integer> ticketIds = new ArrayList<Integer>();
		List<EngineerDuty> engineerDuties = (List<EngineerDuty>) engineerDutyServiceImpl
				.findEngineerDutyByEmail(engineerEmail);
		
		Boolean engineerLoginStatus = engineerService.validateEngineer(engineerEmail, engineerPassword);
		System.out.println(engineerLoginStatus);
		if (engineerLoginStatus) {
			for (int i = 0; i < engineerDuties.size(); i++) {
				ticketIds.add(engineerDuties.get(i).getTicketId());
			}

			return ticketIds;
		}
		return null;

	}

	@PostMapping(path = "/addEngineer")
	public @ResponseBody void addEngineer(@RequestBody Engineers engineer) {
		engineerService.saveEngineer(engineer);

	}

	@DeleteMapping(path = "/deleteEngineer/{engineerEmail}")
	public @ResponseBody void deleteEngineer(@PathVariable("engineerEmail") String email) {
		Engineers engineer = engineerService.findEngineerById(email);
		engineerService.deleteEngineer(engineer);

	}

}








