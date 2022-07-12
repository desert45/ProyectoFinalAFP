package com.bootcamp.java.afp.model;

import javax.validation.constraints.NotBlank;

import com.bootcamp.java.afp.domain.PensionFund;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomersModel {
	
	@JsonProperty("idCustomer")
	private Long id;

	@NotBlank(message="Nombres no puede ser vacio")
	private String name;
	
	@NotBlank(message="Apellidos no puede ser vacio")
	private String lastName;
	
	private String documentNumber;

	private int phoneNumber;

	private String email;
	
	private int pensionFundId;
	
	private String namePensionFund;
}
