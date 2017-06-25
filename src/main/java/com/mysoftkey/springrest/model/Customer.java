/**
 * 
 */
package com.mysoftkey.springrest.model;

import java.io.Serializable;

/**
 * This is domain model for Customer domain object.
 * 
 * @author Ranjeet Jha
 *
 */
public class Customer implements Serializable {

 private Long id;
 private String name;
 private int age;

 public Customer() {

 }

 public Customer(Long id, String name, int age) {
  this.id = id;
  this.name = name;
  this.age = age;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public int getAge() {
  return age;
 }

 public void setAge(int age) {
  this.age = age;
 }

}
