package com.springboot.service;

import java.util.List;

import com.springboot.model.Feedbacks;

public interface FeedbackService {

	List<Feedbacks> fetchAllFeedbacks();

	void saveFeedback(Feedbacks feedback);

}
