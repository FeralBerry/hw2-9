package org.example;

public class Employee {
    private final String lastName;
    private final String firstName;
    private final int departmentId;
    private final float salary;
    public Employee(String lastName, String firstName, int departmentId, float salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        salary = 0;
        departmentId = 0;
    }

    public String toKey() {
        return "firstName: " + firstName + " lastName: " + lastName + " ";
    }
    @Override
    public String toString() {
        return "departmentId: " + departmentId + " salary: " + salary;
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.firstName+this.lastName);
    }
    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Employee c2 = (Employee) other;
        return this.firstName.equals(c2.firstName) && this.lastName.equals(c2.lastName);
    }
}
