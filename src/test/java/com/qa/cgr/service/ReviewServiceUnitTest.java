package com.qa.cgr.service;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.cgr.domain.Review;
import com.qa.cgr.repo.ReviewRepo;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceUnitTest {
	
	@InjectMocks
	private ReviewService reviewService;
	
	@Mock
	private ReviewRepo reviewRepo;
	
	@Test
	public void Create()
	{
		Review input = new Review(1l, "Zack", 10, "I really did not think I would like this game, but I am super happy I gave it a chance");
	}	

}
