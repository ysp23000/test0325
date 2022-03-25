package DTO;

public class User {
	private String uName;		//이름
	private String uId;			//아이디
	private String uPw;			//패스워드
	private String uAddr;		//주소
	private String uPnum;		//연락처
	private String uBirth;		//생년월일
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuPw() {
		return uPw;
	}
	public void setuPw(String uPw) {
		this.uPw = uPw;
	}
	public String getuAddr() {
		return uAddr;
	}
	public void setuAddr(String uAddr) {
		this.uAddr = uAddr;
	}
	public String getuPnum() {
		return uPnum;
	}
	public void setuPnum(String uPnum) {
		this.uPnum = uPnum;
	}
	public String getuBirth() {
		return uBirth;
	}
	public void setuBirth(String uBirth) {
		this.uBirth = uBirth;
	}
	@Override
	public String toString() {
		return "User [uName=" + uName + ", uId=" + uId + ", uPw=" + uPw + ", uAddr=" + uAddr + ", uPnum=" + uPnum
				+ ", uBirth=" + uBirth + "]";
	}
	
	
	
}