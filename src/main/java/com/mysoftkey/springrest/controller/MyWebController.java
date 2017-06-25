package com.mysoftkey.springrest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysoftkey.springrest.service.DateUtil;

@Controller
@RequestMapping("myweb")
public class MyWebController {

 private final Logger logger = LoggerFactory.getLogger(MyWebController.class);
 
 @RequestMapping(value = "test1", method = RequestMethod.GET)
 public ResponseEntity<Map<String, Object>> test1Handler() throws Exception {
   HttpStatus status = HttpStatus.OK;
   Map<String, Object> response = new HashMap<String, Object>();
   System.out.println("in controller started.....");
   try {
     logger.trace("started job of taking backup at " + DateUtil.parseDate(new Date(),DateUtil.DATE_FORMAT_DD_MMM_YY_HH_MM_SS_));
     response.put("ok", 1);
     response.put("status", "success");
     
   } catch (Exception e) {
     response.put("ok", 0);
     response.put("status", "failed");
     status = HttpStatus.BAD_REQUEST;
   }
   return new ResponseEntity<Map<String, Object>>(response, status);

 }
 
 /*@RequestMapping(value = "test2", method = RequestMethod.GET)
 public ResponseEntity<Map<String, Object>> myhandler() throws Exception {
   HttpStatus status = HttpStatus.OK;
   Map<String, Object> response = new HashMap<String, Object>();
   System.out.println("in controller started.....");
   try {
     logger.trace("started job of taking backup at " + DateUtil.parseDate(new Date(),DateUtil.DATE_FORMAT_DD_MMM_YY_HH_MM_SS_));
     response.put("ok", 1);
     response.put("status", "success");
     response.put("isRunnig", ArchivalServiceImpl.IS_RUNNING);
     
   } catch (Exception e) {
     response.put("ok", 0);
     response.put("status", "failed");
     status = HttpStatus.BAD_REQUEST;
   }
   return new ResponseEntity<Map<String, Object>>(response, status);

 }*/

}
