package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.Goods;
import DTO.User;
import UTIL.DBC;

public class dlrmsSQL {
	Connection con = null;

	PreparedStatement pstmt = null;

	ResultSet rs = null;

	public void connect() { // DB 연결 
		con = DBC.DBConnect(); 
	}

	public void conclose() { //DB 해제 
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertUser(User user) { //회원가입

		String sql = "INSERT INTO USERDB VALUES(?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getuName());
			pstmt.setString(2, user.getuId());
			pstmt.setString(3, user.getuPw());
			pstmt.setString(4, user.getuAddr());
			pstmt.setString(5, user.getuBirth());
			pstmt.setString(6, user.getuPnum());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원가입에 성공하였습니다.");
			} else {
				System.out.println("회원가입에 실패하였습니다. 다시 시도해주세요.");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public boolean login(String uId, String uPw) { //로그인 
		boolean check = false;

		String sql = "SELECT * FROM USERDB WHERE U_ID=? AND U_PW=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uId);
			pstmt.setString(2, uPw);

			rs = pstmt.executeQuery();

			check = rs.next();

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("로그인에 성공하였습니다.");
			} else {
				System.out.println("로그인에 실패하였습니다. 다시 시도해주세요.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	public void selectGoods(Goods goods) {  //물품 조회
		String sql = "SELECT GOODS.*,USERDB.U_ID FROM GOODS JOIN USERDB ON (G_U_ID=U_ID) WHERE G_NAME =?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, goods.getgName());

			rs = pstmt.executeQuery();

			while (rs.next()) { 
				System.out.println("판매자명 :" + rs.getString(6) + "\n상품번호 : " + rs.getInt(1) + "\n상품명 : "
						+ rs.getString(2) + "\n상품가격 :" + rs.getInt(3) + "\n상품설명 : " + rs.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	

	public void insertGoods(Goods goods, String uId) { //상품 등록 

		String sql = "INSERT INTO GOODS VALUES ((SELECT NVL(MAX(G_NO),0)+1 FROM GOODS),?,?,?,?)";
		//(SELECT NVL(MAX(G_NO),0)+1 FROM GOODS) ==> 상품명 번호 자동생성

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, goods.getgName());
			pstmt.setInt(2, goods.getgPrice());
			pstmt.setString(3, uId);
			pstmt.setString(4, goods.getgInfo());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("상품등록에 성공하였습니다.");
			} else {
				System.out.println("상품등록에 실패하였습니다. 다시 시도해주세요.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void SellingGoods(String uId) { //판매 상품 조회
		String sql = "SELECT * FROM GOODS WHERE G_U_ID = ? ";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, uId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("상품명 : " + rs.getString(2) + "\n상품가격 :" + rs.getInt(3) + "\n상품설명 : "
						+ rs.getString(5) + "\n\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deposit(int balance, String uId) { //입금
		String sql = "UPDATE ACCOUNT SET A_BAL = A_BAL+? WHERE A_U_ID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, balance);
			pstmt.setString(2, uId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(balance + "원 입금됐습니다.");
			} else {
				System.out.println("입금을 실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void withdraw(int balance, String uId) { //출금
		String sql = "UPDATE ACCOUNT SET A_BAL = A_BAL-? WHERE A_U_ID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, balance);
			pstmt.setString(2, uId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(balance + "원 출금했습니다.");
			} else {
				System.out.println("출금을 실패했습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	public void checkBalance(String uId) { //잔액조회
		String sql = "SELECT * FROM ACCOUNT WHERE A_U_ID =?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(2) + "님 \n잔액 :" + rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	public int Balance(String uId) {// 잔액 리턴
		int balance = 0;
		String sql = "SELECT A_BAL FROM ACCOUNT WHERE A_U_ID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				balance = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return balance;
	}

	public String GoodsName(Goods goods) { //상품명 리턴
		String name = "";
		String sql = "SELECT G_NAME FROM GOODS WHERE G_NO=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods.getgNo());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return name;
	}

	public int GoodsPrice(Goods goods) { //상품 가격 리턴
		int price = 0;

		String sql = "SELECT G_PRICE FROM GOODS WHERE G_NO=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods.getgNo());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				price = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return price;
	}

	public void insertAccount(String uId) { //계좌 등록 
		String sql = "INSERT INTO ACCOUNT VALUES(0,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, uId);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("등록에 성공하였습니다.");
			} else {
				System.out.println("등록에 실패하였습니다. 다시 시도해주세요.");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void BuyGoods(String uId, int gNo, int price) { //구매

		String sql1 = "UPDATE ACCOUNT SET A_BAL = A_BAL-? WHERE A_U_ID=?";
		String sql2 = "UPDATE ACCOUNT SET A_BAL = A_BAL+? WHERE A_U_ID=(SELECT G_U_ID FROM GOODS WHERE G_NO = ?) ";
		//(SELECT G_U_ID FROM GOODS WHERE G_NO = ?) ===> G_NO으로 판매자 ID 조회
		String sql3 = "DELETE FROM GOODS WHERE G_NO= ? ";

		try {
			pstmt = con.prepareStatement(sql1);

			pstmt.setInt(1, price);
			pstmt.setString(2, uId);

			int result1 = pstmt.executeUpdate();

			pstmt = con.prepareStatement(sql2);

			pstmt.setInt(1, price);
			pstmt.setInt(2, gNo);

			int result2 = pstmt.executeUpdate();

			pstmt = con.prepareStatement(sql3);

			pstmt.setInt(1, gNo);

			int result3 = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void UpdateGoods(Goods goods) { //상품 수정
		String sql1 = "UPDATE GOODS SET  G_PRICE = ? , G_INFO = ? WHERE G_NAME= ?";
		String sql2 = "SELECT * FROM GOODS ";
		try {
			pstmt = con.prepareStatement(sql1);
			
			pstmt.setInt(1, goods.getgPrice());
			pstmt.setString(2, goods.getgInfo());
			pstmt.setString(3, goods.getgName());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}

			pstmt = con.prepareStatement(sql2);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(
						"상품명 : " + rs.getString(2) + "\n상품가격 :" + rs.getInt(3) + "\n상품설명 : " + rs.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void DeleteGoods(String loginId, Goods goods) { //상품 삭제
		String sql = "DELETE FROM GOODS WHERE G_U_ID= ? AND G_NAME = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, goods.getgName());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int checkCount(String loginId) { //계좌 생성 제한
		int cnt = 0;

		String sql = "SELECT COUNT(*) FROM ACCOUNT WHERE A_U_ID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	public int checkNotice(String loginId) { //  도배글 제한 (3개)
		int cnt = 0;

		String sql = "SELECT COUNT(*) FROM GOODS WHERE G_U_ID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	public boolean checkgName(Goods goods) { //상품명 존재 여부 확인
		boolean check = false;

		String sql = "SELECT * FROM GOODS WHERE G_NAME = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, goods.getgName());

			rs = pstmt.executeQuery();

			check = rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	public String checkMyId(Goods goods) { // 판매자 아이디 확인

		String check = "";

		String sql = "SELECT G_U_ID FROM GOODS WHERE G_NO = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods.getgNo());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

}
