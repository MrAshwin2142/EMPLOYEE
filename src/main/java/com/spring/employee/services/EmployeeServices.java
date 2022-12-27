package com.spring.employee.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.employee.entity.Department;
import com.spring.employee.entity.Employee;
import com.spring.employee.entity.Project;
import com.spring.employee.repository.DepartmentRepository;
import com.spring.employee.repository.EmployeeRepository;
import com.spring.employee.repository.ProjectRepository;

// import jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
@Service
public class EmployeeServices {
    @Autowired
    private  EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ProjectRepository projectRepository;


    public Employee  saveEmployee(Employee e){
        employeeRepository.save(e);
        return e;
    }

    public List<Employee> getAllEmployees() {
//        System.out.println(empRepo.findAll());
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id){
        Optional<Employee> e=employeeRepository.findById(id);
        if(e.isPresent()){
            return e.get();
        }else{
            return null;
        }
    }

    public Department  getDeptDetailsOfEmp(int id){
        Optional<Employee>e=employeeRepository.findById(id);
        if(e.isPresent())
        return e.get().getDepartment();
        else return null;
    }
    public List<Employee> getEmployeeByProjectId(int project_id){
        Optional<Project> projectOptional=projectRepository.findById(project_id);
        List<Employee> res=new ArrayList<>();
        if(projectOptional.isPresent()){
            List<Employee> empList =employeeRepository.findAll();
            for(Employee e:empList){
                Set<Project> temp=e.getAssignedProjects();
                for(Project x:temp){
                    if(x.getPrj_id()==project_id){res.add(e);}
                }
            }
        }
        return res;
    }

    public Optional<Employee> updateEmployee(Employee e,int emp_id, int dept_id){
        Optional<Employee> temprary= employeeRepository.findById(emp_id);
        Optional<Department> dept=departmentRepository.findById(dept_id);
        if(temprary.isPresent() ){
            e.setEmpId(emp_id);
            if(dept.isPresent()){
            e.setDepartment(null);;
            }
            employeeRepository.save(e);
            return employeeRepository.findById(emp_id);
        }

        return null;
    }
    
    public Employee assignProjectsToEmployee(int emp_id,int project_id){
        Set<Project> projects = null;
        Optional<Employee>e = employeeRepository.findById(emp_id);
        Optional<Project> p = projectRepository.findById(project_id);
        projects = e.get().getAssignedProjects();
        projects.add(p.get());
        e.get().setAssignedProjects(projects);
        return employeeRepository.save(e.get());
    }
    public void deleteEmployee(int id){
        employeeRepository.deleteById(id);
    }

}
