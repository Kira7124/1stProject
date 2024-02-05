package com.project4.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale.Category;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.project4.board.db.BoardDTO;
import com.project4.member.db.MemberDTO;

public class OrderDAO {
	// 공통 변수 선언
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
		
		public void insertOrder(OrderDTO dto,MemberDTO mdto) {
			int order_id = 0;
			
			try {
				con = getCon();
				sql = "select max(order_id) from order1";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					order_id = rs.getInt("max(order_id)")+1;
					
				}
				
				sql="insert into order1(order_id,id,phone_num,zip_code,address,address_detail,price,status1,status2,status3,img,rentalStartDate,rentalEndDate,rentalDuration,name,order_regdate,order_email,order_category)"
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, order_id);
				pstmt.setString(2, mdto.getId());
				pstmt.setString(3, dto.getPhone_num());
				pstmt.setString(4, dto.getZip_code());
				pstmt.setString(5, dto.getAddress());
				pstmt.setString(6, dto.getAddress_detail());
				pstmt.setInt(7, dto.getPrice());
				pstmt.setString(8, dto.getStatus1());
				pstmt.setString(9, dto.getStatus2());
				pstmt.setString(10, dto.getStatus3());
				pstmt.setString(11, dto.getImg());
				pstmt.setString(12, dto.getRentalStartDate());
				pstmt.setString(13, dto.getRentalEndDate());
				pstmt.setString(14, dto.getRentalDuration());
				pstmt.setString(15, dto.getName());
				pstmt.setString(16, dto.getOrder_email());
				pstmt.setString(17, dto.getOrder_category().name());
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {			
				e.printStackTrace();
			}finally {
				CloseDB();
			}
		}
		
		public ArrayList getPurchaseHistory(String user_id) {
			ArrayList orderList = new ArrayList();
			OrderDTO odto = null;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				// 3. SQL 구문 작성 & pstmt 객체
				sql= "SELECT * FROM order1 WHERE id = ? AND order_category = 'PURCHASE' ORDER BY order_regdate DESC";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				// 4. SQL 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리 (rs -> dto)
				while(rs.next()) {
					odto = new OrderDTO();
					
					odto.setOrder_id(rs.getInt("order_id"));
					odto.setId(rs.getString("id"));
					odto.setPhone_num(rs.getString("phone_num"));
					odto.setZip_code(rs.getString("zip_code"));
					odto.setAddress(rs.getString("address"));
					odto.setAddress_detail(rs.getString("address_detail"));
					odto.setPrice(rs.getInt("price"));
					odto.setStatus1(rs.getString("status1"));
					odto.setStatus2(rs.getString("status2"));
					odto.setStatus3(rs.getString("status3"));
					odto.setImg(rs.getNString("img"));
					odto.setRentalStartDate(rs.getString("rentalStartDate"));
					odto.setRentalEndDate(rs.getString("rentalEndDate"));
					odto.setRentalDuration(rs.getString("rentalDuration"));
					odto.setName(rs.getString("name"));
					odto.setOrder_regdate(rs.getDate("order_regdate"));
					odto.setOrder_category(OrderType.PURCHASE);
					odto.setOrder_email(rs.getString("order_email"));
					
					orderList.add(odto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return orderList;
		}

		public ArrayList getRentalHistory(String user_id) {
			ArrayList orderList = new ArrayList();
			OrderDTO odto = null;
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				// 3. SQL 구문 작성 & pstmt 객체
				sql= "SELECT * FROM order1 WHERE id = ? AND order_category = 'RENTAL' ORDER BY rentalStartDate DESC";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				// 4. SQL 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리 (rs -> dto)
				while(rs.next()) {
					odto = new OrderDTO();
					
					odto.setOrder_id(rs.getInt("order_id"));
					odto.setId(rs.getString("id"));
					odto.setPhone_num(rs.getString("phone_num"));
					odto.setZip_code(rs.getString("zip_code"));
					odto.setAddress(rs.getString("address"));
					odto.setAddress_detail(rs.getString("address_detail"));
					odto.setPrice(rs.getInt("price"));
					odto.setStatus1(rs.getString("status1"));
					odto.setStatus2(rs.getString("status2"));
					odto.setStatus3(rs.getString("status3"));
					odto.setImg(rs.getNString("img"));
					odto.setRentalStartDate(rs.getString("rentalStartDate"));
					odto.setRentalEndDate(rs.getString("rentalEndDate"));
					odto.setRentalDuration(rs.getString("rentalDuration"));
					odto.setName(rs.getString("name"));
					odto.setOrder_regdate(rs.getDate("order_regdate"));
					odto.setOrder_category(OrderType.RENTAL);
					odto.setOrder_email(rs.getString("order_email"));
					
					orderList.add(odto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return orderList;
		}
		
		// 주문 취소 메서드 - deleteOrder(odto)
		public void deleteOrder(OrderDTO odto) {
			
			try {
				// 1. 2. DB 연결
				con = getCon();
				// 3. SQL 구문 (DELETE) & pstmt 객체 
				sql = "DELETE FROM order1 WHERE order_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, odto.getOrder_id());
				// 4. SQL 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
		}
		// 주문 취소 메서드 - deleteOrder(odto)
		
}



		
		