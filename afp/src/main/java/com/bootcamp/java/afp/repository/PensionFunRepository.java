package com.bootcamp.java.afp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.java.afp.domain.PensionFund;

public interface PensionFunRepository extends JpaRepository<PensionFund, Long> {

}
