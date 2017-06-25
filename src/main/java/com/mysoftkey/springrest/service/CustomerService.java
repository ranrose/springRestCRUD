/**
 * 
 */
package com.mysoftkey.springrest.service;

import java.util.List;

import com.mysoftkey.springrest.model.Customer;

/**
 * This service class is used for Customer related things.
 * 
 * @author Ranjeet Jha
 *
 */
public interface CustomerService {


 /**
  * @param customer
  * @throws Exception
  */
 public void addCustomer(Customer customer) throws Exception;
 
 /**
  * This method is used to add Customer object in repository.
  * 
  * @param customer
  * @throws Exception
  */
 public boolean upateCustomer(Customer customer) throws Exception;
 
 /**
  * This method is used to delete Customer object by provided id from repository.
  * 
  * @param id
  * @throws Exception
  */
 public boolean deleteCustomerById(Long id) throws Exception;
 
 /**
  * This method is used to get list of  Customer object from repository.
  * 
  * @return
  * @throws Exception
  */
 public List<Customer> getCustomers() throws Exception;
 
 /**
  * This method is used to get Customer object by customer id from repository.
  * 
  * @param id
  * @return
  * @throws Exception
  */
 public Customer getCustomerById(Long id) throws Exception;
 
}
