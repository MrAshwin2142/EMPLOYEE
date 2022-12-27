package com.spring.employee.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.employee.entity.Employee;
import com.spring.employee.services.EmployeeServices;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeServices employeeServices;

    
    EmployeeController(){    
    }
    EmployeeController(EmployeeServices employeeServices){
        this.employeeServices=employeeServices;
    }
    @PostMapping()
    public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee){
        Employee temp_emp=employeeServices.saveEmployee(employee);
        return new ResponseEntity<>(temp_emp,HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees(){
        
        return new ResponseEntity<>(employeeServices.getAllEmployees(), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")  int id){
        Employee temp_emp=employeeServices.getEmployeeById(id);
        if(temp_emp==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ResponseEntity<Employee> empResponseEntity = new ResponseEntity<Employee>(employeeServices.getEmployeeById(id), HttpStatus.OK);
        return empResponseEntity;
    }
    @PutMapping("{eid}/department/{did}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee e, @PathVariable("eid") int id, @PathVariable("did") int dept_id){
        Optional<Employee> temp=employeeServices.updateEmployee(e,id,dept_id);
        if(temp.isPresent()){
            return ResponseEntity.of(Optional.of(temp.get()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @PutMapping("{eid}/project/{pid}")
    public ResponseEntity<Employee> assignProjectsToEmployee(@PathVariable int eid, @PathVariable int pid){
        return new ResponseEntity<>(employeeServices.assignProjectsToEmployee(eid, pid), HttpStatus.OK);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        employeeServices.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
    }




}
