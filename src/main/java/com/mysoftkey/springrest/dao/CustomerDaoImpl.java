/**
 * 
 */
package com.mysoftkey.springrest.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mysoftkey.springrest.model.Customer;

/**
 * this implementation class is DAO (Data Access Object) for Customer entity.
 * 
 * @author ranjeet jha
 *
 */
@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

 private final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

 // this is in-memory customer repository for demonstration purpose
 private static List<Customer> customers = new ArrayList<Customer>();
 
 // get the incremented value and initialize with 1 (provided arg)
 AtomicLong atomicLong = new AtomicLong(1);

 
 public void init() {
  customers.add(new Customer(atomicLong.getAndIncrement(), "ranjeet", 35));
  customers.add(new Customer(atomicLong.getAndIncrement(), "Mukesh", 35));
 }
 
 public boolean addCustomer(Customer customer) throws Exception {
  customer.setId(atomicLong.getAndIncrement());
  return customers.add(customer);

 }

 public boolean upateCustomer(Customer customer) throws Exception {
  for (Customer c : customers) {
   if (c.getId().equals(customer.getId())) {
    customer.setId(c.getId());
    customers.remove(c);
    customers.add(customer);
    logger.debug("updated customer by customer id : ", customer.getId());
    return true;
   }
  }
  return false;
 }

 public boolean deleteCustomerById(Long id) throws Exception {
  boolean status = false;
  Iterator<Customer> it = customers.iterator();
   while(it.hasNext()) {
    Customer c = it.next();
    if (c.getId() == id) {
     it.remove();
     logger.debug("removed customer by customer id : ", id);
     status = true;
    }
   }
   return status;
 }

 public List<Customer> getCustomers() throws Exception {
  return customers;
 }

 public Customer getCustomerById(Long id) throws Exception {
  for (Customer c : customers) {
   if (c.getId() == id) {
    return c;
   }

  }
  return null;

 }
}
