package com.proiect.cinemamanagement.service;

import com.proiect.cinemamanagement.dao.Employee;
import com.proiect.cinemamanagement.entity.EmployeeEntity;
import com.proiect.cinemamanagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //add
    public EmployeeEntity processPostEmployee(Employee employee){
        EmployeeEntity employeeEntity=new EmployeeEntity(employee);

        log.info("employee for add", employee);
        if(employee.getSalary()<0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,  "Request body parameters salary should be positive number.");
        }
        return employeeRepository.save(employeeEntity);
    }

    //get employees withou pagination
    public Iterable<EmployeeEntity> processGetEmployeesWithoutPagination() {
        return employeeRepository.findAll();
    }

    //get all
    public Iterable<EmployeeEntity> processGetEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<EmployeeEntity> pageResult = employeeRepository.findAll(paging);
        return pageResult.getContent();
    }
    //get by name
    public Iterable<EmployeeEntity> processFindByFirstNameContaining(String firstName) {
        return employeeRepository.findByFirstNameContaining(firstName);
    }

    //get by id
    public EmployeeEntity processGetEmployee(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    //delete
    public void processDeleteEmployee(Integer id){

        employeeRepository.deleteById(id);
    }

    //put

    public EmployeeEntity processPutEmployee(Integer id, Employee employee){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if(employeeEntity == null){
            return null;
        }
        employeeEntity.setSalary(employee.getSalary());
        employeeEntity.setPosition(employee.getPosition());

        return employeeRepository.save(employeeEntity);
    }
}

