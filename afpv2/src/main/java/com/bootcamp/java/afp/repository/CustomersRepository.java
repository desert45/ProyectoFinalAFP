package com.bootcamp.java.afp.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bootcamp.java.afp.domain.*;


public interface CustomersRepository extends JpaRepository<Customers, Long>  {
	List<Customers> findByDocumentNumberIgnoreCase(String documentNumber);
}
