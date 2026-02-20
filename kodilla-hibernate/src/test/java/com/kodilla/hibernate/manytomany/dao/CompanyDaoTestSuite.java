package com.kodilla.hibernate.manytomany.dao;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@SpringBootTest
class CompanyDaoTestSuite {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void testSaveManyToMany() {
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");

        Company softwareMachine = new Company("Software Machine");
        Company dataMaesters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");

        softwareMachine.getEmployees().add(johnSmith);
        dataMaesters.getEmployees().add(stephanieClarckson);
        dataMaesters.getEmployees().add(lindaKovalsky);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaKovalsky);

        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(greyMatter);

        //When
        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMaesters);
        int dataMaestersId = dataMaesters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();

        //Then
        assertNotEquals(0, softwareMachineId);
        assertNotEquals(0, dataMaestersId);
        assertNotEquals(0, greyMatterId);

        //CleanUp
        try {
            companyDao.deleteById(softwareMachineId);
            companyDao.deleteById(dataMaestersId);
            companyDao.deleteById(greyMatterId);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    void testRetrieveCompanyByFirstThreeLetters() {
        // Given
        Company company1 = new Company("Software Machine");
        Company company2 = new Company("Soft Solutions");
        Company company3 = new Company("Hardware Factory");

        companyDao.save(company1);
        companyDao.save(company2);
        companyDao.save(company3);

        // When
        List<Company> result = companyDao.retrieveCompanyByFirstThreeLetters("Sof");
        int company1ID = company1.getId();
        int company2ID = company2.getId();
        int company3ID = company3.getId();


        // Then
        assertEquals(2, result.size());

        // CleanUp
        companyDao.deleteById(company1ID);
        companyDao.deleteById(company2ID);
        companyDao.deleteById(company3ID);
    }

    @Test
    void testRetrieveEmployeeByLastName() {
        // Given
        Employee emp1 = new Employee("John", "Smith");
        Employee emp2 = new Employee("Mike", "Smith");
        Employee emp3 = new Employee("Anna", "Brown");

        employeeDao.save(emp1);
        employeeDao.save(emp2);
        employeeDao.save(emp3);

        // When
        List<Employee> result = employeeDao.retrieveEmployeeByLastName("Smith");
        int emp1ID = emp1.getId();
        int emp2ID = emp2.getId();
        int emp3ID = emp3.getId();

        // Then
        assertEquals(2, result.size());

        // CleanUp
        employeeDao.deleteById(emp1ID);
        employeeDao.deleteById(emp2ID);
        employeeDao.deleteById(emp3ID);
    }
}
