package com.proiect.cinemamanagement.rest;

import com.proiect.cinemamanagement.dao.Employee;
import com.proiect.cinemamanagement.entity.EmployeeEntity;
import com.proiect.cinemamanagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/cm/api/v1/employees")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    public EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<EmployeeEntity> createEmployee (@Valid @RequestBody Employee employee)
    {
        //log.info shows message and the object player with its filds
        log.info("Received request to create employees: {}", employee);
        EmployeeEntity savedEmployeeEntity = employeeService.processPostEmployee(employee);

        return new ResponseEntity<>(savedEmployeeEntity, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<Iterable<EmployeeEntity>> getEmployees(
            @RequestParam(required = false) String name, @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {

        Iterable<EmployeeEntity> returnedEmployeeEntity=null;
        if (name == null) {
            log.info("Received request to get employee");
            returnedEmployeeEntity = employeeService.processGetEmployees(pageNo, pageSize, sortBy);
        } else {
            log.info("Received request to get employee by name: " + name);
            returnedEmployeeEntity = employeeService.processFindByFirstNameContaining(name);
        }
        if (returnedEmployeeEntity == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee not found in the database");
        }

        return new ResponseEntity<>(returnedEmployeeEntity, HttpStatus.OK);
    }


    //get employee by id
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployee(@PathVariable Integer id) {
        log.info("Received request to get employee with id: {}", id);
        EmployeeEntity returnedEmployeeEntity = employeeService.processGetEmployee(id);
        log.info("Created returnedEmployeeEntity with id: {}", id);
        if (returnedEmployeeEntity == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee with  id: " + id + " not found in the database");
        }
        log.debug("before return");
        return new ResponseEntity<>(returnedEmployeeEntity, HttpStatus.OK);
    }

    //delete employee
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        log.info("Received request to delete employee with id: {}", id);

      employeeService.processDeleteEmployee(id);

    }

    //edit employee
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> editEmployee(@PathVariable Integer id, @RequestBody Employee employee){
        EmployeeEntity employeeEntity=employeeService.processPutEmployee(id,employee);
        if(employeeEntity == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee with id: " + id + " not found in the database");

        }
        if(employeeEntity.getId() == 1){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee with id: " + id + " can not be deleted");

        }
        return new ResponseEntity<>(employeeEntity, HttpStatus.OK);
    }

}
