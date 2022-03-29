package com.springboot.service;

import java.util.List;

import com.springboot.model.Customers;

public interface CustomerService {

	List<Customers> fetchAllCustomers();

	boolean saveCustomer(Customers customer);

	Customers findCustomerById(String email);

	void deleteCustomer(Customers customer);

	Boolean validateCustomer(String customerEmail, String customerPassword);
}
