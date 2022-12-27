package com.spring.employee.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Department")
public class Department {
    @Id
    @Column(name = "Dept_Id")
    private int dept_id;

    @Column(name="Dept_name")
    private String dept_name;

    @Column(name="Dept_location")
    private String dept_loc;
    
    public Department() {
    }
    public Department(int dept_id, String dept_name, String dept_loc) {
        this.dept_id = dept_id;
        this.dept_name = dept_name;
        this.dept_loc = dept_loc;
    }
    public int getDept_id() {
        return dept_id;
    }
    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }
    public String getDept_name() {
        return dept_name;
    }
    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
    public String getDept_loc() {
        return dept_loc;
    }
    public void setDept_loc(String dept_loc) {
        this.dept_loc = dept_loc;
    }
    @Override
    public String toString() {
        return "Department [dept_id=" + dept_id + ", dept_name=" + dept_name + ", dept_loc=" + dept_loc + "]";
    }

    
}
