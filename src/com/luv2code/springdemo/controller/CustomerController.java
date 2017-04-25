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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("/SaveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}

	@GetMapping("showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){
		
		//get the customer from the service
		Customer theCustomer = customerService.getCustomer(theId);
				
		//set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
		return "customer-form";
				
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId){
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		//send over to our form
		return "redirect:/customer/list";
				
	}
	
	
	
	
}
