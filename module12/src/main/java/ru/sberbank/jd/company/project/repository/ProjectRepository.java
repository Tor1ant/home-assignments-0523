package ru.sberbank.jd.company.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.company.project.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
