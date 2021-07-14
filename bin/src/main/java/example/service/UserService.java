package example.service;

import example.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
  void save(User user);
  UserDetails findByUsername(String username);
}
