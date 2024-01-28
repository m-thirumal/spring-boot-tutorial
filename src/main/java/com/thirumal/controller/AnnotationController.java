/**
 * 
 */
package com.thirumal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thirumal.annotation.MeasureExecutionTime;
import com.thirumal.service.AnnotationService;

/**
 * @author Thirumal
 */
@RestController
@RequestMapping("/annotaion")
public class AnnotationController {
	
	AnnotationService annotationService;

	/**
	 * @param annotationService
	 */
	public AnnotationController(AnnotationService annotationService) {
		super();
		this.annotationService = annotationService;
	}

	@MeasureExecutionTime
	@GetMapping("")
	public String annotaion() {
		return annotationService.get();
	}
}
