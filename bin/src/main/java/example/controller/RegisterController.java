package example.controller;

import example.model.User;
import example.service.SecurityService;
import example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController
{
  @Autowired
  private SecurityService securityService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserValidator userValidator;

	@GetMapping("/register")
	public String register(Model model)
	{
    if (securityService.isAuthenticated()) {
        return "redirect:/";
    }
		model.addAttribute("userForm", new User());
		return "register";
	}

  @PostMapping("/register")
  public String register(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
      userValidator.validate(userForm, bindingResult);

      if (bindingResult.hasErrors()) {
          return "register";
      }

      userService.save(userForm);

      securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

      return "redirect:/user";
  }
}
