package net.ramkumar.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ramkumar.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
