package com.cap.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.org.domain.Customer;
import com.cap.org.repository.CustomerDAO;

@Service
public class LoginServiceImpl /* implements LoginService */{

//	@Autowired
//	private CustomerRepo customerRepo;
	
	@Autowired
	private CustomerDAO customerRepo;
	
	
	public boolean checkLogin(String name, int customerId) {
		
		Customer customer = customerRepo.getCustomer(customerId);
		
		System.out.println("Identfied Customer from DAO -> "+customer);
		
		if(customer != null) {
			if(customer.getName().equalsIgnoreCase(name) && customer.getCustomerId() == customerId)
				return true;
		}
		
		return false;
	}
	
	public double getBalance(int customerId) {
		
		return 0.0;
	}

	
}
