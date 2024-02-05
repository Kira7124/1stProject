package com.project4.center.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class CenterDAO {

	// 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	private CenterDTO cdto;

	// 공통 디비 연결하기(CP)
	private Connection getCon() throws Exception {

		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc");
		con = ds.getConnection();

		return con;
	}

	// 공통 디비 자원해제
	public void CloseDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 수리 신청서 작성 메서드 - asRequest(cdto)
	public void asRequest(CenterDTO cdto) {
		int as_id = 0;

		try {
			// 1, 2. DB 연결
			con = getCon();
			// 3. SQL구문 작성(SELECT) & pstmt 객체
			sql = "SELECT MAX(as_id) FROM as_form";
			pstmt = con.prepareStatement(sql);
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if (rs.next()) {
				as_id = rs.getInt(1) + 1;
			}
			// 3. SQL 구문(INSERT) & pstmt 객체
			sql = "INSERT INTO as_form(as_id, as_model, as_phone_num, as_name, as_comment,"
					+ "as_zip_code, as_address, as_address_detail, as_reference,"
					+ "as_created_date, as_due_date, as_user_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			pstmt = con.prepareStatement(sql);
			// 3-1. ??? 처리
			pstmt.setInt(1, as_id);
			pstmt.setString(2, cdto.getAs_model());
			pstmt.setString(3, cdto.getAs_phone_num());
			pstmt.setString(4, cdto.getAs_name());
			pstmt.setString(5, cdto.getAs_comment());

			pstmt.setString(6, cdto.getAs_zip_code());
			pstmt.setString(7, cdto.getAs_address());
			pstmt.setString(8, cdto.getAs_address_detail());
			pstmt.setString(9, cdto.getAs_reference());

			pstmt.setString(10, cdto.getAs_due_date());
			pstmt.setString(11, cdto.getAs_user_id());
			// 4. SQL 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	// 수리 신청서 작성 메서드 - asRequest(cdto)

	// 수리 내역 조회 메서드 - getHistory(user_id)
	public ArrayList  getHistory(String user_id) {
		ArrayList centerList = new ArrayList();
		CenterDTO cdto = null;
		
		try {
			// 1. 2. DB 연결
			con =  getCon();
			// 3. SQL 구문 작성(SELECT) & pstmt 객체
			sql = "SELECT * FROM as_form WHERE as_user_id = ? ORDER BY as_created_date DESC";
			pstmt  = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리(rs -> dto)
			while(rs.next()) {
				cdto = new CenterDTO();
				
				cdto.setAs_id(rs.getInt("as_id"));
				cdto.setAs_model(rs.getString("as_model"));
				cdto.setAs_phone_num(rs.getString("as_phone_num"));
				cdto.setAs_name(rs.getString("as_name"));
				cdto.setAs_comment(rs.getString("as_comment"));
				cdto.setAs_zip_code(rs.getString("as_zip_code"));
				cdto.setAs_address(rs.getString("as_address"));
				cdto.setAs_address_detail(rs.getString("as_address_detail"));
				cdto.setAs_reference(rs.getString("as_reference"));
				cdto.setAs_created_date(rs.getDate("as_created_date"));
				cdto.setAs_due_date(rs.getString("as_due_date"));
				cdto.setAs_user_id(rs.getString("as_user_id"));
				
				centerList.add(cdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return centerList;
	}
	// 수리 내역 조회 메서드 - getHistory(user_id)

	// 수리 내역 조회 메서드 - getHistory(as_id)
	public CenterDTO getHistory(int as_id) {
		CenterDTO cdto = null;
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성(SLEECT) & pstmt 객체
			sql = "SELECT * FROM as_form WHERE as_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, as_id);
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				cdto = new CenterDTO();
				
				cdto.setAs_id(rs.getInt("as_id"));
				cdto.setAs_model(rs.getString("as_model"));
				cdto.setAs_phone_num(rs.getString("as_phone_num"));
				cdto.setAs_name(rs.getString("as_name"));
				cdto.setAs_comment(rs.getString("as_comment"));
				cdto.setAs_zip_code(rs.getString("as_zip_code"));
				cdto.setAs_address(rs.getString("as_address"));
				cdto.setAs_address_detail(rs.getString("as_address_detail"));
				cdto.setAs_reference(rs.getString("as_reference"));
				cdto.setAs_created_date(rs.getDate("as_created_date"));
				cdto.setAs_due_date(rs.getString("as_due_date"));
				cdto.setAs_user_id(rs.getString("as_user_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return cdto;
	}
	// 수리 내역 조회 메서드 - getHistory(as_id)

	// 수리 신청서 수정 메서드 - asEdit(cdto)
	public void asEdit(CenterDTO cdto) {
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성(UPDATE) & pstmt 객체
			sql = "UPDATE as_form SET as_model = ?, as_phone_num = ?, as_name = ?, as_due_date = ?, as_comment = ?,"
					+ "as_zip_code = ?, as_address = ?, as_address_detail = ?, as_reference = ?, as_edited_date = now() WHERE as_id = ?";
			pstmt = con.prepareStatement(sql);
			// 3-1. ??? 처리
			pstmt.setString(1, cdto.getAs_model());
			pstmt.setString(2, cdto.getAs_phone_num());
			pstmt.setString(3, cdto.getAs_name());
			pstmt.setString(4, cdto.getAs_due_date());
			pstmt.setString(5, cdto.getAs_comment());
			
			pstmt.setString(6, cdto.getAs_zip_code());
			pstmt.setString(7, cdto.getAs_address());
			pstmt.setString(8, cdto.getAs_address_detail());
			pstmt.setString(9, cdto.getAs_reference());

			pstmt.setInt(10, cdto.getAs_id());
			
			// 4. SQL 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	}
	// 수리 신청서 수정 메서드 - asEdit(cdto)

	// 수리 신청서 삭제 메서드 - deleteForm(cdto)
	public void deleteForm(CenterDTO cdto) {
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성(DELETE) & pstmt 객체 
			sql = "DELETE FROM as_form WHERE as_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cdto.getAs_id());
			// 4. SQL 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	// 수리 신청서 삭제 메서드 - deleteForm(cdto)

}
