package example.repository;

import example.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository {
    UserDetails findUserByName(String name);
    void save(User user);
}
