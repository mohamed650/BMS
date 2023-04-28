package scii.training.model;

import lombok.Data;

@Data
public class TransactionsModel {
	
	private String transaction_Id;
	private String from_account_Number;
	private String to_account_Number;
	private int amount;
	private String transaction_Date;
	private String description;
	private String cr_dr;
	private String account_Number;
	private int balance;
	private String otp;
}
