package ru.sberbank.jd.company.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.company.department.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
