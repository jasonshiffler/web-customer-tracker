package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service //this is a Service Layer
public class CustomerServiceImpl implements CustomerService {

	//need to inject customer dao
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional // let the customer layer handle the transaction
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers(); //Delegate the call to the customer dAO
		
		
	}

}
