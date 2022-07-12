package com.bootcamp.java.afp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.java.afp.domain.Customers;
import com.bootcamp.java.afp.domain.WithdrawalRequest;

public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest, Long> {

	List<Customers> findByDocumentNumberIgnoreCase(String documentNumber);

}
