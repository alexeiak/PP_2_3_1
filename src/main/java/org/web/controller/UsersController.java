package org.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.web.model.User;
import org.web.service.UserService;


@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserService userService;


	@PostMapping("")
	public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors() || userService.existsByEmail(user.getEmail())) {
			return "new";
		}
		userService.create(user);
		return "redirect:/users";
	}

	@GetMapping("/new")
	public String htmlCreateUserForm(@ModelAttribute("user") User user) {
		return "new";
	}


	@GetMapping()
	public String getAll(Model model) {
		model.addAttribute("users", userService.getAll());
		return "users";
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userService.getById(id));
		return "/user";
	}


	@PatchMapping("/{id}")
	public String edit(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
	                   @PathVariable("id") Long id) {
		if (bindingResult.hasErrors()) {
			//TODO: handle on a reserved email, but not current email
			return "edit";
		}
		userService.update(user, id);
		return "redirect:/users";
	}

	@GetMapping("/{id}/edit")
	public String htmlEditUserForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userService.getById(id));
		return "/edit";
	}


	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		userService.delete(id);
		return "redirect:/users";
	}
}
