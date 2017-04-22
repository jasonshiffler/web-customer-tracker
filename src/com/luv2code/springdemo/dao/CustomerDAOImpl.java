/*This class implements the Customer DAO interface and allows us to interface
 * with the database

*/
package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository //Annotation for DAO implementations, also allows class to be component scanned
public class CustomerDAOImpl implements CustomerDAO {

	//Need to inject the sessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer", Customer.class);

		//execute query and get result list

		List<Customer> customers = theQuery.getResultList();

		//return the list of customers
		return customers;



	}

}
