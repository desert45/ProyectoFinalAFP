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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.java.afp.model.CustomersModel;
import com.bootcamp.java.afp.service.ICustomerService;

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
@RequestMapping("/v1/customer")
@Slf4j
public class CustomersController {

	@Autowired
	private final ICustomerService curstomerService;
	
	/**
	 * Get list of Customers
	 * @return
	 * @throws Exception
	 */
	@GetMapping()	
	@Operation(summary = "Get list of Customer")
	public ResponseEntity<Object> getAll() throws Exception {
		List<CustomersModel> response =  curstomerService.findAll();
		log.info("getAll" + "OK");
		log.debug(response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	/**
	 * Get Customer by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Get Customer by id")
	public ResponseEntity<CustomersModel> getById(@PathVariable("id") Long id) throws Exception{
		CustomersModel response = curstomerService.findById(id);
		log.info("getById" + "OK");
		log.debug(id.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Create Customers
	 * @param customersModel
	 * @return
	 * @throws Exception
	 */
	@PostMapping()
	@Operation(summary = "Create Customers")
	public ResponseEntity<Object> create(@Valid @RequestBody CustomersModel customersModel) throws Exception {
		CustomersModel response = curstomerService.create(customersModel);
		log.info("create" + "OK");
		log.debug(customersModel.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Update customer by id
	 * @param id
	 * @param customersModel
	 * @throws Exception
	 */
	@PutMapping(path = { "{id}" }, produces = { "application/json" })
	@Operation(summary = "Update customer by id")
	public void update(		
			@PathVariable("id") Long id,
			@Valid @RequestBody CustomersModel customersModel) throws Exception {		
		curstomerService.update(id, customersModel);
		log.info("update" + "OK");
		log.debug(id.toString()+ "/" + customersModel.toString());
	}
	
	/**
	 * Delete customer by id
	 * @param id
	 * @throws Exception
	 * @author aafernandez
	 */
	@DeleteMapping({ "{id}" })
	@Operation(summary = "Delete customer by id")
	public void deleteById(@PathVariable("id") Long id) throws Exception {
		
		curstomerService.deleteById(id);
		log.info("deleteById" + "OK");
		log.debug(id.toString());
	}
	
}
