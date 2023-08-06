package ru.sberbank.jd.user.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.studentsClass.repository.StudentsClassRepository;
import ru.sberbank.jd.user.model.User;
import ru.sberbank.jd.user.repository.UserRepository;
import ru.sberbank.jd.user.role.Role;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StudentsClassRepository studentsClassRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getStudents() {
        return userRepository.findAllByRoles(Role.ROLE_STUDENT).stream()
                .filter(user -> !user.getRoles().contains(Role.ROLE_EMPLOYEE)).collect(
                        Collectors.toList());
    }

    public void addStudent(User user, Long classId) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.ROLE_STUDENT));
        user.setStudentsClass(studentsClassRepository.findById(classId).orElseThrow());
        userRepository.save(user);
    }
}
