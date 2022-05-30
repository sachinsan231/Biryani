/**
 * 
 */
package com.github.biryani.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.biryani.services.BiryaniService;
import com.github.biryani.web.model.BiryaniDto;

/**
 * @author kadam.sachin
 *
 */

@RestController
@RequestMapping("/api/v1/biryani")
public class BiryaniController {
	
	private final BiryaniService service;

	public BiryaniController(BiryaniService service) {
		super();
		this.service = service;
	}
	
	@GetMapping({"/{beerId}"})
	public ResponseEntity<BiryaniDto> getBiryani(@PathVariable("beerId") UUID beerId){
		return new ResponseEntity<>(service.getBiryani(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity handlePost(@Validated @RequestBody BiryaniDto dto) {
		BiryaniDto savedDto = service.saveBiryani(dto);
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		headers.add("Location", "/api/v1/biryani"+savedDto.getId().toString());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@PutMapping({"/{beerId}"})
	public ResponseEntity updateBiryani(@PathVariable("beerId") UUID beerId, @RequestBody BiryaniDto biryaniDto) {
		service.updateBiryani(beerId, biryaniDto);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List> exceptionHandler(MethodArgumentNotValidException e){
		
		
		List<String> errors = new ArrayList(e.getErrorCount());
		e.getBindingResult().getAllErrors().forEach(constr -> {
			errors.add(constr.getDefaultMessage()+" : "+constr.getObjectName());
		});
		return new ResponseEntity<List>(errors, HttpStatus.BAD_REQUEST);
	}

}
