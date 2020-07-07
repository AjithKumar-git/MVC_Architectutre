package com.cap.org.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginService {

	public boolean checkLogin(String name, int customerId);
}
