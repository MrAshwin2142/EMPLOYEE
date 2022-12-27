package com.spring.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.employee.entity.Department;
import com.spring.employee.repository.DepartmentRepository;

@Service
public class DepartmentServices {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentServices(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public List<Department> getdepartmentDetails(){
        return (List<Department>) departmentRepository.findAll();
    }

    public Department getdepartmentDetailsbyId(int id){
        Optional<Department>temp=departmentRepository.findById(id);
        if(temp.isPresent()){
            return temp.get();
        }else{
            return null;
        }
    }


    public Department addDepartment(Department dept){
        departmentRepository.save(dept);
        return dept;
    }


    public Department updateDepartment(Department dept,int id){
        Optional<Department> dept1=departmentRepository.findById(id);

        if(dept1.isPresent()) {
            dept.setDept_id(id);;
            departmentRepository.save(dept);
            return dept;
        }
       return dept1.get();
    }

    public void deleteAllDepartment(){
        departmentRepository.deleteAll();
    }
    public void deleteDepartmentbyId(int id){
        departmentRepository.deleteById(id);
    }
    public Department  upsert(Department dept,int id){
        dept.setDept_id(id);;
        departmentRepository.save(dept);
        return dept;
    }

    
}
