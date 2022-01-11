package com.proiect.cinemamanagement.serviceTests;

import com.proiect.cinemamanagement.dao.Employee;
import com.proiect.cinemamanagement.entity.EmployeeEntity;
import com.proiect.cinemamanagement.repository.EmployeeRepository;
import com.proiect.cinemamanagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;

    private static Employee employee;

    private static List<Employee> employees;


    @BeforeAll
    public static void setup(){
        employee=new Employee("Mihai", "Ioana-Alexandra", 200f, "casier");
        employees = new ArrayList<Employee>();
        employees.add(employee);
       Employee employee2=new Employee("Popescu", "Ioana", 20f, "casier");
        employees.add(employee2);
    }

    @Test
    @DisplayName("Test save employee")
    public void testSaveEmployee(){
        EmployeeEntity employeeEntity=new EmployeeEntity(employee);
        doReturn(employeeEntity).when(employeeRepository).save(employeeEntity);
        employeeService.processPostEmployee(employee);
    }

    @Test
    @DisplayName("Test get employees")
    public void testGetAllEmployees(){
        doReturn(employees).when(employeeRepository).findAll();
        employeeService.processGetEmployees(1,1,"id");
    }

    @Test
    @DisplayName("Test get employee")
    public void testGetEmployee(){
        EmployeeEntity employeeEntity=new EmployeeEntity(employee);
        doReturn(Optional.empty()).when(employeeRepository).findById(employeeEntity.getId());
        employeeService.processGetEmployee(employeeEntity.getId());
    }

    @Test
    @DisplayName("Test put employee")
    public void testPutEmployee(){
       EmployeeEntity employeeEntity=new EmployeeEntity(employee);
//        doReturn(employeeEntity).when(employeeRepository).save(employeeEntity);
        employeeService.processPutEmployee(employeeEntity.getId(),employee);
    }

    @Test
    @DisplayName("Test delete employee")
    public void testDeleteEmployee(){
        EmployeeEntity employeeEntity=new EmployeeEntity(employee);
        //doReturn(Optional.empty()).when(employeeRepository).deleteById(employeeEntity.getId());
        employeeService.processDeleteEmployee(employeeEntity.getId());
    }

}
