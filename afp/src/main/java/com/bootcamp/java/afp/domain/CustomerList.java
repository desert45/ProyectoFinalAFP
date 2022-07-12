package com.bootcamp.java.afp.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

public class CustomerList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRequest;
	
	@NotNull
	@Column(unique=true)
	private int documentNumber;
	
	@NotNull
	private double amount;
	
	@NotNull
	private int accountNumber;
	
	@NotNull
	@Future
	private Timestamp retirementDate;
	
	@NotNull
	private Long pensionFoundId;
	
}
