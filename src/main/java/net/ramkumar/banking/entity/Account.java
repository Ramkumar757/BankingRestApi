package net.ramkumar.banking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Table(name="accounts")
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="account_holder")
	private String accountHolderName;
	private double balance;
	public Account(Long id, String accountHolderName, double balance) {
		
		this.id = id;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
	}
	public Account() {
		super();
		
	}

}
