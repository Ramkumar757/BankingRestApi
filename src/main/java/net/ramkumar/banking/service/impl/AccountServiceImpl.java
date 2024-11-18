package net.ramkumar.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.ramkumar.banking.dto.AccountDto;
import net.ramkumar.banking.entity.Account;
import net.ramkumar.banking.mapper.AccountMapper;
import net.ramkumar.banking.repository.AccountRepository;
import net.ramkumar.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Accounts does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Accounts does not exists"));
		
		double total = account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withDrawal(Long id, double amount) {
		Account savedAccount;
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Accounts does not exists"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient amount");
		}
		else {
		double total = account.getBalance()-amount;
		account.setBalance(total);
		 savedAccount = accountRepository.save(account);
		}
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		
		
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) ->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
		
		 
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Accounts does not exists"));
		
		accountRepository.deleteById(id);
		
	}
}
