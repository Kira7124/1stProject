package com.project4.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.catalina.connector.Response;

public class MemberDAO {
	
	Connection con;
	String sql;
	ResultSet rs;
	PreparedStatement pstmt;
	
	// 회원정보 수정 - 회원 포인트 변경 메서드 updateMemberPoint(MemberDTO mdto, String payAmount)
	
	public int updateMemberPoint(MemberDTO mdto, int payAmount) {
		int result = 0;
		int plusPoint = (int) (payAmount*0.05);
		
		try {
			// DB 연결
			getCon();
			
			// sql & pstmt
			sql = "update user set point = point + ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, plusPoint);
			pstmt.setString(2, mdto.getId());
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	
	// 회원정보 수정 - 회원 포인트 변경 메서드 updateMemberPoint(MemberDTO mdto, String payAmount)
	
	
	// 회원정보 수정 - 회원등급 변경 메서드 updateMemberGrade(MemberDTO mdto)
	
	public int updateMemberGrade(MemberDTO mdto, String toGrade) {
		
		System.out.println(" DAO : updateMemberGrade()");
		System.out.println("mdto : " + mdto);
		System.out.println("업그레이드 등급 : " + toGrade);
		int result = 0;
		
		try {
			// DB 연결
			getCon();
			
			// sql & pstmt
			sql = "update user set grade = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, toGrade);
			pstmt.setString(2, mdto.getId());
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	
	// 회원정보 수정 - 회원등급 변경 메서드 updateMemberGrade(MemberDTO mdto)
	
	
	// 회원정보 수정 - 비밀번호 변경 메서드 updatePassword(MemberDTO mdto)
	public int updatePassword(MemberDTO mdto) {
		
		int result = 0;
		try {
			// DB 연결
			getCon();
			
			// sql & pstmt
			sql = "update user set pw = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getPw());
			pstmt.setString(2, mdto.getId());
			
			// 데이터처리
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	// 회원정보 수정 - 비밀번호 변경 메서드 updatePassword(MemberDTO mdto)

	
	// 회원 정보 수정 - 이메일 변경 메서드 updateEmail(MemberDTO mdto)
	
	public int updateEmail(MemberDTO mdto) {
		int result = -1;
		System.out.println(" DAO : updateEmail() 실행");
		try {
			// DB
			getCon();
			
			//sql & pstmt
			sql = "select * from user where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getEmail());
			System.out.println("DAO : updateEmail(), 전달받은 이메일 = " + mdto.getEmail());
			// 실행 및 데이터 처리
			rs = pstmt.executeQuery();
			if (rs.next() ) {
				// 중복
				System.out.println(" DAO : 중복 이메일 존재");
				result = -1;
				return result;
			}
			
			// 이메일 변경
			// sql & pstmt
			sql = "update user set email = ?, email_auth_check = '0' where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getEmail());
			pstmt.setString(2, mdto.getId());
			
			// 실행 & 데이터 처리
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println(" DAO : 이메일 변경 완료");
				return result;
			} else {
				System.out.println(" DAO : 이메일 변경 중 오류 발생");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		return result;
	}
	
	// 회원 정보 수정 - 이메일 변경 메서드 updateEmail(MemberDTO mdto)

	
	
	// 회원 정보 수정 -  휴대전화 번호 변경 메서드 changePhoneNum(MemberDTO mdto)
	
	public int changePhoneNum(MemberDTO mdto) {
		
		System.out.println(" DAO : changePhoneNum(mdto) 실행");
		int result = 0;
		// id, phoneNum
		String id = mdto.getId();
		String newPhoneNum = mdto.getPhoneNum();
		
		try {
			// DB 연결
			getCon();
			
			// sql & pstmt
			sql = "select * from user where phone_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPhoneNum);
			
			// 실행 및 데이터 처리
			rs = pstmt.executeQuery();
			if(rs.next() ) {
				result = -1; // 중복 값 존재
				
			} else {
				sql = "update user set phone_num = ? where id = ?";
				pstmt = con.prepareStatement(sql);
				System.out.println(" DAO : id = "+id);
				System.out.println(" DAO : newPhoneNum = "+newPhoneNum);
				pstmt.setString(1, newPhoneNum);
				pstmt.setString(2, id);
				
				// 실행
				result = pstmt.executeUpdate();
			}
				
			if (result == 1) {
				// 정상 업데이트 
				System.out.println(" DAO : changePhoneNum() 정상 실행 완료");
			} else if (result == 0){
				// 오류
				System.out.println(" DAO : changePhoneNum() 오류 발생");
			} else { // -1
				System.out.println(" DAO : changePhoneNum() 중복 존재 ");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
		
	}
	
	// 회원 정보 수정 -  휴대전화 번호 변경 메서드 changePhoneNum(MemberDTO mdto)
	
	
	// 아이디 찾기 메서드 findMemberId (String inputEmail)
	
	public String findMemberId (String inputEmail) {
		System.out.println(" DAO : findMemberId() 실행");
		try {
			// DB 연결
			getCon();
			
			// sql & pstmt
			sql = "select id from user where email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputEmail);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 데이터 처리
			if (rs.next()) {
				
				String id = rs.getString("id");
				
				return id;
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return null;
	}
	
	// 아이디 찾기 메서드 findMemberId (String inputEmail)
	
	
	// 비밀번호 찾기 메서드 findMemberPw (String inputId, String inputEmail)
	
	public String findMemberPw (String inputId, String inputEmail) {
		System.out.println(" DAO : findMemberPw() 실행");
		try {
			// DB 연결
			getCon();
			
			// sql & pstmt
			sql = "select * from user where id = ? and email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputEmail);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 데이터 처리
			if (rs.next()) {
				
				return "true";
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return "false";
	}
	
	// 비밀번호 찾기 메서드 findMemberPw (String inputId, String inputEmail)
	
	
	// 회원 정보 불러오기 메서드 getMemberInfo(String id)
	
	public MemberDTO getMemberInfo(String id) {
		System.out.println(" DAO : getMemberInfo() 실행");
		MemberDTO mdto ;
		try {
			// DB 연결
			getCon();
			
			// sql & pstmt
			sql = "select * from user where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 데이터 처리
			if (rs.next()) {
				
				mdto = new MemberDTO();
				
				mdto.setName(rs.getString("name"));
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setBirthDate(rs.getString("birth_date"));
				mdto.setAddress(rs.getString("address"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhoneNum(rs.getString("phone_num"));
				mdto.setGrade(rs.getString("grade"));
				mdto.setPayAmount(rs.getInt("pay_amount"));
				System.out.println(mdto);
				
				return mdto;
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return null;
	}
	
	// 회원 정보 불러오기 메서드 getMemberInfo(String id)
	
	
	
	// 아이디 중복 확인 메서드 isDuplicateId (String id)
	
	public boolean isDuplicateId (String id) {
		
		System.out.println(" DAO : isDuplicateId() 호출");
		try {
			// DB 연결
			getCon();
			
			// sql & pstmt
			sql = "select id from user where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			// 데이터 처리 
			if (rs.next()) {
				System.out.println(" DAO : 중복 아이디 존재");
				return true;
			}
			System.out.println(" DAO : 사용 가능한 아이디");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return false;
	}
	
	
	// 아이디 중복 확인 메서드 isDuplicateId (String id)

	
	// 이메일 중복 확인 메서드 isDuplicateEmail(MemberDTO mdto)
	
	public boolean isDuplicateEmail(MemberDTO mdto) {
		try {
			// DB 연결
			getCon();
			
			// SQL & pstmt
			sql = "select email from user where email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getEmail());
			
			// 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 입력받은 이메일이 존재
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return false;
	}
	
	// 이메일 중복 확인 메서드 isDuplicateEmail(MemberDTO mdto)
	
	
	
	// 이메일 인증 처리 메서드 updateEmailAuthCheck(String id)
	public int updateEmailAuthCheck(String id) {
		System.out.println(" DAO : updateEmailAuthCheck(String id) 호출");
		int result = 0;
		try {
			// DB 연결
			getCon();
			
			// SQL & pstmt 
			sql = "UPDATE user SET email_auth_check = 1 WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 실행
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
		
	}
	
	
	// 이메일 인증 처리
	
	
	// 특정 아이디를 갖는 유저의 이메일 해쉬코드를 구하는 메서드 getUserEmailHash()
	public String getUserEmailHash(MemberDTO mdto) {
		System.out.println(" DAO : getUserEmailHash(mdto) 호출");
		String emailHash = "";
		try {
			// DB 연결
			getCon();
			
			// SQL % pstmt
			sql = "select email_hash from user where id = ?";
			pstmt = con.prepareStatement(sql);
			
			// 실행
			rs = pstmt.executeQuery();
			pstmt.setString(1, mdto.getId());
			// 데이터 처리
			if(rs.next()) {
				emailHash = rs.getString(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		
		return emailHash;
	}
	

	// 특정 아이디를 갖는 유저의 이메일 해쉬코드를 구하는 메서드 getUserEmailHash()
	
	// 이메일 인증 확인 메서드
	public boolean isEmailAuthenticate(String id) {
		System.out.println(" DAO : isEmailAuthenticate() 호출");
		try {
			// DB 연결
			getCon();
			// SQL 작성 및 pstmt 객체 생성
			sql = "select email_auth_check from user where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// SQL 실행
			rs = pstmt.executeQuery();
			// 1 = 이메일 인증 완료 / 0 = 이메일 인증 X
			if (rs.next()) {
				if (rs.getInt(1) == 1) {
					System.out.println(" DAO : 이메일 인증 확인 완료 ");
					return true;
					}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		

		
		return false;
	}	
	
	
	
	// 이메일 인증 확인 메서드
	
	// DB 연결 메서드 getCon()
	public Connection getCon() throws Exception {
		
		Context initCTX = new InitialContext();
		
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc");
		con = ds.getConnection();
		System.out.println(" DAO : DB 연결 성공(커넥션풀)");
		System.out.println(" DAO : " + con);
		
		return con;
	}
	// DB 연결 메서드 getCon()
	
	// DB 해제 메서드 closeDB()

	public void closeDB() {
			try {
				if (rs != null)	rs.close();
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
				System.out.println(" DAO : DB 연결 해제");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	// DB 해제 메서드 closeDB()
	
	// 로그인 체크 메서드 - memberCheck(dto)

	public int memberCheck (MemberDTO mdto) {
		System.out.println(" DAO : memberCheck() 호출");
		int result = -1; // -1 : 로그인 정보 없음 // 0 : 아이디, 비밀번호 오류 // 1 : 로그인
		try {
			//1,2 DB 연결
			con = getCon();
			//3. SQL 작성 및 pstmt 객체
			sql = "select pw from user where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			System.out.println(mdto.getId());
			//4. SQL 실행
			rs = pstmt.executeQuery();
			
			//5. 데이터 처리
			if (rs.next()) {
				
				if(mdto.getPw().equals(rs.getString("pw"))) {
					result = 1;	// id pw 같다
				} else {
					result = 0; // id pw 다르다 
				}
				
			} else {
				result = -1; // id 없음
			}
			System.out.println(" DAO : 로그인 데이터 처리 완료 (" + result + ")");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		finally {
			closeDB();
		}
		
		return result;
	}
			
			
	// 로그인 체크 메서드 - memberCheck(dto)
	
	// 회원가입 메서드 - insertMember(dto)
	
	public int insertMember(MemberDTO dto) {
		System.out.println(" DAO : insertMember() 호출");
		int result = 0;
			// 1,2 DB 연결
				try {
					con = getCon(); 
					
			// 3.  sql 작성 & pstmt 객체 생성
					sql = "insert into user"
							+ "(id, pw, birth_date, phone_num, address, email, reg_date, ad_check"
							+ ", final_login, point, grade, isAdmin, name,email_auth_check,emal_hash) "
							+ "values (?,?,?,?,?,?,now(),?,now(),?,?,?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getId());
					pstmt.setString(2, dto.getPw());
					pstmt.setString(3, dto.getBirthDate());
					pstmt.setString(4, dto.getPhoneNum());
					pstmt.setString(5, dto.getAddress());
					pstmt.setString(6, dto.getEmail());
					pstmt.setString(7, dto.getAdCheck());
					pstmt.setInt(8, dto.getPoint());
					pstmt.setString(9, dto.getGrade());
					pstmt.setInt(10, dto.getIsAdmin());
					pstmt.setString(11, dto.getName());
					pstmt.setInt(12, 0);
					pstmt.setString(13, dto.getEmailHash());
					
					

			// 4.  SQL 실행
					result = pstmt.executeUpdate();
					System.out.println("회원가입, 성공");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					closeDB();
				}
				return result;
			}
				
		// 회원가입 메서드 - insertMember(dto)
	
	public ArrayList getMemberList() {
		ArrayList memberList = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO mdto = new MemberDTO();
				
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setEmail(rs.getString("email"));
				mdto.setName(rs.getString("name"));
				mdto.setPhoneNum(rs.getString("phone_num"));
				mdto.setAddress(rs.getString("address"));
				memberList.add(mdto);
				
			}
			
			System.out.println("DAO : 회원목록 조회완료");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return memberList;
		
	}
	
	
	public int getMemberCount(String search) {
		int result = 0;
		
		try {
			//1,2 DB 연결
			con = getCon();
			//3. sql 구문 작성 & pstmt 객체 생성
			sql = "select count(*) from user where id like ?";
			pstmt = con.prepareStatement(sql);
			
			// ???
			pstmt.setString(1, "%"+search+"%");
			
			//4. sql 실행
			rs = pstmt.executeQuery();
			
			//5. 데이터처리 - 개수 저장
			if(rs.next()) {
				result = rs.getInt(1);
			}
			System.out.println(" DAO : 개수 : "+result+"개");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
		return result;
	}
	
	public ArrayList searchMembers(String search) {
		System.out.println(" DAO : searchMembers() 호출");
		ArrayList searchMembers = new ArrayList();
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 구문 작성(select) & pstmt 객체
			sql = "select * from user where id like ?";
			pstmt = con.prepareStatement(sql);
	
			pstmt.setString(1, "%"+search+"%");

			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> dto)
			while(rs.next()) {
				
				// 글 하나의 정보 => MemberDTO저장
				MemberDTO mdto = new MemberDTO();
				mdto.setId(rs.getString("id"));
				mdto.setName(rs.getString("name"));
				mdto.setPw(rs.getString("pw"));
				mdto.setPhoneNum(rs.getString("phone_num"));
				mdto.setEmail(rs.getString("email"));
				mdto.setAddress(rs.getString("address"));
				searchMembers.add(mdto);

			}
			System.out.println(searchMembers);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return searchMembers;
	}	
	
	
	
	
	// 11 - 14 까지 영역-----------------------------------------------------------
	
	//회원정보 삭제 메서드
	public void deleteMember(MemberDTO dto) {
	    try {
	        con = getCon();

	        // 회원 정보 삭제 전에 board 테이블에서 관련 데이터 삭제
	        sql = "delete from board where id=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, dto.getId());
	        pstmt.executeUpdate(); // board 테이블에서 관련 데이터 삭제

	        sql = "delete from cart where u_id=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, dto.getId());
	        pstmt.executeUpdate(); // cart 테이블에서 관련 데이터 삭제
	        
	        sql = "delete from order1 where id=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, dto.getId());
	        pstmt.executeUpdate(); // cart 테이블에서 관련 데이터 삭제
	        
	        
	        // 회원 정보 삭제
	        sql = "delete from user where id=?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, dto.getId());
	        pstmt.executeUpdate(); // 회원 정보 삭제 완료

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        closeDB();
	    }
	}
	
	// 11 - 15 까지 영역-----------------------------------------------------------
	
	public int updateMember(MemberDTO dto) {
		int result = -1;
		try {
			con = getCon();
			sql = "select pw from user where id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPw().equals(rs.getString("pw"))) {
					//3. sql 작성(update) & pstmt객체
					sql = "update user set name=?,email=?,address=?,phone_num=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getName());
					pstmt.setString(2, dto.getEmail());
					pstmt.setString(3, dto.getAddress());
					pstmt.setString(4, dto.getPhoneNum());
					pstmt.setString(5, dto.getId());
					//4. sql 실행
					result = pstmt.executeUpdate();
					// result = 1;
				}else {
					result = 0; // 사용자의 비밀번호 오류
				}
			}else {
				result = -1; // 회원정보X,에러발생
			}
			
			System.out.println(" DAO : 회원정보 수정완료! ("+result+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		 }	
		  finally {
			closeDB();
		}
		
		return result;
		
	}
}
