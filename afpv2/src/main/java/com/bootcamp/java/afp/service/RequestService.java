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
public class RequestService implements IRequestService {

	private final WithdrawalRequestRepository withdrawalRequestRepository;
	private final WithdrawalRequestMapper withdrawalRequestMapper;
	private final PensionFunRepository pensionFunRepository;
	private final CustomerListRepository customerListRepository;

	@Override
	public List<WithdrawalRequestModel> findAll() throws Exception {
		List<WithdrawalRequest> request = withdrawalRequestRepository.findAll();

		return withdrawalRequestMapper.requestToEventModels(request);
	}

	@Override
	public WithdrawalRequestModel findById(Long id) throws Exception {
		Optional<WithdrawalRequest> request = withdrawalRequestRepository.findById(id);
		if (request.isPresent())
			return withdrawalRequestMapper.requestToEventModel(request.get());
		else
			throw new Exception("No se encontraron datos de la solicitud de retiro");
	}

	@Override
	public WithdrawalRequestModel create(WithdrawalRequestModel withdrawalRequestModel) throws Exception {
		/*
		 * Valida el tipo de pension que se ingresara: 1: PRIMA 2: INTEGRA 3: PROFUTURO
		 * 4: HABITAT
		 */
		Optional<PensionFund> pension = pensionFunRepository.findById((long) withdrawalRequestModel.getPensionFundId());
		if (!pension.isPresent()) {
			throw new Exception("El fondo de pensiones ingresado no existe, favor de ingresar uno valido");
		}

		// Valida si el cliente existe en la lista de clientes.
		List<CustomerList> request = customerListRepository.findByDocumentNumberIgnoreCase(withdrawalRequestModel.getDocumentNumber());

		if (!request.isEmpty()) {
			
			double monto = request.get(0).getAmount();

			 if (monto > withdrawalRequestModel.getAmount()) {
			withdrawalRequestModel.setNamePensionFund(pension.get().getName());
			
			WithdrawalRequest customers = withdrawalRequestRepository.save(withdrawalRequestMapper.requestModelToEvent(withdrawalRequestModel));
			return withdrawalRequestMapper.requestToEventModel(customers);
			 } else
				 throw new Exception(
							"No se puede registrar la solicitud. Monto mayor que el permitido");
		}else
			 throw new Exception(
				"El cliente con DNI " + withdrawalRequestModel.getDocumentNumber() + " ya tiene una solicitud pendiente o no existe");
	}

	@Override
	public void deleteById(Long id) throws Exception {
		// Valida que el ID ingresado exista
		Optional<WithdrawalRequest> request = withdrawalRequestRepository.findById(id);
		if (request.isPresent()) {
			withdrawalRequestRepository.deleteById(id);
		} else {
			throw new Exception("No se encontro registro de la solicitud");
		}

	}

}
