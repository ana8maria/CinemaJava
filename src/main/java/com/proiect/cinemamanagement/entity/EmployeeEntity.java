package com.proiect.cinemamanagement.entity;

import com.proiect.cinemamanagement.dao.Employee;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="employee")

public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private int id;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="salary")
    private Float salary;

    @Column(name ="position")
    private String position;

    public EmployeeEntity(){}

    public EmployeeEntity(Employee employee){
        this.firstName=employee.getFirstName();
        this.lastName=employee.getLastName();
        this.salary=employee.getSalary();
        this.position=employee.getPosition();
    }

    public EmployeeEntity(int id, String firstName, String lastName, Float salary, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeEntity)) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return id == that.id &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                salary.equals(that.salary) &&
                position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary, position);
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                '}';
    }
}
