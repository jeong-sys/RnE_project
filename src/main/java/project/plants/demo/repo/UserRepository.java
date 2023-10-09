package project.plants.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.plants.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickname);
}

