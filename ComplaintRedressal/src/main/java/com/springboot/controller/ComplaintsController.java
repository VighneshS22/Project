package com.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Complaints;
import com.springboot.repository.ComplaintRepository;
import com.springboot.service.ComplaintServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/complaints")
public class ComplaintsController {

	@Autowired
	ComplaintServiceImpl complaintServiceImpl;

	@Autowired
	ComplaintRepository complaintRepository;

	@GetMapping("/getAllComplaints")
	public List<Complaints> getAllComplaints() {
		List<Complaints> complaints = (List<Complaints>) complaintRepository.findAll();
		return complaints;
	}

	@GetMapping("/getAllComplaintsByEmail/{customerEmail}")
	public List<Complaints> getAllComplaintsByEmail(@PathVariable("customerEmail") String customerEmail) {
		System.out.println("inside complaints controller -- " + customerEmail);

		List<Complaints> complaints = (List<Complaints>) complaintServiceImpl.findComplaintByEmail(customerEmail);
		return (List<Complaints>) complaints;
	}

	@PostMapping("/getAllComplaintsByTicketIds")
	public List<Complaints> getAllComplaintsByTicketIds(@RequestBody int[] ticketIds) {

		System.out.println("ticket Ids from Backend -- " + ticketIds);
		List<Complaints> complaints = new ArrayList<Complaints>();
		for (int i = 0; i < ticketIds.length; i++) {
			System.out.println(ticketIds[i]);
			complaints.add(complaintServiceImpl.findComplaintById(ticketIds[i]));
		}
		return (List<Complaints>) complaints;

	}

	@GetMapping("/getComplaintById/{ticketId}")
	public Complaints getComplaintById(@PathVariable("ticketId") int ticketId) {
		Complaints complaint = complaintServiceImpl.findComplaintById(ticketId);
		return complaint;
	}

	@PostMapping(path = "/addComplaint")
	public @ResponseBody void addComplaint(@RequestBody Complaints complaint) {
		complaintServiceImpl.saveComplaint(complaint);

	}

	@DeleteMapping(path = "/deleteComplaintById/{ticketId}")
	public @ResponseBody void deleteComplaintById(@PathVariable("ticketId") int ticketId) {
		Complaints complaint = complaintServiceImpl.findComplaintById(ticketId);
		complaintServiceImpl.deleteComplaint(complaint);

	}

	@PutMapping("/updateComplaint")
	public boolean updateComplaint(@RequestBody Complaints complaint) {
		System.out.println(complaint.getTicketId() + "-------");
		Complaints existingComplaint = complaintServiceImpl.findComplaintById(complaint.getTicketId());
		existingComplaint.setComplaint(complaint.getComplaint());
		existingComplaint.setCustomerEmail(complaint.getCustomerEmail());
		existingComplaint.setPincode(complaint.getPincode());
		existingComplaint.setStatus(complaint.getStatus());
		complaintRepository.save(existingComplaint);

		return true;
	}

}
