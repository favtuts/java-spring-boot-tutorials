package com.favtuts.swagger.controller;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Employee Model", description = "Employee Model for employee controller request response")
public class Employee {
    Integer id;
    String name;
    String address;
    String department;
    Double age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }
}
