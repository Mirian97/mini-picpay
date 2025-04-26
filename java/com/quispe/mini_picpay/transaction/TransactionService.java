package com.quispe.mini_picpay.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quispe.mini_picpay.authorization.AuthorizerService;
import com.quispe.mini_picpay.notification.NotificationService;
import com.quispe.mini_picpay.wallet.Wallet;
import com.quispe.mini_picpay.wallet.WalletRepository;
import com.quispe.mini_picpay.wallet.WalletType;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private AuthorizerService authorizerService;

	@Autowired
	private NotificationService notificationService;

	@Transactional
	public Transaction create(Transaction transaction) {
		validate(transaction);

		Transaction newTransaction = transactionRepository.save(transaction);

		Wallet payerWallet = walletRepository.findById(newTransaction.payer()).get();
		walletRepository.save(payerWallet.debit(transaction.value()));

		Wallet payeeWallet = walletRepository.findById(newTransaction.payee()).get();
		walletRepository.save(payeeWallet.credit(transaction.value()));

		authorizerService.authorize(newTransaction);

		notificationService.notify(newTransaction);

		return newTransaction;
	}

	/*
	 * Validate:
	 * - the payer has a commom wallet
	 * - the payer has enough balance
	 * - the payer is not the payee
	 */
	private void validate(Transaction transaction) {
		walletRepository.findById(transaction.payee())
				.map(payee -> walletRepository.findById(transaction.payer())
						.map(payer -> isTransactionValid(transaction, payer) ? transaction : null)
						.orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s".formatted(transaction)))

				).orElseThrow(() -> new InvalidTransactionException("Invalid transaction - %s".formatted(transaction)));
	}

	private boolean isTransactionValid(Transaction transaction, Wallet payer) {
		return payer.type() == WalletType.COMMOM.getValue()
				&& payer.balance().compareTo(transaction.value()) >= 0
				&& !payer.id().equals(transaction.payee());
	}

	public List<Transaction> list() {
		return transactionRepository.findAll();
	}
}
