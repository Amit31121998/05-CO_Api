package com.amit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.entity.CaseWorker;

public interface CaseWorkerRepo extends JpaRepository<CaseWorker, Integer> {
	
	
	public CaseWorker findByCaseWorkerEmail(String email);
	
	public CaseWorker findByCaseWorkerEmailAndCaseWorkerPwd(String email, String pwd);
	


}
