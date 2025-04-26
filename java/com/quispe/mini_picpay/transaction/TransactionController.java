package com.quispe.mini_picpay.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping
	public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
		return ResponseEntity.ok(transactionService.create(transaction));
	}

	@GetMapping
	public ResponseEntity<List<Transaction>> list() {
		return ResponseEntity.ok(transactionService.list());
	}
}
