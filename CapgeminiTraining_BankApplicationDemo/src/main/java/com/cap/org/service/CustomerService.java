package com.cap.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.org.repository.CustomerDAO;

@Service
public class CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	public double getBalance(int customerId) {
		
		return customerDAO.getBalance(customerId);
		
	}

	public boolean transferBalance(int debitCustomerID, int creditCustomerId, double balance) {
		
		return customerDAO.transferBalance(debitCustomerID, creditCustomerId, balance);
		
	}
	
	

}
