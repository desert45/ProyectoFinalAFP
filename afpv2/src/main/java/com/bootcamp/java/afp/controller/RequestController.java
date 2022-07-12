package com.bootcamp.java.afp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.java.afp.model.CustomersModel;
import com.bootcamp.java.afp.model.WithdrawalRequestModel;
import com.bootcamp.java.afp.service.ICustomerService;
import com.bootcamp.java.afp.service.IRequestService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for Customers
 * @author aafernandez
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/request")
@Slf4j
public class RequestController {

	@Autowired
	private final IRequestService requestService;
	
	/**
	 * Get list of request
	 * @return
	 * @throws Exception
	 */
	@GetMapping()	
	@Operation(summary = "Get list of Customer")
	public ResponseEntity<Object> getAll() throws Exception {
		List<WithdrawalRequestModel> response =  requestService.findAll();
		log.info("getAll" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Get request by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Get Customer by id")
	public ResponseEntity<WithdrawalRequestModel> getById(@PathVariable("id") Long id) throws Exception{
		WithdrawalRequestModel response = requestService.findById(id);
		log.info("getById" + "OK");
		log.debug(id.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Create request
	 * @param customersModel
	 * @return
	 * @throws Exception
	 */
	@PostMapping()
	@Operation(summary = "Create Customers")
	public ResponseEntity<Object> create(@Valid @RequestBody WithdrawalRequestModel withdrawalRequestModel) throws Exception {
		WithdrawalRequestModel response = requestService.create(withdrawalRequestModel);
		log.info("create" + "OK");
		log.debug(withdrawalRequestModel.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Delete request by id
	 * @param id
	 * @throws Exception
	 * @author aafernandez
	 */
	@DeleteMapping({ "{id}" })
	@Operation(summary = "Delete customer by id")
	public void deleteById(@PathVariable("id") Long id) throws Exception {
		
		requestService.deleteById(id);
		log.info("deleteById" + "OK");
		log.debug(id.toString());
	}
	
}
