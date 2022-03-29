package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Customers;
import com.springboot.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customers> fetchAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public boolean saveCustomer(Customers customer) {
		try {
			customerRepository.save(customer);
			return true;
		} catch (Exception e) {
			System.out.println("Customer with Same Phone Already Exists: " + e);
			return false;
		}
	}

	@Override
	public Customers findCustomerById(String email) {
		Customers customer = customerRepository.findById(email).orElse(null);
		return customer;
	}

	@Override
	public void deleteCustomer(Customers customer) {
		customerRepository.delete(customer);
	}

	@Override
	public Boolean validateCustomer(String customerEmail, String customerPassword) {

		if (customerRepository.findById(customerEmail).isPresent()) {
			Customers customer = customerRepository.findById(customerEmail).get();
			String dbPassword = customer.getCustomerPassword().toString();
			if (dbPassword.equals(customerPassword)) {
				return true;
			}
		}
		return false;
	}

}
