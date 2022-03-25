package UI;

import java.util.Scanner;

import DAO.dlrmsSQL;
import DTO.Account;
import DTO.Goods;
import DTO.User;

public class dlrmsMain {

	public static void main(String[] args) {
		User user = new User();
		
		Goods goods = new Goods();
		dlrmsSQL sql = new dlrmsSQL();

		Scanner sc = new Scanner(System.in);

		boolean run = true; // 이근마켓 메인화면 while 실행 변수

		int menu = 0; //메뉴 switch문
		int lgmenu = 0;//메뉴 변수

		String loginId = ""; //로그인 전역변수

		sql.connect(); //DB 연결

		while (run) {
			System.out.println("╭────────────");
			System.out.println("╰─﹏ | welcome ! 이근마켓!! .°•");
			System.out.println("ੈ♡₊˚•");
			System.out.println("╭ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝╮ ");
			System.out.println("      1.회원가입  2.로그인  3.어플종료          ");
			System.out.println("╰ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞╯");
			System.out.print("메뉴선택 >>");
		
			menu = sc.nextInt();
			System.out.println("\n \n ");
			switch (menu) {
			case 1:
				System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
				System.out.println("    	    회원가입		      ");
				System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
				System.out.print("아이디 생성 >");
				String uId = sc.next();
				System.out.print("비밀번호 생성 >");
				String uPw = sc.next();
				System.out.print("이름을 입력해주세요 >");
				String uName = sc.next();
				System.out.print("생년월일을 입력해주세요 >");
				String uBirth = sc.next();
				System.out.print("주소를 입력해주세요 >");
				String uAddr = sc.next();
				System.out.print("연락처를 입력해주세요 >");
				String uPnum = sc.next();
				System.out.println();
				System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
				
				user.setuId(uId);
				user.setuPw(uPw);
				user.setuName(uName);
				user.setuBirth(uBirth);
				user.setuAddr(uAddr);
				user.setuPnum(uPnum);

				sql.insertUser(user); //회원가입 정보 입력

				break;

			case 2:
				boolean check = false; // 로그인 확인 유/무
				String loginPw = ""; //로그인 패스워드
				boolean lgrun = true; //마켓 메뉴 while문 실행 변수

				System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
				System.out.println("    	   로그인 정보  	      ");
				System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
				System.out.print("회원ID를 입력해주세요 > ");
				loginId = sc.next();

				System.out.print("회원PW를 입력해주세요 > ");
				loginPw = sc.next();
				System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
				check = sql.login(loginId, loginPw); //로그인 확인 메소드
				System.out.println("\n \n");
			
				if (check) {
					while (lgrun) {
						System.out.println("ੈ♡₊˚•");
						System.out.println("╭ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝╮ ");
						System.out.println("             이근마켓 메뉴				");
						System.out.println("    1.조회/구매 2.물품등록  3.판매물품관리		");
						System.out.println("    4.나의계좌  5.로그아웃  6.어플종료");
						System.out.println("╰ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞╯");
						System.out.print("메뉴를 선택해주세요(1~6) >");
						lgmenu = sc.nextInt();
					
						switch (lgmenu) {
						case 1:
							boolean brun = true; //구매 while 문 변수 
							int bmenu = 0; //구매 번호 선택 (switch)
							while (brun) {
								System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
								System.out.println("1.조회   2.구매   3.뒤로가기");
								System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
								System.out.print("메뉴를 선택해주세요 >>");
								bmenu = sc.nextInt();
								

								switch (bmenu) {
								case 1:
									System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
									System.out.println("            물품 조회 ");
									System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
									System.out.print("조회하실 상품명을 입력해주세요 >");
									String good = sc.next();

									goods.setgName(good); 

									sql.selectGoods(goods); //sql selectgoods 메소드 (물품 조회 메소드)
									System.out.println();
								
									break;
									
								case 2:

									boolean grun = true; //물품 구매 while
									int gmenu = 0; //switch 변수
									while (grun) {
										System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
										System.out.println("            물품 구매");
										System.out.println("  1.물품조회  2.물품구매    3.뒤로가기");
										System.out.println("┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈┈┈☼┈┈");
										System.out.print("메뉴를 선택해주세요 >>");
										gmenu = sc.nextInt();
										String gName = ""; //gName은 물품 명 
										int gNo = 0;  //상품 번호 변수
										switch (gmenu) {
										case 1:
											
											System.out.print("상품명 조회 >");
											
											gName = sc.next();

											goods.setgName(gName); 

											if (sql.checkgName(goods)) {  //상품명 존재 여부 확인
												sql.selectGoods(goods); // 상품 조회
											} else {
												System.out.println("존재하지않는 상품명입니다.  다시입력해주세요");
											}
											break;
										case 2:
											
											System.out.print("상품 번호 입력 >");
											gNo = sc.nextInt();

											goods.setgNo(gNo);
											sql.GoodsPrice(goods); // 상품 가격 리턴
											

											if (sql.checkMyId(goods).equals(loginId)) { //판매자 아이디와 로그인 아이디가 같은지 확인 
												System.out.println("자신이 등록한 물품은 구매할 수 없습니다.");
											} else {

												if (sql.Balance(loginId) >= sql.GoodsPrice(goods)) { //구매자의 잔액이 상품의 가격보다 같거나 클 경우 
													sql.BuyGoods(loginId, gNo, sql.GoodsPrice(goods)); //물품 구매 메소드
													System.out.println("구매에 성공하였습니다.");

												}

												else if (sql.Balance(loginId) < sql.GoodsPrice(goods)) { //잔액 부족할 때

													System.out.println("구매에 실패하였습니다 (잔액 부족)");
													boolean srun = true;// 충전 while
													int smenu = 0; //충전 메뉴 선택 switch
													while (srun) {
														System.out.println("충전하시겠습니까? (Y=1 / N=2)?");
														smenu = sc.nextInt();
														switch (smenu) {
														case 1:
															int dps = 0; //입금액 변수
															System.out.print("입급하실 금액을 입력해주세요 >");
															dps = sc.nextInt();
															sql.deposit(dps, loginId); //입금 메소드 
															srun = false; //입금하고 바로 나가기 때문에 false를 씀
															break;
														case 2:
															srun = false; //case2 입력시 바로 while 탈출
															break;
														default:
															System.out.println("1/2 중에 입력해주세요");
															break;
														}
													}
												} else {

													System.out.println("구매에 실패하였습니다 (상품정보 오류)");

												}
											}

										case 3:
											grun = false; 
											break;
										default:
											System.out.println("다시입력해주세요");
											break;
										} 
									} //end grun while

									break;

								case 3:
									brun = false;
									break;
								default:
								}
							}

							break;
						case 2:
							if (sql.checkNotice(loginId) < 3) { //게시글 제한 메소드
								System.out.println("물품 등록 메뉴입니다.");
								System.out.print("등록하실 상품명을 입력해주세요>");
								String gName = sc.next();
								System.out.println("상품에 대한 설명을 입력해주세요 >");
								String gInfo = sc.next();
								System.out.println("판매하실 가격을 입력해주세요");
								int gPrice = sc.nextInt();

								goods.setgName(gName);
								goods.setgInfo(gInfo);
								goods.setgPrice(gPrice);

								sql.insertGoods(goods, loginId); //물품등록 메소드

							} else {
								System.out.println("게시글은 3개까지만 작성 가능합니다.");
							}

							break;
						case 3:

							boolean mrun = true; //판매 while 변수
							int mmenu = 0; // 판매 실행 변소
							while (mrun) {
								System.out.println("ੈ♡₊˚•");
								System.out.println("╭ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝╮ ");
								System.out.println("           판매중인 물품 메뉴입니다.");
								System.out.println("        1.조회  2.수정  3.삭제  4. 뒤로가기");
								System.out.println("╰ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞╯");
								System.out.print("메뉴를 선택해주세요 >");
								mmenu = sc.nextInt();

								switch (mmenu) {
								case 1:
									System.out.println("<판매중인 물품>");

									sql.SellingGoods(loginId); //판매물품 조회 메소드
									break;
								case 2:
									System.out.println("<판매중인 물품 수정>");
									System.out.print("수정하실 물품명을 입력해주세요");
									String egoods = sc.next();

									
									System.out.print("변경후 물품가격 >");
									int egPrice = sc.nextInt();
									System.out.print("변경후 물품설명 >");
									String egInfo = sc.next();

									goods.setgName(egoods);
									goods.setgPrice(egPrice);
									goods.setgInfo(egInfo);

									sql.UpdateGoods(goods); //판매 물품 수정 메소드

									break;
								case 3:
									System.out.println("<판매중인 물품 삭제>");
									System.out.print("삭제하실 물품명을 입력해주세요>");
									String dgoods = sc.next();

									goods.setgName(dgoods);
									sql.DeleteGoods(loginId, goods); //판매물품 삭제 메소드

									break;
								case 4:
									mrun = false;
									break;
								}
							} //판매 while 종료

							break;

						case 4:

							boolean arun = true; // 입금/출금/잔액조회 while
							int amenu = 0;// 입금/출금/잔액조회 switch
							while (arun) {
								System.out.println("ੈ♡₊˚•");
								System.out.println("╭ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ◜◝╮ ");
								System.out.println("            입금/출금/잔액조회 메뉴입니다.");
								System.out.println("1. 계좌등록 2. 잔액조회 3. 입금 4. 출금  5. 뒤로가기");
								System.out.println("╰ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞╯");
								System.out.print("메뉴를 선택해주세요 > ");
								amenu = sc.nextInt();
								switch (amenu) {
								case 1:
									if (sql.checkCount(loginId) > 0) { //계좌 갯수 제한 메소드
										System.out.println("계좌는 2개이상 만들수 없습니다.");
									} else {
										sql.insertAccount(loginId); //계좌 생성 메소드
										System.out.println("계좌생성 완료");
									}
									break;
								case 2:
									sql.checkBalance(loginId); //잔액조회 메소드
									break;
								case 3:
									int dps = 0; //입금액 변수
									System.out.print("입금하실 금액을 입력해주세요 >");
									dps = sc.nextInt();
									sql.deposit(dps, loginId); //입금 메소드
									break;
								case 4:
									int wd = 0; //출금액 변수
									System.out.print("출금하실 금액을 입력해주세요 >");
									wd = sc.nextInt();
									int balance2 = sql.Balance(loginId); // 잔액변수
									if (balance2 >= wd) {
										sql.withdraw(wd, loginId); //출금 메소드
									} else {
										System.out.println("잔액이 " + (wd - balance2) + "원 부족합니다.");
									}
									break;
								case 5:

									arun = false;
									break;
								default:
									System.out.println("메뉴를 다시 입력해주세요");
									break;
								}
							}

							break;

						case 5:
							System.out.println("로그아웃을 실행합니다.");

							lgrun = false;
							break;
						case 6:
							System.out.println();
							System.out.println("ੈ♡₊˚•");
							System.out.println("╭ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝╮ ");
							System.out.println("              어플을 종료합니다.");
							System.out.println("╰ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞╯");
							System.out.println("O");
							System.out.println("°");
							System.out.println("/}__/}");
							System.out.println("( • ▼•)");
							lgrun = false;
							run = false;
							
							break;
						default:
							System.out.println("1~6번 메뉴를 입력해주세요.");

							break;
						}
					}
				}
				break;
			case 3:
				System.out.println("ੈ♡₊˚•");
				System.out.println("╭ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝ ͡ ◜◝╮ ");
				System.out.println("              어플을 종료합니다.");
				System.out.println("╰ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞ ͜ ◟◞╯");
				System.out.println("O");
				System.out.println("°");
				System.out.println("/}__/}");
				System.out.println("( • ▼•)");
				run = false;
				break;

			default:
				System.out.println("1,2,3 중에서 입력해주세요.");
				break;
			}

		}
		sql.conclose();
	}
}
