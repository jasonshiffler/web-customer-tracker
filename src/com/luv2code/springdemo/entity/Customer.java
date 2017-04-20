/**
 * @author jason
 * This is an entity class that maps a customer object to the customer table in the database
 */

package com.luv2code.springdemo.entity;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;


@Entity                      //This is an Entity Class
@Table(name="customer")      //This class maps to the customer table
public class Customer {

	@Id                                                //Specifies the entity field is a primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)  //Allow mysql to autoincrement
	@Column(name="id")                                 //Specify the name of the actual column in the database
	private int id;
	private String firstName;
	private String lastName;
	private String email;


}
