package com.proiect.cinemamanagement.dao;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Employee {

    @NotNull private String firstName;
    @NotNull private String lastName;
    @NotNull private Float salary;
    @NotNull private String position;

    public Employee(@NotNull String firstName, @NotNull String lastName, @NotNull Float salary, @NotNull String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.position = position;
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
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                salary.equals(employee.salary) &&
                position.equals(employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, salary, position);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                '}';
    }
}
