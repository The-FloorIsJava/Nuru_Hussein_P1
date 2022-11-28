package Service;

import Com.Revature.ExpenseReimbursmentSoftware.DAO.EmployeeDAO;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Employee;
import Com.Revature.ExpenseReimbursmentSoftware.Model.Role;
import Com.Revature.ExpenseReimbursmentSoftware.Service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeServiceTestSuite {

    EmployeeService sut;
    EmployeeDAO employeeDAO;

    @Before
    public void testPrep() {
        employeeDAO = new EmployeeDAO();
        sut = new EmployeeService(employeeDAO);
    }
    @Test
    public  void test_create_employee_returnNewEmployee() {
        //AAA

        //Arrange
        Employee employee = new Employee("TakeItHome", Role.Employee, "takeithome");
        //Act
       // when(mockEmployeeDAO.create(employee)).thenReturn(employee);
        Employee employee1 = sut.createEmployee(employee);

        //Assert
       Assert.assertNotNull(employee1);
    }
}
