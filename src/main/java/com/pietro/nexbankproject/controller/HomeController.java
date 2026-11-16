package com.pietro.nexbankproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pietro.nexbankproject.model.Account;
import com.pietro.nexbankproject.service.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	private final AccountService accountService;
	
	public HomeController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("account", new Account());
		return "login";	
	}
	
	@PostMapping("/")
	public String login(Model model, @ModelAttribute Account account, HttpSession session) {
		
		Account current = accountService.authenticate(account.getCpf(), account.getPassword());
		
		if (current != null) {
			model.addAttribute("acc", current);
			model.addAttribute("reposta", true);
			session.setAttribute("acc", current);
			
			return "safe";
		}
		
		login(model);		
		model.addAttribute("reposta", false);
		
		return "login";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/help")
	public String help() {
		return "help";
	}
	
	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {
		
		Account account = (Account) session.getAttribute("acc");
		
		if (account != null) {
			model.addAttribute("acc", account);
		}
		
		return "profile";
	}
}
