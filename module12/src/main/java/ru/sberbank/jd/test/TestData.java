package ru.sberbank.jd.test;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.jd.company.department.model.Department;
import ru.sberbank.jd.company.department.repository.DepartmentRepository;
import ru.sberbank.jd.company.employee.model.Employee;
import ru.sberbank.jd.company.employee.repository.EmployeeRepository;
import ru.sberbank.jd.company.project.model.Project;
import ru.sberbank.jd.company.project.repository.ProjectRepository;

@Configuration
@AllArgsConstructor
@Slf4j
public class TestData {

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    @PostConstruct
    @Transactional
    public void initData() {
        Project project = new Project(null, "Новый проект");
        Department department = new Department(null, "Департамент java");
        Project newProject = projectRepository.save(project);
        Department newDepartment = departmentRepository.save(department);
        Employee employee = new Employee(null, "Фёдор", "Достоеквский",
                LocalDateTime.of(1821, 11, 11, 1, 1),
                "developer", project, newDepartment, false);
        Employee chief = new Employee(null, "Александр", "Романов",
                LocalDateTime.of(1777, 12, 23, 1, 1), "chief",
                project, newDepartment, true);
        Employee newChief = employeeRepository.save(chief);
        Employee newEmployee = employeeRepository.save(employee);
        List<Object> all = List.of(newProject, newDepartment, newChief, newEmployee);
        System.out.println(all);
    }
}
