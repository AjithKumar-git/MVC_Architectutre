package com.cap.org.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;
	
	
	private String name;
	
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "account_number")
	private Account account;


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", account=" + account + "]";
	}
	
	

}
