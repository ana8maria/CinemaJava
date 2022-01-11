package com.proiect.cinemamanagement.tests;

import com.proiect.cinemamanagement.dao.Employee;
import com.proiect.cinemamanagement.entity.EmployeeEntity;
import com.proiect.cinemamanagement.service.EmployeeService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
@ActiveProfiles("test")
public class EmployeeControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @BeforeClass
    public static void init() {
    }

    @Test
    public void testPostEmployee() throws Exception {

        Assert.assertNotNull(employeeService);
        ObjectMapper mapper = new ObjectMapper();

        Employee employee= new Employee("firstName", "lastName", 1.2f,"casier");
        EmployeeEntity convertedEmployeeEntity = new EmployeeEntity(employee);

        given(this.employeeService.processPostEmployee(employee)).willReturn(convertedEmployeeEntity);

        this.mvc.perform(post("/cm/api/v1/employees").content(mapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(mapper.writeValueAsString(convertedEmployeeEntity)));

    }


    @Test
    public void testGetEmployees() throws Exception{
        ArrayList<EmployeeEntity> list = new ArrayList<>();

        Assert.assertNotNull(employeeService);

        EmployeeEntity employee= new EmployeeEntity(1, "firstName", "lastName", 1.2f,"casier");
        EmployeeEntity employee2= new EmployeeEntity(2, "firstName2", "lastName2", 1.22f,"casier2");
        list.add(employee);
        list.add(employee2);
        ObjectMapper mapper = new ObjectMapper();
        given(this.employeeService.processGetEmployees(1,1,"id")).willReturn(list);
        this.mvc
                .perform(get("/cm/api/v1/employees").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

    @Test
    public void testGetEmployee() throws Exception {

        int id = 3;
        Assert.assertNotNull(employeeService);


        Employee employee =
                new Employee(
                        "firstName", "lastName", 1.2f,"casier");

        EmployeeEntity convertedEmployee = new EmployeeEntity(employee);

        ObjectMapper mapper = new ObjectMapper();

        given(this.employeeService.processGetEmployee(id)).willReturn(convertedEmployee);

        this.mvc
                .perform(
                        get("/cm/api/v1/employees/3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(convertedEmployee)));
    }

    @Test
    public void testPutEmployee() throws Exception {

        Assert.assertNotNull(employeeService);

        Employee employee =
                new Employee(
                        "firstName", "lastName", 1.2f,"casier");

        EmployeeEntity convertedEmployee = new EmployeeEntity(employee);

        ObjectMapper mapper = new ObjectMapper();

        given(this.employeeService.processPutEmployee(2, employee)).willReturn(convertedEmployee);
        this.mvc
                .perform(
                        put("/cm/api/v1/employees/{id}", 2)
                                .content(mapper.writeValueAsString(employee))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(convertedEmployee)));
    }

//    @Test
//    public void testDeleteEmployee() throws Exception {
//
//        Assert.assertNotNull(employeeService);
//
//        Employee employee =
//                new Employee(
//                        "firstName", "lastName", 1.2f,"casier");
//
//        EmployeeEntity convertedEmployee = new EmployeeEntity(employee);
//
//        String endpoint = "/cm/api/v1/employees/id=%s";
//        this.mvc
//                .perform(delete(String.format(endpoint, convertedEmployee.getId())).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//    }

}
