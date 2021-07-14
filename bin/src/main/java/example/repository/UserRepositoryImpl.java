package example.repository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.ArrayList;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
  	public InMemoryUserDetailsManager inMemoryUserDetailsManager;

  	@Autowired
  	public PasswordEncoder passwordEncoder;

    @Override
    public UserDetails findUserByName(String name){
      try {
        return inMemoryUserDetailsManager.loadUserByUsername(name);
      } catch(Exception e) {}
        return null;
    }

    @Override
    public void save(example.model.User user){
      String name = user.getUsername();
      String password = user.getPassword();
//      Set<Role> roles = user.getRoles();
String[] roles = {"ROLE_USER"};
      ArrayList<GrantedAuthority> grantedAuthoritiesList= new ArrayList<>();
      for (String role : roles) {
        grantedAuthoritiesList.add(new SimpleGrantedAuthority(role));
      }

      inMemoryUserDetailsManager
      .createUser(new org.springframework.security.core.userdetails.User(name, passwordEncoder.encode(password), grantedAuthoritiesList));

    }
}
