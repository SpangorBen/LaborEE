package com.labor.laboree.models;


public class Employee {
    private Integer id;

    private String name;
    private String email;
    private String phone;
    private String position;
    private Department department;

    public Employee() {
    }

    public Employee (String name, String email, String phone, String position, Department department) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.department = department;
    }

    public Employee(Integer id, String name, String email, String phone, String position, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.department = department;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", department=" + department +
                '}';
    }
}
