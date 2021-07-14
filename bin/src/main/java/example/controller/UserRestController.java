package example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController
{
	@Autowired
	public InMemoryUserDetailsManager inMemoryUserDetailsManager;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@GetMapping("/person/{username}")
	public String checkIfUserExists(@PathVariable("username") String username)
	{
		boolean flag = inMemoryUserDetailsManager.userExists(username);
		if (flag)
			return "\""+username + "\" exist in InMemoryUserDetailsManager";
		else
			return "redirect:/";
	}

	@GetMapping("/person/create/{username}/{password}/{role}")
	public String createUser(@PathVariable("username") String username, @PathVariable("password") String password,
			@PathVariable("role") String role)
	{

		ArrayList<GrantedAuthority> grantedAuthoritiesList= new ArrayList<>();
		grantedAuthoritiesList.add(new SimpleGrantedAuthority(role));

		inMemoryUserDetailsManager.createUser(new User(username, passwordEncoder.encode(password), grantedAuthoritiesList));

		return checkIfUserExists(username);
	}

}
