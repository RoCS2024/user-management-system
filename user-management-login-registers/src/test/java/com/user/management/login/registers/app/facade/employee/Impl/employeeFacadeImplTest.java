package com.user.management.login.registers.app.facade.employee.Impl;

import com.user.management.login.registers.app.model.employee.Employee;
import com.user.management.login.registers.data.employee.dao.employeeDao;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class employeeFacadeImplTest {

    @Test
    void testSaveEmployee() throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeNo("EMP21-0143");
        employee.setLastName("Magnaye");
        employee.setFirstName("Justine");
        employeeDao employeeDao = mock(employeeDao.class);
        when(employeeDao.saveEmployee(employee)).thenReturn(employee);

        employeeFacadeImpl employeeFacade = new employeeFacadeImpl(employeeDao);
        Employee result = employeeFacade.saveEmployee(employee);

        assertNotNull(result, "Saved employee should not be null.");
        assertEquals(employee, result, "Saved employee should match input employee.");
        verify(employeeDao, times(1)).saveEmployee(employee);
    }

    @Test
    void testCheckEmployeeId() throws SQLException {
        String employeeId = "EMP21-0143";
        Employee expectedEmployee = new Employee();
        expectedEmployee.setEmployeeNo(employeeId);
        expectedEmployee.setLastName("Magnaye");
        expectedEmployee.setFirstName("Justine");
        employeeDao employeeDao = mock(employeeDao.class);
        when(employeeDao.checkEmployeeId(employeeId)).thenReturn(expectedEmployee);

        employeeFacadeImpl employeeFacade = new employeeFacadeImpl(employeeDao);
        Employee result = employeeFacade.checkEmployeeId(employeeId);

        assertNotNull(result, "Returned employee should not be null.");
        assertEquals(expectedEmployee, result, "Returned employee should match expected employee.");
        verify(employeeDao, times(1)).checkEmployeeId(employeeId);
    }
}