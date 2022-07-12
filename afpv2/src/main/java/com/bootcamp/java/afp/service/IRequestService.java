package com.bootcamp.java.afp.service;

import java.util.List;

import com.bootcamp.java.afp.model.*;

public interface IRequestService {
	
	List<WithdrawalRequestModel> findAll() throws Exception;
	
	WithdrawalRequestModel findById(Long id) throws Exception;

	WithdrawalRequestModel create(WithdrawalRequestModel withdrawalRequestModel) throws Exception;

	void deleteById(Long id) throws Exception;

}