package DTO;

public class Account {
	/*
		ACCOUNTNUM  NVARCHAR2(20) PRIMARY KEY,
    	BALANCE     NUMBER,
		CODENUM     NUMBER REFERENCES BCODE(CODENUM),
    	CID         NVARCHAR2(10) REFERENCES CLIENT(CID)
	 */
	
	private int balance;
	private String uId;
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	@Override
	public String toString() {
		return "Account [balance=" + balance + ", uId=" + uId + "]";
	}
	
	
	
	
	
}
