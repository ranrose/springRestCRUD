/**
 * 
 */
package com.mysoftkey.springrest.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mysoftkey.springrest.model.Customer;
import com.mysoftkey.springrest.service.CustomerService;

/**
 * This is controller class for Restful webservice in Spring to 
 * handle all Cutomer related method.
 * 
 * @author ranjeet jha
 *
 */
//@RestController
@Controller
@RequestMapping(value="/customer")
public class CustomerController {

 private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

 @Autowired
 private CustomerService customerService;
 
 @RequestMapping(value = "/hello", method = RequestMethod.GET)
 public ResponseEntity<String> getHello() {
  return new ResponseEntity<String>("Hello", HttpStatus.OK);
 }
 
 @RequestMapping(value="loginPage")
 public String showLogin(){
  /*ModelAndView mv = new ModelAndView();
  mv.setViewName("login");
  return mv;*/
  return "login";
  
 }
 /**
  * This method is used to get list of Customer object.
  * url: http://localhost:8080/springRestCRUD/customer/
  * 
  * @return
  */
 @RequestMapping(value = "/", method = RequestMethod.GET)
 public ResponseEntity<List<Customer>> getCustomerListHandler() {
  List<Customer> customerList = null;
  HttpStatus status = HttpStatus.OK;
  try {
   customerList = customerService.getCustomers();
   if (customerList == null) {
    
   }
  } catch (Exception e) {
   status = HttpStatus.BAD_REQUEST;
  }

  return new ResponseEntity<List<Customer>>(customerList, status);
 }

 /**
  * This method is used to get one Customer object by id.
  * 
  * @param id
  * @return
  */
 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
 public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
  System.out.println("called at : " +new Date());
  Customer customer = null;
  MultiValueMap<String, String> resHeader = new LinkedMultiValueMap<String, String>();
  try {
   customer = customerService.getCustomerById(id);
   
   Calendar cal = Calendar.getInstance();
   cal.add(Calendar.MINUTE, 1);
   resHeader.add("Expires", cal.getTime().toString());
   resHeader.add("Date", new Date().toString());
   if (customer == null) {
    customer = new Customer();
   // return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
   }
  } catch (Exception e) {
  }
  //return new ResponseEntity<Customer>(customer, HttpStatus.OK);
  return new ResponseEntity<Customer>(customer, resHeader, HttpStatus.OK);
 }

 /**
  * This method is used to add Customer object.
  * 
  * @param customer
  * @return
  */
 @RequestMapping(value = "/add", method = RequestMethod.POST)
 public ResponseEntity<Map<String, Object>> addCustomerHandler(@RequestBody Customer customer) {
 
  Map<String, Object> response = new HashMap<String, Object>();
  HttpStatus status = HttpStatus.OK;
  try {
   customerService.addCustomer(customer);
   response.put("customer", customer);
   response.put("ok", 1);
   response.put("messge", "successfully added customer object");
  } catch (Exception e) {
   logger.error("Exception caught while adding customer , msg: ", e.getMessage());
   response.put("ok", 0);
   response.put("messge", "something went wrong");
   response.put("error", e.getMessage());
   status = HttpStatus.BAD_REQUEST;
  }

  return new ResponseEntity<Map<String, Object>>(response, status);
 }

 /**
  * This method is used to delete Customer object by id.
  * 
  * @param id
  * @return
  */
 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
 public ResponseEntity<Map<String, Object>> deleteCustomerHandler(@PathVariable("id") Long id) {
  boolean status = false;
  HttpStatus httpStatus = HttpStatus.OK;
  Map<String, Object> response = new HashMap<String, Object>();
  try {
   status = customerService.deleteCustomerById(id);
   if (!status) {
    return new ResponseEntity("No Customer found to delete for ID " + id, HttpStatus.NOT_FOUND);
   }
   response.put("ok", 1);
   response.put("messge", "successfully deleted");
   logger.debug("succefully deleted , id: ", id);
  } catch (Exception e) {
   response.put("ok", 0);
   response.put("messge", "something went wrong");
   response.put("error", e.getMessage());
   httpStatus = HttpStatus.BAD_REQUEST;
  }
  return new ResponseEntity<Map<String, Object>>(response, httpStatus);
 }

 /**
  * This method is used to update Customer object for provided payload json.
  * 
  * @param id
  * @param customer
  * @return
  */
 @RequestMapping(value = "{id}", method = RequestMethod.PUT)
 public ResponseEntity<Map<String, Object>> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
  boolean isUpdated = false;
  HttpStatus status = HttpStatus.OK;
  Map<String, Object> response = new HashMap<String, Object>();
  try {
   isUpdated = customerService.upateCustomer(customer);
   
   if (!isUpdated) {
    response.put("ok", 1);
    response.put("messge", "successfully deleted");
    logger.debug("succefully deleted , id: ", id);
   } else {
    response.put("ok", 1);
    response.put("messge", "No customer found for id: "+ id);
    logger.debug("No customer found for id: ", id);
   }
  } catch (Exception e) {
   response.put("ok", 0);
   response.put("messge", "Exception occured while update, msg:" + e.getMessage());
   logger.debug("No customer found for id: ", id);
  }

  return new ResponseEntity<Map<String, Object>>(response, status);
 }

}
