package com.spring.employee.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.stereotype.Repository;
import com.spring.employee.entity.Employee;

// @Repository
// @EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
   // public List<Employee> findByDept_Id(int dept_id);
   // public Employee FindByEmpIdAndDeptId(int emp_id,int dept_id); 
}
