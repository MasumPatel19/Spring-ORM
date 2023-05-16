package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.EmployeeDao;
import com.spring.orm.entities.Employee;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

		EmployeeDao employeeDao = context.getBean("employeeDao", EmployeeDao.class);
//		Employee employee = new Employee(104, "Employee4", "HR");
//		int insert = employeeDao.insert(employee);
//		System.out.println(insert + " record inserted.");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("\n*********************************");
			System.out.println("* 1.Insert Employee Record      *");
			System.out.println("* 2.Update Employee Record      *");
			System.out.println("* 3.Get All Employee Records    *");
			System.out.println("* 4.Get Employee Record By Id   *");
			System.out.println("* 5.Delete Employee Record      *");
			System.out.println("* 6.Exit                        *");
			System.out.println("*********************************");

			try {
				System.out.println("Enter your choice : ");
				int ch = Integer.parseInt(br.readLine());
				switch (ch) {
				case 1:
					System.out.println("\nEnter Id : ");
					int empId = Integer.parseInt(br.readLine());

					System.out.println("Enter Name : ");
					String empName = br.readLine();

					System.out.println("Enter Designation : ");
					String empDesignation = br.readLine();

					Employee e = new Employee();
					e.setId(empId);
					e.setName(empName);
					e.setDesignation(empDesignation);

					employeeDao.insert(e);
					System.out.println("Employee Record Inserted.");

					break;
				case 2:
					System.out.println("\nEnter Employee id : ");
					int eUpdateId = Integer.parseInt(br.readLine());

					Employee getEmpData = employeeDao.getEmployee(eUpdateId);
					String getEmpname = getEmpData.getName();
					String getEmpDesignation = getEmpData.getDesignation();

					System.out.println("\n What you want to update ? ");
					System.out.println("1 : Update Employee name.");
					System.out.println("2 : Update Employee designation.");
					int choice = Integer.parseInt(br.readLine());
					Employee updatedEmp = new Employee();
					if (choice == 1) {

						System.out.println("Enter Updated Name : ");
						String updatedName = br.readLine();

//						updatedEmp.setId(eUpdateId);
//						updatedEmp.setName(updatedName);
//						updatedEmp.setDesignation(empDes);
						updatedEmp = new Employee(eUpdateId, updatedName, getEmpDesignation);
						System.out.println("Employee name updated successfully.");

					} else if (choice == 2) {
						System.out.println("Enter Updated Designation : ");
						String updatedDesignation = br.readLine();
//						Employee uEmployee = new Employee();
//						String empnm = updatedEmp.getName();
//						updatedEmp.setId(eUpdateId);
//						updatedEmp.setDesignation(updatedDesignation);
//						updatedEmp.setName(empnm);

						updatedEmp = new Employee(eUpdateId, getEmpname, updatedDesignation);
						System.out.println("Employee designation updated successfully.");

					} else {
						System.out.println("Invalid...");
					}

					employeeDao.updateEmployee(updatedEmp);
					System.out.println("Employee Record successfully updated.");

					break;
				case 3:
					List<Employee> allEmploee = employeeDao.getAllEmploee();
					for (Employee emp : allEmploee) {
						System.out.println("\nEmployee Id : " + emp.getId());
						System.out.println("Employee Name : " + emp.getName());
						System.out.println("Employee Designation : " + emp.getDesignation());
					}
					break;
				case 4:
					System.out.println("\nEnter Employee Id : ");
					int eId = Integer.parseInt(br.readLine());
					Employee employee = employeeDao.getEmployee(eId);
					System.out.println("Employee Id : " + employee.getId());
					System.out.println("Employee Name : " + employee.getName());
					System.out.println("Employee Designation : " + employee.getDesignation());
					break;
				case 5:
					System.out.println("\nEnter Employee id for deleting record : ");
					int id = Integer.parseInt(br.readLine());
					employeeDao.deleteEmployee(id);
					System.out.println("Employee deleted.");
					break;
				case 6:
					return;

				default:
					System.out.println("Invalid choice.");
					break;
				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
}
