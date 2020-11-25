package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author guycui
 */
@Controller
public class TestValidator {
	@GetMapping("/test")
	public String showForm(User user) {
		return "form";
	}

	@GetMapping("/results")
	public String results() {
		return "results";
	}

	@PostMapping("/test")
	public String checkUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attr){
		if (bindingResult.hasErrors()) {
			return "form";
		}
		attr.addFlashAttribute("user",user);
		return "redirect:/results";
	}
}
