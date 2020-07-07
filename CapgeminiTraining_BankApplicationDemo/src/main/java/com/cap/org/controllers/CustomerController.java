package com.cap.org.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cap.org.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	

	@RequestMapping("/checkBalance")
	public String redirectToCheckBalanceJSP() {
		System.out.println("Check Balance COntroller ....");
		return "check-balance"; 
	}
	
	@RequestMapping("/checkBalanceDetails")
	public ModelAndView getBalance(@RequestParam("customerId") int customerId) {
		
		ModelAndView mov = new ModelAndView();
		
		double balance = customerService.getBalance(customerId);
		
		System.out.println("Balance Identified for "+customerId+" = "+balance);
		
		mov.setViewName("show-balance");
		mov.addObject("balance", balance);
		mov.addObject("customerId", customerId);
		
		return mov;
	}
	
	
	@RequestMapping("/transfer")
	public String redirectFundTransferJSP() {
		System.out.println("Transfer Controller ..Redirecting to JSP..");
		return "fund-transfer"; 
	}
	
	@RequestMapping("/fundTransfer")
	public ModelAndView fundTransfer(@RequestParam("debitCustomerID") int debitCustomerID,
			@RequestParam("creditCustomerId") int creditCustomerId,
			@RequestParam("balance") double balance) {
		
		System.out.println("Transferring Funds "+balance);
		ModelAndView mov = new ModelAndView();
		
		double initialBalanceCustomer1 = customerService.getBalance(debitCustomerID);
		double initialBalanceCustomer2 = customerService.getBalance(creditCustomerId);
		
		boolean transferStatus = customerService.transferBalance(debitCustomerID, creditCustomerId, balance);
		
		double updatedBalanceCustomer1 = customerService.getBalance(debitCustomerID);
		double updatedBalanceCustomer2 = customerService.getBalance(creditCustomerId);
		
		
		mov.setViewName("fund-transfer-confirmation");
		if(transferStatus) {
			
			mov.addObject("initialCustomer1", debitCustomerID+" Initial Balance "+initialBalanceCustomer1);
			mov.addObject("initialCustomer2", creditCustomerId+" Initial Balance "+initialBalanceCustomer2);
			
			mov.addObject("updatedBalanceCustomer1", debitCustomerID+" Updated Balance "+updatedBalanceCustomer1);
			mov.addObject("updatedBalanceCustomer2", creditCustomerId+" Updated Balance "+updatedBalanceCustomer2);
			
			mov.addObject("transferStatus", "Success");
			return mov;
		}else {
			mov.addObject("transferStatus", "Failed");
			return mov;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
