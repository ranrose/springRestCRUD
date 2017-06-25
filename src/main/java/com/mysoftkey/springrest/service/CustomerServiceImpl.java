/**
 * 
 */
package com.mysoftkey.springrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysoftkey.springrest.dao.CustomerDao;
import com.mysoftkey.springrest.model.Customer;

/**
 * This service impl class is used for customer entity.
 * 
 * @author Ranjeet Jha
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

 @Autowired
 private CustomerDao customerDao;
 
 public void addCustomer(Customer customer) throws Exception {
  customerDao.addCustomer(customer);
  
 }

 public boolean upateCustomer(Customer customer) throws Exception {
  return customerDao.upateCustomer(customer);
  
 }

 public boolean deleteCustomerById(Long id) throws Exception {
  return customerDao.deleteCustomerById(id);
  
 }

 public List<Customer> getCustomers() throws Exception {
  return customerDao.getCustomers();
 }

 public Customer getCustomerById(Long id) throws Exception {
  return customerDao.getCustomerById(id);
 }

}
