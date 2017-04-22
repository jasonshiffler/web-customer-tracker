/**
 * @author jason
 * This is the controller to handle all web requests to /customer and sub URLs
 */

package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.entity.*;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.dao.CustomerDAO;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// Need to inject the customer service 
	@Autowired
	private CustomerService customerService;
	
	//We only want get requests 
	@GetMapping("/list")  
	public String listCustomers(Model theModel){
		
		// get the customers from the Service
		
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add the customers to the model
		
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
		
	}

	//the mapping for the add customer Form
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		
		//create model attribute to bind form data
		
		Customer theCustomer = new Customer();
		
		theModel.addAttribute(theCustomer);
		
		return "customer-form";
		
	}
	
	
}
