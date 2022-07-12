package com.bootcamp.java.afp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.java.afp.domain.CustomerList;
import com.bootcamp.java.afp.domain.Customers;

public interface CustomerListRepository extends JpaRepository<CustomerList, Long> {

	List<CustomerList> findByDocumentNumberIgnoreCase(String documentNumber);

}
