package com.bootcamp.java.afp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bootcamp.java.afp.model.CustomersModel;
import com.bootcamp.java.afp.service.ICustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@WebMvcTest(CustomersController.class)
public class CustomersControllerTestIT {

	@MockBean
	private ICustomerService customerService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAll() throws Exception {
		mockMvc.perform(get("/v1/customer").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void getById() throws Exception {
		mockMvc.perform(get("/v1/customer/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void create() throws Exception {
		CustomersModel customers = CustomersModel.builder().id(Long.valueOf(1)).name("Antony").lastName("Fernandez")
				.documentNumber("71893700").email("antonydesert@gmail.com").phoneNumber(941489609).pensionFundId(1)
				.namePensionFund("PRIMA").build();

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/customer").content(asJsonString(customers))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void update() throws Exception {
		CustomersModel customers = CustomersModel.builder().id(Long.valueOf(1)).name("Antony").lastName("Fernandez")
				.documentNumber("71893700").email("antonydesert@gmail.com").phoneNumber(941489609).pensionFundId(1)
				.namePensionFund("PRIMA").build();

		mockMvc.perform(MockMvcRequestBuilders.put("/v1/customer/1").content(asJsonString(customers))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	void deleteById() throws Exception {
		mockMvc.perform(delete("/v1/customer/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) throws JsonProcessingException {

		return new ObjectMapper().writeValueAsString(obj);
	}

}
