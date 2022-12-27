package com.spring.employee.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@Table(name="Employees")
public class Employee {

    @Id
    @Column(name = "Employee_Id")
    private int empId;

    @Column(name="Employee_name")
    private String emp_name;
    
    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department department;

    @Column
    @ManyToMany
    @JoinTable(name="Employee_Project",joinColumns = @JoinColumn(name="Employee_Id"),inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> assignedProject = new HashSet<>();
   
    public Employee() {
    }

    public Employee(int empId, String emp_name, Department department, Set<Project> assignedProjects) {
        this.empId = empId;
        this.emp_name = emp_name;
        this.department = department;
        this.assignedProject = assignedProjects;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Project> getAssignedProjects() {
        return assignedProject;
    }

    public void setAssignedProjects(Set<Project> assignedProjects) {
        this.assignedProject = assignedProjects;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", emp_name=" + emp_name + ", department=" + department
                + ", assignedProjects=" + assignedProject + "]";
    }

}
