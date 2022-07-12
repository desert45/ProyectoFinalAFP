package com.bootcamp.java.afp.service;

import java.util.List;

import com.bootcamp.java.afp.model.*;

public interface ICustomerService {
	
	List<CustomersModel> findAll() throws Exception;
	
	CustomersModel findById(Long id) throws Exception;

	CustomersModel create(CustomersModel customersModel) throws Exception;

	void update(Long id, CustomersModel customersModel) throws Exception;

	void deleteById(Long id) throws Exception;

}