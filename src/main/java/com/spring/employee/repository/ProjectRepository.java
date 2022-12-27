package com.spring.employee.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// import com.spring.employee.entity.Department;
// import org.springframework.stereotype.Repository;
import com.spring.employee.entity.Project;


// @Repository
// @EnableJpaRepositories
public interface ProjectRepository extends JpaRepository<Project,Integer>{
    // List<Project> findAllByDept(Department dept);
}
