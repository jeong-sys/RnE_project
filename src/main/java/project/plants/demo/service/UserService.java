package project.plants.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.plants.demo.entity.User;
import project.plants.demo.repo.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findOrCreateByNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);
        if (user == null) {
            user = new User();
            user.setNickname(nickname);
            userRepository.save(user);
        }
        return user;
    }
}
