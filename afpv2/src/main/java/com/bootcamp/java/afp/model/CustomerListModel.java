package com.bootcamp.java.afp.model;

import java.sql.Timestamp;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerListModel {

	@JsonProperty("idCustomerList")
	private Long id;
	
	@NotBlank(message="DNI no puede ser vacio")
	private String documentNumber;
	
	private double amount;
	
	private int accountNumber;

	@NotNull
	@Future
	@JsonProperty("date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Timestamp retirementDate;
	
	private int pensionFundId;
	
	private String namePensionFund;
}
