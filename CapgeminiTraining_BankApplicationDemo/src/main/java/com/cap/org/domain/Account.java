package com.cap.org.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_number")
	private int accountNumber;
	
	
	private double balance;
	
	
	@OneToOne(mappedBy = "account",fetch = FetchType.LAZY, 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE,
						CascadeType.DETACH, CascadeType.REFRESH})
	private Customer customer;


	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}
	
}


























