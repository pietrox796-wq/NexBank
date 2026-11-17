package com.pietro.nexbankproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pietro.nexbankproject.model.Account;
import com.pietro.nexbankproject.service.AccountService;
import com.pietro.nexbankproject.service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BankOperationController {
	
	private final AccountService accountService;
	private final TransactionService transactionService;
	
	public BankOperationController(AccountService accountService, TransactionService transactionService) {
		this.accountService = accountService;
		this.transactionService = transactionService;
	}
	
	@GetMapping("/bankoperation")
	public String transfer(Model model) {
		model.addAttribute("modelReceiving", new Account());
		return "bankoperation";
	}
	
	@PostMapping("/bankoperation")
	public String transfer(Model model, Account modelReceiving, HttpSession session) {
		
		Account sending = (Account) session.getAttribute("acc");
		Account receiving = accountService.authenticateByCpf(modelReceiving.getCpf());
		Double amount = modelReceiving.getCash();
		String resposta;
		
		if (sending != null && receiving != null && amount != null && sending.getId() != receiving.getId()) {
			try {
				boolean bool = sending.send(receiving, amount);
				if (bool) {
					resposta = "Operação feita com sucesso";
					transactionService.add(sending, receiving, amount);
				} else {
					resposta = "Erro na transferência";
				}
			} catch (Exception e) {
				resposta = "Erro na transferência" + " Erro: " + e;
			}
		} else {
			resposta = "";
		};
		
		accountService.update(receiving);
		accountService.update(sending);
		
		model.addAttribute("resposta", resposta);
		model.addAttribute("acc", sending);
		
		model.addAttribute("modelReceiving", new Account());
		return "bankoperation";
	}

	@GetMapping("/transaction")
	public String transaction(Model model) {
		model.addAttribute("modelReceiving", new Account());
		return "transaction";
	}
}
