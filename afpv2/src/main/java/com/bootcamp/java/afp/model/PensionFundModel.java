package com.bootcamp.java.afp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PensionFundModel {

	@JsonProperty("idPensionFund")
	private Long id;
	
	private String name;
}
