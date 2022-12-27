package com.spring.employee.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employee.entity.Department;
import com.spring.employee.services.DepartmentServices;
// import com.spring.employee.services.EmployeeServices;

@RestController
// @RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    private DepartmentServices departmentServices;

    @GetMapping("/Dept")
    public List<Department> getDepartment(){
        return departmentServices.getdepartmentDetails();
    }

    @GetMapping("Dept/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") int id){
        return ResponseEntity.of(Optional.of(departmentServices.getdepartmentDetailsbyId(id)));
    }

    @PostMapping("/Dept")
    public ResponseEntity<Department> addDepartment(@RequestBody Department d){
        return ResponseEntity.of(Optional.of(departmentServices.addDepartment(d)));
    }

    @PutMapping("/Dept/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department d, @PathVariable("id") int id){
          Department temp=departmentServices.updateDepartment(d,id);
          if(temp==null){
           return   ResponseEntity.status(HttpStatus.NOT_FOUND).build();
          }else{
             return ResponseEntity.of(Optional.of(temp));
          }
    }

    @DeleteMapping("/Dept")
    public ResponseEntity<String> deleteAllDepartment(){
        departmentServices.deleteAllDepartment();
        return new ResponseEntity<>("All Department deleted successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/Dept/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") int id){
        departmentServices.deleteDepartmentbyId(id);
        return new ResponseEntity<>("Department deleted successfully!", HttpStatus.OK);
    }

    @PatchMapping("/Dept/{id}")
    public ResponseEntity<Department> upsert(@RequestBody Department dept,@PathVariable int id){
        return ResponseEntity.of(Optional.of(departmentServices.upsert(dept,id)));
    } 
}
