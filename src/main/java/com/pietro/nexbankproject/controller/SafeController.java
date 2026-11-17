package com.pietro.nexbankproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pietro.nexbankproject.model.Account;
import com.pietro.nexbankproject.model.Transaction;
import com.pietro.nexbankproject.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SafeController {
	
	private final TransactionService transactionService;
	
	public SafeController(TransactionService transactionService) {
		// TODO Auto-generated constructor stub
		this.transactionService = transactionService;
	}
	
	@GetMapping("/safe")
	public String transfer(Model model, HttpSession session) {

	    Account acc = (Account) session.getAttribute("acc");

	    List<Transaction> transactions = transactionService.findSendings(acc.getId());
	    List<Transaction> receivings = transactionService.findReceivings(acc.getId());

	    model.addAttribute("transactions", transactions);
	    model.addAttribute("receivings", receivings);

	    model.addAttribute("acc", acc);

	    return "safe";
	}
	
	@PostMapping("/safe")
	public String transferr(Model model, HttpSession session) {
		return transfer(model, session);
	}
	
	
}
