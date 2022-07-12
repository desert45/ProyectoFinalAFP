package com.bootcamp.java.afp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bootcamp.java.afp.domain.*;
import com.bootcamp.java.afp.mapper.*;
import com.bootcamp.java.afp.model.*;
import com.bootcamp.java.afp.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

	private final CustomersRepository customersRepository;
	private final CustomerMapper customerMapper;
	private final PensionFunRepository pensionFunRepository;

	@Override
	public List<CustomersModel> findAll() throws Exception {
		List<Customers> customer = customersRepository.findAll();

		return customerMapper.customersToEventModels(customer);
	}

	@Override
	public CustomersModel findById(Long id) throws Exception {
		Optional<Customers> customer = customersRepository.findById(id);
		if (customer.isPresent())
			return customerMapper.customersToEventModel(customer.get());
		else
			throw new Exception("No se encontraron datos del cliente");
	}

	@Override
	public CustomersModel create(CustomersModel customersModel) throws Exception {
		/*
		 * Valida el tipo de pension que se ingresara: 1: PRIMA 2: INTEGRA 3: PROFUTURO
		 * 4: HABITAT
		 */
		Optional<PensionFund> pension = pensionFunRepository.findById((long) customersModel.getPensionFundId());
		if (!pension.isPresent()) {
			throw new Exception("El fondo de pensiones ingresado no existe, favor de ingresar uno valido");
		}
		// Valida que no exista 2 clientes registrados por el DNI.
		List<Customers> customer = customersRepository
				.findByDocumentNumberIgnoreCase(customersModel.getDocumentNumber());

		if (customer.isEmpty()) {
			customersModel.setNamePensionFund(pension.get().getName());
			Customers customers = customersRepository.save(customerMapper.customersModelToEvent(customersModel));
			return customerMapper.customersToEventModel(customers);
		} else
			throw new Exception(
					"El cliente con DNI " + customersModel.getDocumentNumber() + " ya se encuentra registrado");

	}

	@Override
	public void update(Long id, CustomersModel customersModel) throws Exception {

		// Valida que el ID ingresado exista
		Optional<Customers> customerOptional = customersRepository.findById(id);

		if (customerOptional.isPresent()) {
			Customers CustomerToUpdate = customerOptional.get();
			Optional<PensionFund> pension = pensionFunRepository.findById((long) customersModel.getPensionFundId());
			
			customersModel.setNamePensionFund(pension.get().getName());
			
			customerMapper.update(CustomerToUpdate, customersModel);
			customersRepository.save(CustomerToUpdate);
		} else
			throw new Exception("No se encontraron datos");
	}

	@Override
	public void deleteById(Long id) throws Exception {
		// Valida que el ID ingresado exista
		Optional<Customers> customer = customersRepository.findById(id);
		if (customer.isPresent()) {
			customersRepository.deleteById(id);
		} else {
			throw new Exception("No se encontro registro del cliente");
		}

	}

}
