package example.service;

import example.model.User;
import example.model.Role;
import example.repository.UserRepository;
import example.repository.RoleRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(User user) {
        user.setPassword(user.getPassword());
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        System.out.println("[LOG] user saved");
    }

    @Override
    public UserDetails findByUsername(String username) {
        return userRepository.findUserByName(username);
    }
}
