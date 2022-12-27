package com.spring.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.stereotype.Repository;
import com.spring.employee.entity.Department;

// @Repository
// @EnableJpaRepositories
public interface DepartmentRepository extends JpaRepository<Department,Integer>{
    
}
