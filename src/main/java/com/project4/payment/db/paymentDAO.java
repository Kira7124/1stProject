package com.project4.payment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class paymentDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 공통) 디비 연결하기(CP)
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
	
	public paymentDTO getPaymentInfo(String product_name) {
		paymentDTO dto = null;
		
		try {
			con = getCon();
			sql = "select*from test_payment where product_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product_name);
			rs = pstmt.executeQuery();
			dto = new paymentDTO();
			while(rs.next()) {
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_price(rs.getInt("product_price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		return dto;
	}
	
	
	
	
}
