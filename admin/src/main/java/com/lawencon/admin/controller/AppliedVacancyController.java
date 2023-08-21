package com.lawencon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.admin.dto.InsertResDto;
import com.lawencon.admin.dto.UpdateResDto;
import com.lawencon.admin.dto.appliedvacancy.InsertAppliedVacancyReqDto;
import com.lawencon.admin.dto.appliedvacancy.UpdateProgressReqDto;
import com.lawencon.admin.service.AppliedVacancyService;

@RestController
@RequestMapping("/applied")
public class AppliedVacancyController {

	@Autowired
	private AppliedVacancyService appliedVacancyService;
	
	@PutMapping
	public ResponseEntity<UpdateResDto> changeStatus(@RequestBody UpdateProgressReqDto data) {
		final UpdateResDto response = appliedVacancyService.changeAppliedStatusProgress(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/apply")
	public ResponseEntity<InsertResDto> apply(@RequestBody InsertAppliedVacancyReqDto data) {
		final InsertResDto response = appliedVacancyService.insertAppliedVacancy(data);
		return new ResponseEntity<InsertResDto>(response, HttpStatus.CREATED);
	}

}
