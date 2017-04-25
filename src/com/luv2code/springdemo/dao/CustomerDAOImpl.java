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

	//Returns a list of the customers in the database
	@Override
	public List<Customer> getCustomers() {

		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//create a query ... sort by lastName and then sort by firstName
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName, firstName",
						Customer.class);

		//execute query and get result list

		List<Customer> customers = theQuery.getResultList();

		//return the list of customers
		return customers;

	}
	//Saves a customer to the database
	@Override
	public void saveCustomer(Customer theCustomer) {

		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save the customer if new or Update if the customer already exists
		currentSession.saveOrUpdate(theCustomer);
	}

	//Retrieve a customer from the database
	@Override
	public Customer getCustomer(int theId) {

		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// retreive/read from database using the primary key

		Customer theCustomer = currentSession.get(Customer.class, theId);

		return theCustomer;
	}

	//Delete a customer with a specific id
	@Override
	public void deleteCustomer(int theId) {

		//get current hibernate session

		Session currentSession = sessionFactory.getCurrentSession();

		//delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId",theId);
		theQuery.executeUpdate();

	}

}
