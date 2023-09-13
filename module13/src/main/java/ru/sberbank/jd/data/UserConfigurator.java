package ru.sberbank.jd.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sberbank.jd.studentsClass.model.StudentsClass;
import ru.sberbank.jd.studentsClass.repository.StudentsClassRepository;
import ru.sberbank.jd.user.model.User;
import ru.sberbank.jd.user.repository.UserRepository;
import ru.sberbank.jd.user.role.Role;

@Configuration
@AllArgsConstructor
@Slf4j
public class UserConfigurator {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final StudentsClassRepository studentsClassRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void initData() {
        StudentsClass studentsClass = new StudentsClass();
        studentsClass.setClassLetter('A');
        studentsClass.setClassLvl(7L);
        studentsClass = studentsClassRepository.saveAndFlush(studentsClass);
        User employee = new User();
        employee.setBirthDate(LocalDate.now());
        employee.setUsername("employee");
        employee.setPassword(passwordEncoder.encode("password"));
        employee.setFirstName("Вячеслав");
        employee.setLastName("Ржеутский");
        employee.setRoles(Set.of(Role.ROLE_EMPLOYEE, Role.ROLE_STUDENT));
        User student = new User();
        student.setBirthDate(LocalDate.now());
        student.setUsername("student");
        student.setPassword(passwordEncoder.encode("password"));
        student.setFirstName("Студент");
        student.setLastName("Студентович");
        student.setStudentsClass(studentsClass);
        student.setRoles(Set.of(Role.ROLE_STUDENT));
        userRepository.saveAll(List.of(employee, student));
    }
}
