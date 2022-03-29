package com.springboot.controller;

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

import com.springboot.model.Feedbacks;
import com.springboot.repository.FeedbackRepository;
import com.springboot.service.FeedbackService;
import com.springboot.service.FeedbackServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/feedbacks")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;

	@Autowired
	FeedbackServiceImpl feedbackServiceImpl;

	@Autowired
	FeedbackRepository feedbackRepository;

	@GetMapping("/getAllFeedbacks")
	public List<Feedbacks> getAllFeedbacks() {
		List<Feedbacks> feedbacks = (List<Feedbacks>) feedbackService.fetchAllFeedbacks();
		return feedbacks;
	}

	@PostMapping(path = "/addFeedback")
	public @ResponseBody void addFeedback(@RequestBody Feedbacks feedback) {
		System.out.println(feedback.getFeedback());
		feedbackService.saveFeedback(feedback);

	}

	@DeleteMapping(path = "/deleteFeedbackById/{feedbackId}")
	public @ResponseBody void deleteComplaintById(@PathVariable("feedbackId") int feedbackId) {
		System.out.println(feedbackId + "----------- ");
		Feedbacks feedback = feedbackServiceImpl.findFeedbackById(feedbackId);
		feedbackServiceImpl.deleteFeedback(feedback);

	}

	@PutMapping("/updateFeedback")
	public boolean updateFeedback(@RequestBody Feedbacks feedback) {
		System.out.println(feedback.getTicketId() + "-------");
		Feedbacks existingFeedbacks = feedbackServiceImpl.findFeedbackById(feedback.getFeedbackId());
		existingFeedbacks.setTicketId(feedback.getTicketId());
		existingFeedbacks.setCustomerEmail(feedback.getCustomerEmail());
		existingFeedbacks.setFeedback(feedback.getFeedback());

		feedbackRepository.save(existingFeedbacks);

		return true;
	}

}
