package ru.sberbank.jd.studentsClass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.studentsClass.model.StudentsClass;

@Repository
public interface StudentsClassRepository extends JpaRepository<StudentsClass, Long> {

}
