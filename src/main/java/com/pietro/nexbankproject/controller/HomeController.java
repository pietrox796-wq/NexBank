package com.pietro.nexbankproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pietro.nexbankproject.model.Account;
import com.pietro.nexbankproject.repo.TransactionRepository;
import com.pietro.nexbankproject.service.AccountService;
import com.pietro.nexbankproject.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	private final TransactionRepository transactionRepository;
	private final AccountService accountService;
	private final TransactionService transactionService;
	
	public HomeController(AccountService accountService, TransactionService transactionService, TransactionRepository transactionRepository) {
		this.accountService = accountService;
		this.transactionService = transactionService;
		this.transactionRepository = transactionRepository;
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

		    model.addAttribute("transactions", transactionService.findSendings(current.getId()));
		    model.addAttribute("receivings", transactionService.findReceivings(current.getId()));
			
		    model.addAttribute("account", new Account());	
		    
			return "safe";
		}
		
		session.setAttribute("acc", null);
		model.addAttribute("account", new Account());	
		model.addAttribute("resposta", false);
		
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
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("acc", new Account());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(Model model, @ModelAttribute Account acc) {
	    String answer;
	    try {
	        boolean canContinue = accountService.save(acc);

	        if (canContinue) {
	            answer = "Usuário cadastrado com sucesso!";
	        } else {
	        	System.out.println("A");
	            answer = "Erro ao cadastrar usuário.";
	        }

	    } catch (Exception e) {
	    	System.out.println(e);
	        answer = "Erro ao cadastrar usuário.";
	    }

	    model.addAttribute("answer", answer);
	    model.addAttribute("acc", new Account());

	    return "register";
	}
}
