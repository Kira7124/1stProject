package com.project4.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartDAO {

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
	
	
	// 장바구니 목록 가져오는 메서드 -getCartList(){
	public ArrayList getCartList(String u_id) {
		// 장바구니 목록을 저장하는 배열
		ArrayList cartList = new ArrayList();
	
		
		// 디비연결정보
					// 1. 드라이버 로드
					// 2. 디비 연결
		try {
			con = getCon();
			// 3. sql 작성(select) & pstmt 객체
//				sql = "select * from cart";
			sql = "select * from cart where u_id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, u_id);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리 
			while(rs.next()) {
				// 글하나의 정보 => CartDTO 저장 
				CartDTO cdto = new CartDTO();
				cdto.setC_id(rs.getInt("c_id"));
				cdto.setCapacity(rs.getString("capacity"));
				cdto.setColor(rs.getString("color"));
				cdto.setImage(rs.getString("image"));
				cdto.setModel(rs.getString("model"));
				cdto.setP_id(rs.getInt("p_id"));
				cdto.setPrice(rs.getInt("price"));
				cdto.setTitle(rs.getString("title"));
				cdto.setU_id(rs.getString("u_id"));
				cdto.setGrade(rs.getString("grade"));
				cdto.setName(rs.getString("name"));
				cdto.setStart_date(rs.getString("start_date"));
				cdto.setEnd_date(rs.getString("end_date"));
				cdto.setTotal_date(rs.getString("total_date"));
				
				// 장바구니 목록의 정보를 배열 한칸에 저장
				cartList.add(cdto);
			}
			
			System.out.println(" DAO : 장바구니 목록 조회성공! ");
			System.out.println(" DAO : " + cartList.size());
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally { 
			CloseDB();
		}
		
		return cartList;
		
	}
	// 장바구니 목록 가져오는 메서드 -getCartList()
		
		
	// 구매 or 대여 장바구니 추가 메서드(cdto) 
	public void insertCart(CartDTO cdto) {
	    int c_id = 1;

	    try {
	        // 1.2 디비 연결
	        con = getCon();

	        // c_id 계산하기
	        // 3. sql구문 (select) & pstmt 객체
//		        sql = "select max(c_id) from cart";
	        sql = "select max(c_id) from cart";
	        pstmt = con.prepareStatement(sql);
	        // 4.sql실행
	        rs = pstmt.executeQuery();
	        // 5.데이터처리
	        if (rs.next()) {
	            c_id = rs.getInt(1) + 1;
	        }

	        // title이 대여랑 같을 때
	        if ("대여".equals(cdto.getTitle())) {
	            sql = "insert into cart (c_id, p_id, u_id, price, image, model, color, capacity, title, start_date, end_date,name,total_date) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        } else { // title이 구매 일 때
	            sql = "insert into cart (c_id, p_id, u_id, price, image, model, color, capacity, title, grade, name) values (?,?,?,?,?,?,?,?,?,?,?)";
	        }
	        pstmt = con.prepareStatement(sql);

	        // 공통 필드 설정
	        pstmt.setInt(1, c_id);
	        pstmt.setInt(2, cdto.getP_id());
	        pstmt.setString(3, cdto.getU_id());
	        pstmt.setInt(4, cdto.getPrice());
	        pstmt.setString(5, cdto.getImage());
	        pstmt.setString(6, cdto.getModel());
	        pstmt.setString(7, cdto.getColor());
	        pstmt.setString(8, cdto.getCapacity());
	        pstmt.setString(9, cdto.getTitle());
	        pstmt.setString(10, cdto.getGrade());
	        pstmt.setString(11, cdto.getName());

	        // title에 따른 필드 설정
	        if ("대여".equals(cdto.getTitle())) {
	        pstmt.setInt(1, c_id);
	        pstmt.setInt(2, cdto.getP_id());
	        pstmt.setString(3, cdto.getU_id());
	        pstmt.setInt(4, cdto.getPrice());
	        pstmt.setString(5, cdto.getImage());
	        pstmt.setString(6, cdto.getModel());
	        pstmt.setString(7, cdto.getColor());
	        pstmt.setString(8, cdto.getCapacity());
	        pstmt.setString(9, cdto.getTitle());
	         pstmt.setString(10, cdto.getStart_date());
	         pstmt.setString(11, cdto.getEnd_date());
	         pstmt.setString(12, cdto.getName());
	         pstmt.setString(13, cdto.getTotal_date());

	        }

	        // 4. sql 실행
	        pstmt.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        CloseDB();
	    }
	}
	// 구매 or 대여 장바구니 추가 메서드(cdto) 
	public void deleteCart(CartDTO cdto) {
	
		try {
			//1.2 디비 연결
			con = getCon();
			// 3. sql 구문 & pstmt 객체 				
			sql = "delete from cart where c_id =?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cdto.getC_id());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			 System.out.println(" DAO : 장바구니 삭제 완료! " + cdto);
		} catch (Exception e) {
			e.printStackTrace();
		}finally { 
			CloseDB();
		}
		
	
	}
	// 구매 or 대여 삭제 메서드 deleteCart(CartDTO dto)


	
	
	
} // CartDAO
