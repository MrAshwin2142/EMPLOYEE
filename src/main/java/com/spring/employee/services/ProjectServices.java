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

@Service
public class ProjectServices {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired 
    private EmployeeRepository employeeRepository;

    public List<Project> getAllProject(){
        return (List<Project>) projectRepository.findAll();
    }

    public Project getProjectById(int id){
        Optional<Project> proj=projectRepository.findById(id);
        if(proj.isPresent()){return
                proj.get();
        }else{return null;}
    }


    // public List<Project> getProjectByDeptId(int deptId){
    //     Optional<Department> dept=departmentRepository.findById(deptId);
    //     if(dept.isPresent()){
    //         return projectRepository.findAllByDept(dept.get());
    //     }else{
    //         return null;
    //     }
    // }

    public List<Project> getProjectByEmpId(int emp_id){
        Optional<Employee> e=employeeRepository.findById(emp_id);
        List<Project>res=new ArrayList<>();
        if(e.isPresent()){
            List<Project> projectList =projectRepository.findAll();
            for(Project p:projectList){
                Set<Employee> temp=p.getEmployees();
                for(Employee x:temp){
                    if(x.getEmpId()==emp_id){res.add(p);}
                }
            }
        }
        return res;
    }

    public Project addProject(Project p){
        projectRepository.save(p);
        return p;
    }

    public Project addProjectWithDeptId(Project p, int deptid){
        Optional<Department> dept=departmentRepository.findById(deptid);
        if(dept.isPresent()) {
            p.setDept(dept.get());
        }
            projectRepository.save(p);
            return p;
    }

    public Project addProjectWithEmp(Project p,int emp_id){
        Optional<Employee> e=employeeRepository.findById(emp_id);
        if(e.isPresent()){
            p.getEmployees().add(e.get());
        }
        projectRepository.save(p);
        return p;
    }
    public boolean assignEmployeeToProject(int pid,int eid){
        Optional<Project> projects=projectRepository.findById(pid);
        Optional<Employee> empOptional=employeeRepository.findById(eid);
        if(projects.isPresent() && empOptional.isPresent()){
            Set<Employee> e=projects.get().getEmployees();
            e.add(empOptional.get());
            return true;
        }else{return false;}
    }

    public String deleteAllProjects(){
        projectRepository.deleteAll();
        return "project Deleted SuccessFully";
    }
    public boolean deleteById(int id){
        if(projectRepository.findById(id).isPresent()) {
            projectRepository.deleteById(id);
            return true;
        }else{
            return  false;
        }
    }

   public Boolean updateDepartmentId(int project_id,int Dept_id){
        Optional<Project> projectOptional=projectRepository.findById(project_id);
        Optional<Department> deptOptional=departmentRepository.findById(Dept_id);
        if(projectOptional.isPresent() && deptOptional.isPresent()){
            projectOptional.get().setDept(deptOptional.get());
            return true;
        }else{
            return false;
        }
    }




}
