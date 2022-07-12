package com.bootcamp.java.afp.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CustomerList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRequest;
	
	@NotNull
	@Column(unique=true)
	private String documentNumber;
	
	@NotNull
	private double amount;
	
	@NotNull
	private int accountNumber;
	
	@NotNull
	private Timestamp retirementDate;
	
	private int pensionFoundId;
	
}
