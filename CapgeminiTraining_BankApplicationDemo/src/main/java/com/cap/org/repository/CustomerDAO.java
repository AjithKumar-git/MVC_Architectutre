package com.cap.org.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cap.org.domain.Account;
import com.cap.org.domain.Customer;
import com.cap.org.hibernateConfiguration.HibernateConfiguration;

@Repository
public class CustomerDAO/* implements CustomerRepo */{


	public Customer getCustomer(int customerId) {
		
		SessionFactory factory = HibernateConfiguration.hibernateConfig();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		System.out.println("Checking Customer in database for ID : "+customerId);
		
		//Customer customer = session.get(Customer.class, customerId);
		
		String hql = "From Customer c where c.customerId = :customerId";
		Query query = session.createQuery(hql);
		query.setParameter("customerId",customerId);
		
		List<Customer> customerList = (List<Customer>) query.list();
		Customer customer = null;
		
		if(customerList.size() > 0)
			customer = customerList.get(0);
		System.out.println(customerId+ "-> "+customer);
		
		return customer;
	}

	public double getBalance(int customerId) {
		
		Customer customer = getCustomer(customerId);
		double balance = 0.00;
		
		
		if(customer != null) {
			Account account = customer.getAccount();
			balance = account.getBalance();
		}
		
		return balance;
	}

	public boolean transferBalance(int debitCustomerID, int creditCustomerId, double balance) {
		
		Customer debitCustomer = getCustomer(debitCustomerID);
		Customer creditCustomer = getCustomer(creditCustomerId);
		
		if(debitCustomer != null && creditCustomer !=null) {
			
			SessionFactory factory = HibernateConfiguration.hibernateConfig();
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			
			if(debitCustomer.getAccount().getBalance() >= balance) {
			
				System.out.println("Current balances :");
				System.out.println("Debit Account : "+debitCustomer.getAccount().getBalance());
				System.out.println("Credit Account : "+creditCustomer.getAccount().getBalance());
				
				debitCustomer.getAccount().setBalance(debitCustomer.getAccount().getBalance() - balance);
				creditCustomer.getAccount().setBalance(creditCustomer.getAccount().getBalance() + balance);
				
				session.saveOrUpdate(debitCustomer);
				session.saveOrUpdate(creditCustomer);
				
				session.saveOrUpdate(debitCustomer.getAccount());
				session.saveOrUpdate(creditCustomer.getAccount());
				
				System.out.println("Transfer Successful");
				session.getTransaction().commit();
				return true;
			}
			
			return false;
			
		}
		
		
		return false;
	}

}
