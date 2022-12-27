package com.spring.employee.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "Projects")
public class Project {
    @Id
    @Column
    private int prj_id;

    @Column
    private String prj_name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Dept_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Department dept;

    @JsonIgnore
    @Column(name="employeeAssigned")
    @ManyToMany(mappedBy = "assignedProject")
    private Set<Employee> employees = new HashSet<>();
    public Project() {
    }
    public Project(int prj_id, String prj_name, Department dept, Set<Employee> employees) {
        this.prj_id = prj_id;
        this.prj_name = prj_name;
        this.dept = dept;
        this.employees = employees;
    }
    public int getPrj_id() {
        return prj_id;
    }
    public void setPrj_id(int prj_id) {
        this.prj_id = prj_id;
    }
    public String getPrj_name() {
        return prj_name;
    }
    public void setPrj_name(String prj_name) {
        this.prj_name = prj_name;
    }
    public Department getDept() {
        return dept;
    }
    public void setDept(Department dept) {
        this.dept = dept;
    }
    public Set<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    @Override
    public String toString() {
        return "Project [prj_id=" + prj_id + ", prj_name=" + prj_name + ", dept=" + dept + ", employees=" + employees
                + "]";
    }

    
}
