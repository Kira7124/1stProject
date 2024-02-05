package com.project4.salewrite.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SaleWriteDAO {

	// 공통 변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 공통) DB 연결하기
	private Connection getCon() throws Exception {

		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc");
		con = ds.getConnection();

		System.out.println(" DAO : 디비연결 성공!! ");
		System.out.println(" DAO : " + con);

		return con;
	}

	// 공통) 디비 자원해제
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

	// 신청서(방문) 작성 method - saleVisit()
	public void saleVisit(SaleWirteDTO dto) {
		int sale_id = 0;
		
		try {
			// 1.2 DB 연결
			con = getCon();
			
			// 3. SQL구문(SELECT) & pstmt 객체
			sql = "SELECT MAX(sale_id) FROM sale_form";
			pstmt = con.prepareStatement(sql);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				sale_id = rs.getInt(1) + 1;
			}
			System.out.println("DAO: sale_id: " + sale_id);
			
			// 3. SQL 구문(INSERT) & pstmt
			sql = "INSERT INTO sale_form(sale_id, sale_user_id, sale_model, sale_name, sale_expected_price, sale_phone_num, "
					+ "sale_bank_name, sale_account_num, sale_owner_name, "
					+ "sale_visit_date, sale_created_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			
			// 3-1. ???
			pstmt.setInt(1, sale_id);
			pstmt.setString(2, dto.getSale_user_id());
			pstmt.setString(3, dto.getSale_model());
			pstmt.setString(4, dto.getSale_name());
			pstmt.setInt(5, dto.getSale_expected_price());
			pstmt.setString(6, dto.getSale_phone_num());
			
			pstmt.setString(7, dto.getSale_bank_name());
			pstmt.setString(8, dto.getSale_account_num());
			pstmt.setString(9, dto.getSale_owner_name());
			
			pstmt.setString(10, dto.getSale_visit_date());
			
			// 4. SQL 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO: " + sale_id + "번 신청 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
	}
	//  신청서(방문) 작성 method - saleVisit()
	
	// 신청서(택배) 작성 method - saleDelivery()
	public void saleDelivery(SaleWirteDTO dto) {
		int sale_id = 0;
		
		try {
			// 1.2 DB 연결
			con = getCon();
			
			// 3. SQL구문(SELECT) & pstmt 객체
			sql = "SELECT MAX(sale_id) FROM sale_form";
			pstmt = con.prepareStatement(sql);
			
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()) {
				sale_id = rs.getInt(1) + 1;
			}
			System.out.println("DAO: sale_id: " + sale_id);
			
			// 3. SQL 구문(INSERT) & pstmt
			sql = "INSERT INTO sale_form(sale_id, sale_user_id, sale_model, sale_name, sale_expected_price, sale_phone_num, "
					+ "sale_bank_name, sale_account_num, sale_owner_name, "
					+ "sale_zip_code, sale_address, sale_address_detail, sale_reference, "
					+ "sale_delivery_date, sale_created_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			
			// 3-1. ???
			pstmt.setInt(1, sale_id);
			pstmt.setString(2, dto.getSale_user_id());
			pstmt.setString(3, dto.getSale_model());
			pstmt.setString(4, dto.getSale_name());
			pstmt.setInt(5, dto.getSale_expected_price());
			pstmt.setString(6, dto.getSale_phone_num());
			
			pstmt.setString(7, dto.getSale_bank_name());
			pstmt.setString(8, dto.getSale_account_num());
			pstmt.setString(9, dto.getSale_owner_name());
			
			pstmt.setString(10, dto.getSale_zip_code());
			pstmt.setString(11, dto.getSale_address());
			pstmt.setString(12, dto.getSale_address_detail());
			pstmt.setString(13, dto.getSale_reference());
			
			pstmt.setString(14, dto.getSale_delivery_date());
			
			// 4. SQL 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO: " + sale_id + "번 신청 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
	}
	//  신청서(택배) 작성 method - saleDelivery()

	// 신청 내역 조회 메서드 - getHistory(user_id)
	public ArrayList getHistory(String user_id) {
		ArrayList saleList = new ArrayList();
		SaleWirteDTO swdto = null;
		
		try {
			// 1. 2. DB 연결
			con = getCon();
			// 3. SQL 구문 작성(SELECT) & pstmt 객체
			sql = "SELECT * FROM sale_form WHERE sale_user_id = ? ORDER BY sale_created_date DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			// 4. SQL 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 (rs -> dto)
			while(rs.next()) {
				swdto = new SaleWirteDTO();
				
				swdto.setSale_id(rs.getInt("sale_id"));
				swdto.setSale_user_id(rs.getString("sale_user_id"));
				swdto.setSale_model(rs.getString("sale_model"));
				swdto.setSale_expected_price(rs.getInt("sale_expected_price"));
				swdto.setSale_name(rs.getString("sale_name"));
				swdto.setSale_phone_num(rs.getString("sale_phone_num"));
				swdto.setSale_bank_name(rs.getString("sale_bank_name"));
				swdto.setSale_account_num(rs.getString("sale_account_num"));
				swdto.setSale_owner_name(rs.getString("sale_owner_name"));
				swdto.setSale_zip_code(rs.getString("sale_zip_code"));
				swdto.setSale_address(rs.getString("sale_address"));
				swdto.setSale_address_detail(rs.getString("sale_address_detail"));
				swdto.setSale_reference(rs.getString("sale_reference"));
				swdto.setSale_created_date(rs.getDate("sale_created_date"));
				swdto.setSale_visit_date(rs.getString("sale_visit_date"));
				swdto.setSale_delivery_date(rs.getString("sale_delivery_date"));
				
				saleList.add(swdto);
			}
			System.out.println("DAO: 수리 신청 내역 조회 성공!");
			System.out.println("DAO: " + saleList.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		return saleList;
	}
	// 신청 내역 조회 메서드 - getHistory(user_id)

	// 판매 신청서 삭제 메서드 - deleteForm()
	public void deleteForm(SaleWirteDTO swdto) {
		try {
			// 1.2  DB 연결
			con = getCon();
			// 3. SQL 구문(DELETE) & pstmt 객체
			sql = "DELETE FROM sale_form WHERE sale_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, swdto.getSale_id());
			// 4. SQL 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	}
	// 판매 신청서 삭제 메서드 - deleteForm()
	
}
