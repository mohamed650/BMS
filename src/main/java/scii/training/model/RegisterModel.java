package scii.training.model;

import lombok.Data;

@Data
public class RegisterModel {
	
	private String customer_Id;
	private String account_Number;
	private String customer_AccountTypeId;
	private String customer_AccountType;
	private String customer_FirstName;
	private String customer_LastName;
	private String customer_Gender;
	private String customer_Phone;
	private String customer_Email;
	private String customer_DOB;
	private String customer_Address;
	private String customer_ZipCode;
	private int state_code;
	private String state_name;
	private String city_code;
	private String city_name;
	private String customer_Aadhar;
	private String customer_OccupationId;
	private String customer_Occupation;
	private String imgFile;
	private String customer_Password;
	private String joint_customer_FirstName;
	private String joint_customer_LastName;
	private String joint_customer_Gender;
	private String joint_customer_Phone;
	private String joint_customer_Email;
	private String joint_customer_DOB;
	private String joint_customer_Address;
	private String joint_customer_ZipCode;
	private int joint_state_code;
	private String joint_state_name;
	private String joint_city_code;
	private String joint_city_name;
	private String joint_customer_Aadhar;
	private String joint_customer_OccupationId;
	private String joint_customer_Occupation;
	private String created_date;
	private String updated_date;
	private String last_login_dateTime;
	private String block_flg;
	private String active_flg;
	private String deactivated_Date;
}
