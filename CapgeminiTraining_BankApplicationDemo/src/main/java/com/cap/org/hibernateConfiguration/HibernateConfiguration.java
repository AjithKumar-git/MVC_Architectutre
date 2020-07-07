package com.cap.org.hibernateConfiguration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cap.org.domain.Account;
import com.cap.org.domain.Customer;

public class HibernateConfiguration {

	public static SessionFactory hibernateConfig() {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(Account.class)
				.buildSessionFactory(); 
		
		
		return factory;
	}
	
}
