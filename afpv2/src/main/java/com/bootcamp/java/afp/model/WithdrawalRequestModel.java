package com.bootcamp.java.afp.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawalRequestModel {

	@JsonProperty("idWithdrawalRequest")
	private Long idWithdrawalRequest;
	
	@NotBlank(message="DNI no puede ser vacio")
	private String documentNumber;
	
	
	private double amount;
	
	private int pensionFundId;
	
	private String namePensionFund;
}
