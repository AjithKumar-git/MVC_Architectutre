package com.cap.org.repository;

import org.springframework.stereotype.Repository;

import com.cap.org.domain.Customer;

@Repository
public interface CustomerRepo {

	public Customer getCustomer(int customerId);
	
}
