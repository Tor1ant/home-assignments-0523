package ru.sberbank.jd.user.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.user.model.User;
import ru.sberbank.jd.user.role.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String userName);

    List<User> findAllByRoles(Role role);
}
