package com.bootcamp.java.afp.domain;

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
public class WithdrawalRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idWithdrawalRequest;
	
	@NotNull
	@Column(unique=true)
	private String documentNumber;
	
	@NotNull
	private double amount;
	
	@NotNull
	private int pensionFundId;
	
	private String namePensionFund;
	
}
