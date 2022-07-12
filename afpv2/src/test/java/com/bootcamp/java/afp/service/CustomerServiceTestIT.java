package com.bootcamp.java.afp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.bootcamp.java.afp.domain.Customers;
import com.bootcamp.java.afp.domain.PensionFund;
import com.bootcamp.java.afp.mapper.CustomerMapper;
import com.bootcamp.java.afp.model.CustomersModel;
import com.bootcamp.java.afp.repository.CustomersRepository;
import com.bootcamp.java.afp.repository.PensionFunRepository;

@SpringBootTest(classes = {CustomerMapper.class})
class CustomerServiceTestIT {
 
	@Mock
	CustomersRepository customersRepository;
	
	@Mock
	PensionFunRepository pensionFunRepository;
	
	@Spy
	private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
	 
	
	CustomerService customerService;
	
	@BeforeEach
	void beforeEach() {
		customerService = new CustomerService(customersRepository, customerMapper,pensionFunRepository);
	}
		
	@Test
	void create() throws Exception 
	{
		 //given
			 
		 Customers customers = Customers.builder()
				 	   .id(Long.valueOf(1))
				 	   .name("Antony")
				 	   .lastName("Fernandez")
				 	   .documentNumber("71893700")
				 	   .email("antonydesert@gmail.com")
				 	   .phoneNumber(941489609)
				 	   .pensionFundId(1)
				 	   .build();
		 given(customersRepository.save(any(Customers.class))).willReturn(customers);
		 
		 //when		
	 

		 CustomersModel eventsToCreate = customerMapper.customersToEventModel(customers);
		 CustomersModel eventsCreated = customerService.create(eventsToCreate);
	     
	     //then
	     then(customersRepository).should().save(any(Customers.class));
	     assertThat(eventsCreated).isNotNull();
	}
	
	@Test
	void update() throws Exception 
	{
		 //given
		 Customers customers = Customers.builder()
				 	.id(Long.valueOf(1))
			 	   .name("Antony")
			 	   .lastName("Fernandez")
			 	   .documentNumber("71893700")
			 	   .email("antonydesert@gmail.com")
			 	   .phoneNumber(941489609)
			 	   .pensionFundId(2)
			 	   .namePensionFund("PROFUTURO")
			 	   .build();
		 given(customersRepository.findById(Long.valueOf(1))).willReturn(Optional.of(customers));
	 
		 //when		
		 CustomersModel eventsToUpdate = customerMapper.customersToEventModel(customers);
	     customerService.update(Long.valueOf(1), eventsToUpdate);
	     
	     //then
	     then(customersRepository).should().save(any(Customers.class));
	}
	
	@Test
	void updateNotFound() throws Exception 
	{
		Customers customers = Customers.builder()
				 	.id(Long.valueOf(1))
			 	   .name("Antony")
			 	   .lastName("Fernandez")
			 	   .documentNumber("71893700")
			 	   .email("antonydesert@gmail.com")
			 	   .phoneNumber(941489609)
			 	   .pensionFundId(1)
			 	   .build();
		 CustomersModel eventsToUpdate = customerMapper.customersToEventModel(customers);
		    
		 assertThrows(Exception.class, () -> customerService.update(Long.valueOf(1), eventsToUpdate));
	}
		
	@Test
	void findAll() throws Exception 
	{
		 //given
		 List<Customers> customers = new ArrayList<Customers>();
		 customers.add(Customers.builder()
				 	.id(Long.valueOf(1))
				 	.name("Antony")
				 	.lastName("Fernandez")
			 	   	.documentNumber("71893700")
			 	   	.email("antonydesert@gmail.com")
			 	   	.phoneNumber(941489609)
			 	   	.pensionFundId(1)
				 	.build());
		 
		 customers.add(Customers.builder()
				 	.id(Long.valueOf(2))
			 	   .name("Joam")
			 	   .lastName("Perez")
			 	   .documentNumber("71893701")
			 	   .email("Joam@gmail.com")
			 	   .phoneNumber(941489453)
			 	   .pensionFundId(2)
			 	   .build());
		 
		 given(customersRepository.findAll()).willReturn(customers);
		 
		 //when
	     List<CustomersModel> customersFindAll = customerService.findAll();
	     
	     //then
	     then(customersRepository).should().findAll();
	     assertThat(customersFindAll).isNotNull();
	}
	
	@Test
	void findById() throws Exception 
	{
		 //given
		 Customers customers = new Customers();
		 given(customersRepository.findById(Long.valueOf(1))).willReturn(Optional.of(customers));
		 
		 //when
	     CustomersModel customersFindById = customerService.findById(Long.valueOf(1));
	     
	     //then
	     then(customersRepository).should().findById(Long.valueOf(1));
	     assertThat(customersFindById).isNotNull();
	}
	
	@Test
	void findByIdNotFound() throws Exception 
	{
		 assertThrows(Exception.class, () -> customerService.findById(Long.valueOf(1)));
	}
	
		
	@Test
	void deleteById() throws Exception{
		 //given
		
		 //when		
	     customerService.deleteById(Long.valueOf(1));
	     
	     //then
	     then(customersRepository).should().deleteById(anyLong());
	}
	
}
