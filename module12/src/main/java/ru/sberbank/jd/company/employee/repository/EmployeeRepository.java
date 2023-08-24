package ru.sberbank.jd.company.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.company.department.model.Department;
import ru.sberbank.jd.company.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
