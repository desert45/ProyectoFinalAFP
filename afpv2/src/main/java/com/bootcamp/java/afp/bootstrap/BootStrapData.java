package com.bootcamp.java.afp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bootcamp.java.afp.domain.*;
import com.bootcamp.java.afp.repository.*;

@Component
public class BootStrapData implements CommandLineRunner {

	private final PensionFunRepository pensionFunRepository;

	public BootStrapData(PensionFunRepository pensionFunRepository) {
		super();
		this.pensionFunRepository = pensionFunRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		pensionFunRepository.save(PensionFund.builder().name("PRIMA").build());

		pensionFunRepository.save(PensionFund.builder().name("INTEGRA").build());

		pensionFunRepository.save(PensionFund.builder().name("PROFUTURO").build());

		pensionFunRepository.save(PensionFund.builder().name("HABITAT").build());

		System.out.println("Pension Fund Initialized");

	}

}
