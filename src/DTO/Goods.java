package DTO;

public class Goods {
	private int gNo;
	private String gName;
	private int gPrice;
	private String guId;
	private String gInfo;
	public int getgNo() {
		return gNo;
	}
	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public int getgPrice() {
		return gPrice;
	}
	public void setgPrice(int gPrice) {
		this.gPrice = gPrice;
	}
	public String getGuId() {
		return guId;
	}
	public void setGuId(String guId) {
		this.guId = guId;
	}
	public String getgInfo() {
		return gInfo;
	}
	public void setgInfo(String gInfo) {
		this.gInfo = gInfo;
	}
	@Override
	public String toString() {
		return "Goods [gNo=" + gNo + ", gName=" + gName + ", gPrice=" + gPrice + ", guId=" + guId + ", gInfo=" + gInfo
				+ "]";
	}
	
}
