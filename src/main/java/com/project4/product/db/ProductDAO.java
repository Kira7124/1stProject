package com.project4.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO {
	// 공통 변수 선언
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";
		
		
		// 공통) DB 연결하기
		private Connection getCon() throws Exception{
			
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
		
	
		
		// 상품 등록(글쓰기) 메서드() - insertProduct(ProductDTO)
		public void insertProduct(ProductDTO dto) {
			int product_id = 0;

			// * id 계산하기 => 1부터 1씩 증가
			try {
				// 1.2. 디비 연결
				con = getCon();
				// 3. sql 구문(select) & pstmt 객체
				sql = "select max(product_id) from product";
				pstmt = con.prepareStatement(sql);
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리
				if (rs.next()) {
					product_id = rs.getInt("max(product_id)") + 1;
				} else {
					System.out.println("없다");
				}

				System.out.println(" DAO : 상품 번호 : " + product_id);

				// 3. sql 구문(insert) & pstmt 객체
				sql = "insert into product(product_id, model, company, name, category, color, color2, color3, "
						+ "capacity, capacity2, capacity3, option1, option2, option3, option4, option5, option6, "
						+ "price, image, title, regdate, update_date) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?)";
				pstmt = con.prepareStatement(sql);

				// ???
				pstmt.setInt(1, product_id);
				pstmt.setString(2, dto.getModel());
				pstmt.setString(3, dto.getCompany());
				pstmt.setString(4, dto.getName());
				pstmt.setString(5, dto.getCategory().name());

				pstmt.setString(6, dto.getColor());
				pstmt.setString(7, dto.getColor2());
				pstmt.setString(8, dto.getColor3());
				pstmt.setString(9, dto.getCapacity());
				pstmt.setString(10, dto.getCapacity2());

				pstmt.setString(11, dto.getCapacity3());
				pstmt.setInt(12, dto.getOption1());
				pstmt.setInt(13, dto.getOption2());
				pstmt.setInt(14, dto.getOption3());
				pstmt.setInt(15, dto.getOption4());

				pstmt.setInt(16, dto.getOption5());
				pstmt.setInt(17, dto.getOption6());
				pstmt.setInt(18, dto.getPrice());
				pstmt.setString(19, dto.getImage());
				pstmt.setString(20, dto.getTitle());

				pstmt.setDate(21, dto.getUpdate_date());

				// 4. sql 실행
				pstmt.executeUpdate();

//				System.out.println(" DAO : " + product_id + "번 상품 등록 완료! ");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

		}

		// 상품 등록(글쓰기) 메서드() - insertProduct(ProductDTO)

		// 특정 상품의 정보를 가져오기 메서드() - getProduct(product_id)
		public ProductDTO getProduct(int product_id) {
			ProductDTO dto = null;

			try {
				// 1. 2. 디비 연결
				con = getCon();
				// 3. sql 구문(select) & pstmt 객체
				sql = "select * from product where product_id=?";
				pstmt = con.prepareStatement(sql);
				// ???
				pstmt.setInt(1, product_id);
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리 : rs에 있는 데이터를 dto로 옮기는 작업
				if (rs.next()) { // 데이터 존재할 때
					dto = new ProductDTO();

					dto.setProduct_id(rs.getInt("product_id"));
					dto.setModel(rs.getString("model"));
					dto.setCompany(rs.getString("company"));
					dto.setName(rs.getString("name"));
					dto.setCategory(rs.getString("category"));

					dto.setColor(rs.getString("color"));
					dto.setColor2(rs.getString("color2"));
					dto.setColor3(rs.getString("color3"));
					dto.setCapacity(rs.getString("capacity"));
					dto.setCapacity2(rs.getString("capacity2"));

					dto.setCapacity3(rs.getString("capacity3"));
					dto.setOption1(rs.getInt("option1"));
					dto.setOption2(rs.getInt("option2"));
					dto.setOption3(rs.getInt("option3"));
					dto.setOption4(rs.getInt("option4"));

					dto.setOption5(rs.getInt("option5"));
					dto.setOption6(rs.getInt("option6"));
					dto.setPrice(rs.getInt("price"));
					dto.setImage(rs.getString("image"));
					dto.setTitle(rs.getString("title"));

					dto.setRegdate(rs.getDate("regdate"));
					dto.setUpdate_date(rs.getDate("update_date"));
				}

				System.out.println(" DAO : 상품 정보 조회 성공! ");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return dto;
		}
		// 특정 상품의 정보를 가져오기 메서드() - getProduct(product_id)

		// 특정 상품 수정 메서드 - updateProduct(ProductDTO)
		public void updateProduct(ProductDTO dto) {
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 작성 & pstmt 객체
				sql = "update product set model=?, company=?, name=?, category=?, color=?, color2=?, color3=?, "
						+ "capacity=?, capacity2=?, capacity3=?, option1=?, option2=?, option3=?, option4=?, option5=?, option6=?, "
						+ "image=?, price=?, title=?, update_date=now() where product_id=?";
				pstmt = con.prepareStatement(sql);

				// ???
				pstmt.setString(1, dto.getModel());
				pstmt.setString(2, dto.getCompany());
				pstmt.setString(3, dto.getName());
				pstmt.setString(4, dto.getCategory().name());
				pstmt.setString(5, dto.getColor());

				pstmt.setString(6, dto.getColor2());
				pstmt.setString(7, dto.getColor3());
				pstmt.setString(8, dto.getCapacity());
				pstmt.setString(9, dto.getCapacity2());
				pstmt.setString(10, dto.getCapacity3());

				pstmt.setInt(11, dto.getOption1());
				pstmt.setInt(12, dto.getOption2());
				pstmt.setInt(13, dto.getOption3());
				pstmt.setInt(14, dto.getOption4());
				pstmt.setInt(15, dto.getOption5());

				pstmt.setInt(16, dto.getOption6());
				pstmt.setString(17, dto.getImage());
				pstmt.setInt(18, dto.getPrice());
				pstmt.setString(19, dto.getTitle());
				pstmt.setInt(20, dto.getProduct_id());

				// 4. sql 실행
				pstmt.executeUpdate();

				System.out.println(" DAO : 상품 : " + dto.getProduct_id() + "수정 완료! ");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

		}
		// 특정 상품 수정 메서드 - updateProduct(ProductDTO)

		// 특정 상품 삭제하는 메서드 - deleteProduct(ProductDTO pdto)
		public void deleteProduct(ProductDTO pdto) {
			try {
				// 1.2. 디비 연결
				con = getCon();

				// 3. sql 작성 & pstmt 객체
				sql = "delete from product where product_id=? and title=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pdto.getProduct_id()); // pdto 안에 product_id 담아왔기 때문
				pstmt.setString(2, pdto.getTitle());
				// 4. sql 실행
				pstmt.executeUpdate();

				System.out.println(" DAO : 상품 : " + pdto.getProduct_id() + "삭제 완료! ");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
		}
		// 특정 상품 삭제하는 메서드 - deleteProduct(ProductDTO pdto)

		// 특정(모델명, 카테고리) 목록 가져오는 메서드 - getBoard(model, category)
		public ArrayList getList(String model, String category, String name) {
			// 글정보를 저장하는 배열
			ArrayList productList = new ArrayList();
			ProductDTO dto = null;
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 구문 작성(select) & pstmt 객체
				sql = "select model, name, max(price) from product where category = ? group by model, name";
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, category);

				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리 (rs -> dto)
				while (rs.next()) {

					// 글 하나의 정보 => ProductDTO저장
					dto = new ProductDTO();

					dto.setPrice(rs.getInt("max(price)"));
					dto.setModel(rs.getString("model"));
					dto.setName(rs.getString("name"));

					// 글 하나의 정보를 배열의 한칸에 저장
					productList.add(dto);
				}
				System.out.println(" DAO : 목록 조회 성공! ");
				System.out.println(" DAO : " + productList.size());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return productList;
		}
		// 특정(모델명, 카테고리) 목록 가져오는 메서드 - getBoard(model, category)

		// 상품 전체 목록 가져오는 메서드 -getList()
		public ArrayList getList() {
			// 글정보를 저장하는 배열
			ArrayList productList = new ArrayList();
			ProductDTO dto = null;
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 구문 작성(select) & pstmt 객체
				sql = "select * from product";
				pstmt = con.prepareStatement(sql);

				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리 (rs -> dto)
				while (rs.next()) {

					// 글 하나의 정보 => ProductDTO저장
					dto = new ProductDTO();
					dto.setProduct_id(rs.getInt("product_id"));
					dto.setModel(rs.getString("model"));
					dto.setName(rs.getString("name"));
					dto.setCategory(rs.getString("category"));
					dto.setColor(rs.getString("color"));
					dto.setColor2(rs.getString("color2"));
					dto.setColor3(rs.getString("color3"));
					dto.setCapacity(rs.getString("capacity"));
					dto.setCapacity2(rs.getString("capacity2"));
					dto.setCapacity3(rs.getString("capacity3"));
					dto.setImage(rs.getString("image"));
					dto.setPrice(rs.getInt("price"));
					dto.setCompany(rs.getString("company"));

					// 글 하나의 정보를 배열의 한칸에 저장
					productList.add(dto);
				}
				System.out.println(" DAO : 목록 조회 성공! ");
				System.out.println(" DAO : " + productList.size());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return productList;
		}

		// 상품 전체 목록 가져오는 메서드 -getList()

		
		// 특정 글의 정보를 가져오기() - getOrder(id)
		public ProductDTO getOrder(int product_id) {
			ProductDTO dto = null;
			
			try {
				//1,2. DB 연결
				con = getCon();
				//3. sql 구문 작성 & pstmt 객체 생성
				sql="select * from product where product_id=?";
				pstmt = con.prepareStatement(sql);
				
				// ???
				pstmt.setInt(1, product_id);
				
				// sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터처리 ( rs => dto)
				if(rs.next()) {
					dto = new ProductDTO();
					dto.setProduct_id(rs.getInt("product_id"));
					dto.setModel(rs.getString("model"));
					dto.setName(rs.getString("name"));
					dto.setColor(rs.getString("color"));
					dto.setColor2(rs.getString("color2"));
					dto.setColor3(rs.getString("color3"));
					dto.setCapacity(rs.getString("capacity"));
					dto.setCapacity2(rs.getString("capacity2"));
					dto.setCapacity3(rs.getString("capacity3"));
					dto.setOption1(rs.getInt("option1"));
					dto.setOption2(rs.getInt("option2"));
					dto.setOption3(rs.getInt("option3"));
					dto.setOption4(rs.getInt("option4"));
					dto.setOption5(rs.getInt("option5"));
					dto.setOption6(rs.getInt("option6"));
					dto.setImage(rs.getString("image"));
					dto.setPrice(rs.getInt("price"));
					dto.setCompany(rs.getString("company"));
					dto.setTitle(rs.getString("title"));
					dto.setCategory(rs.getString("category"));
					dto.setRegdate(rs.getDate("regdate"));
					dto.setUpdate_date(rs.getDate("update_date"));
				}
				System.out.println(" DAO : 글정보 조회 성공");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return dto;
		}
		

		// 특정 글의 정보를 가져오기() - getLental(id)
		public ProductDTO getLental(int product_id) {
			ProductDTO dto = null;
			
			try {
				//1,2. DB 연결
				con = getCon();
				//3. sql 구문 작성 & pstmt 객체 생성
				sql="select * from product where product_id=?";
				pstmt = con.prepareStatement(sql);
				
				// ???
				pstmt.setInt(1, product_id);
				
				// sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터처리 ( rs => dto)
				if(rs.next()) {
					dto = new ProductDTO();
					dto.setProduct_id(rs.getInt("product_id"));
					dto.setModel(rs.getString("model"));
					dto.setName(rs.getString("name"));
					dto.setColor(rs.getString("color"));
					dto.setColor2(rs.getString("color2"));
					dto.setColor3(rs.getString("color3"));
					dto.setCapacity(rs.getString("capacity"));
					dto.setCapacity2(rs.getString("capacity2"));
					dto.setCapacity3(rs.getString("capacity3"));
					dto.setOption1(rs.getInt("option1"));
					dto.setOption2(rs.getInt("option2"));
					dto.setOption3(rs.getInt("option3"));
					dto.setOption4(rs.getInt("option4"));
					dto.setOption5(rs.getInt("option5"));
					dto.setOption6(rs.getInt("option6"));
					dto.setImage(rs.getString("image"));
					dto.setPrice(rs.getInt("price"));
					dto.setCompany(rs.getString("company"));
					dto.setTitle(rs.getString("title"));
					dto.setCategory(rs.getString("category"));
					dto.setRegdate(rs.getDate("regdate"));
					dto.setUpdate_date(rs.getDate("update_date"));
				}
				System.out.println(" DAO : 글정보 조회 성공");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return dto;
		}
		
		// 특정 글의 정보를 가져오기() - getLental(id)
				
				
		// 특정 글의 정보를 가져오기() - getSale(id)
		public ProductDTO getSale(int product_id) {
			ProductDTO dto = null;
			
			try {
				//1,2. DB 연결
				con = getCon();
				//3. sql 구문 작성 & pstmt 객체 생성
				sql="select * from product where product_id=?";
				pstmt = con.prepareStatement(sql);
				
				// ???
				pstmt.setInt(1, product_id);
				
				// sql 실행
				rs = pstmt.executeQuery();
				
				// 5. 데이터처리 ( rs => dto)
				if(rs.next()) {
					dto = new ProductDTO();
					dto.setProduct_id(rs.getInt("product_id"));
					dto.setModel(rs.getString("model"));
					dto.setName(rs.getString("name"));
					dto.setColor(rs.getString("color"));
					dto.setColor2(rs.getString("color2"));
					dto.setColor3(rs.getString("color3"));
					dto.setCapacity(rs.getString("capacity"));
					dto.setCapacity2(rs.getString("capacity2"));
					dto.setCapacity3(rs.getString("capacity3"));
					dto.setOption1(rs.getInt("option1"));
					dto.setOption2(rs.getInt("option2"));
					dto.setOption3(rs.getInt("option3"));
					dto.setOption4(rs.getInt("option4"));
					dto.setOption5(rs.getInt("option5"));
					dto.setOption6(rs.getInt("option6"));
					dto.setImage(rs.getString("image"));
					dto.setPrice(rs.getInt("price"));
					dto.setCompany(rs.getString("company"));
					dto.setTitle(rs.getString("title"));
					dto.setCategory(rs.getString("category"));
					dto.setRegdate(rs.getDate("regdate"));
					dto.setUpdate_date(rs.getDate("update_date"));
				}
				System.out.println(" DAO : 글정보 조회 성공");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return dto;
		}
		
		// 특정 글의 정보를 가져오기() - getSale(id)
		
		// 특정(검색용) 목록 가져오는 메서드 - searchProducts(serach)
		public ArrayList searchProducts(String search) {
			System.out.println(" DAO : searchProducts() 호출");
			ArrayList searchProducts = new ArrayList();
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 구문 작성(select) & pstmt 객체
				sql = "select * from product where name like ?";
				pstmt = con.prepareStatement(sql);
		
				pstmt.setString(1, "%"+search+"%");

				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리 (rs -> dto)
				while(rs.next()) {
					
					// 글 하나의 정보 => ProductDTO저장
					ProductDTO dto = new ProductDTO();
					dto.setProduct_id(rs.getInt("product_id"));
					dto.setModel(rs.getString("model"));
					dto.setName(rs.getString("name"));
					dto.setColor(rs.getString("color"));
					dto.setColor2(rs.getString("color2"));
					dto.setColor3(rs.getString("color3"));
					dto.setCapacity(rs.getString("capacity"));
					dto.setCapacity2(rs.getString("capacity2"));
					dto.setCapacity3(rs.getString("capacity3"));
					dto.setOption1(rs.getInt("option1"));
					dto.setOption2(rs.getInt("option2"));
					dto.setOption3(rs.getInt("option3"));
					dto.setOption4(rs.getInt("option4"));
					dto.setOption5(rs.getInt("option5"));
					dto.setOption6(rs.getInt("option6"));
					dto.setImage(rs.getString("image"));
					dto.setPrice(rs.getInt("price"));
					dto.setCompany(rs.getString("company"));
					dto.setTitle(rs.getString("title"));
					dto.setCategory(rs.getString("category"));
					dto.setRegdate(rs.getDate("regdate"));
					dto.setUpdate_date(rs.getDate("update_date"));
					searchProducts.add(dto);

				}
				System.out.println(searchProducts);


			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
			return searchProducts;
		}	
		// 특정(검색용) 목록 가져오는 메서드 - searchProducts(serach)
		
		
		// 글 개수 계산 메서드 - getProductCount()
		public int getProductCount(String title) {
			int result = 0;
			
			try {
				//1,2 DB 연결
				con = getCon();
				//3. sql 구문 작성 & pstmt 객체 생성
				sql = "select count(*) from product where title=?";
				pstmt = con.prepareStatement(sql);
				
				//???
				pstmt.setString(1, title);
				
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
				CloseDB();
			}
			
			
			return result;
		}
		
		
		// 글 개수 계산 메서드 - getProductCount()
		
		
				
		// 전체 상품 목록 조회 메서드 -getProductList()
		public ArrayList getProductList(String title) {
			ArrayList productList = new ArrayList();
			ProductDTO pd = null;

			try {
				con = getCon(); // 데이터 베이스 연결 메서드
				sql = "select * from product where title=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					pd = new ProductDTO();

					pd.setProduct_id(rs.getInt("product_id"));
					pd.setModel(rs.getString("model"));
					pd.setName(rs.getString("name"));
					pd.setColor(rs.getString("color"));
					pd.setColor2(rs.getString("color2"));
					pd.setColor3(rs.getString("color3"));
					pd.setCapacity(rs.getString("capacity"));
					pd.setCapacity2(rs.getString("capacity2"));
					pd.setCapacity3(rs.getString("capacity3"));
					pd.setOption1(rs.getInt("option1"));
					pd.setOption2(rs.getInt("option2"));
					pd.setOption3(rs.getInt("option3"));
					pd.setOption4(rs.getInt("option4"));
					pd.setOption5(rs.getInt("option5"));
					pd.setOption6(rs.getInt("option6"));
					pd.setImage(rs.getString("image"));
					pd.setPrice(rs.getInt("price"));
					pd.setCompany(rs.getString("company"));
					pd.setTitle(rs.getString("title"));
					pd.setCategory(rs.getString("category"));
					pd.setRegdate(rs.getDate("regdate"));
					pd.setUpdate_date(rs.getDate("update_date"));
					
					productList.add(pd);
				}

				System.out.println(" DAO : 전체 상품 조회 성공!!!!");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return productList;

		}
		// 전체 상품 목록 조회 메서드 -getProductList()

				
				
		// 카테고리별 상품 조회 메서드 - getProductList(title,category)
			public ArrayList getProductList(String title, String category) {
			    ArrayList productList = new ArrayList();
			    ProductDTO pd = null;
			    try {
			        con = getCon();
			        sql = "select * from product where title =? and category =?";
			        pstmt = con.prepareStatement(sql);
			        pstmt.setString(1, title);
			        pstmt.setString(2, category);
			        
			        rs = pstmt.executeQuery();

			        while (rs.next()) {
			            pd = new ProductDTO();
			            pd.setProduct_id(rs.getInt("product_id"));
						pd.setModel(rs.getString("model"));
						pd.setName(rs.getString("name"));
						pd.setColor(rs.getString("color"));
						pd.setColor2(rs.getString("color2"));
						pd.setColor3(rs.getString("color3"));
						pd.setCapacity(rs.getString("capacity"));
						pd.setCapacity2(rs.getString("capacity2"));
						pd.setCapacity3(rs.getString("capacity3"));
						pd.setOption1(rs.getInt("option1"));
						pd.setOption2(rs.getInt("option2"));
						pd.setOption3(rs.getInt("option3"));
						pd.setOption4(rs.getInt("option4"));
						pd.setOption5(rs.getInt("option5"));
						pd.setOption6(rs.getInt("option6"));
						pd.setImage(rs.getString("image"));
						pd.setPrice(rs.getInt("price"));
						pd.setCompany(rs.getString("company"));
						pd.setTitle(rs.getString("title"));
						pd.setCategory(rs.getString("category"));
						pd.setRegdate(rs.getDate("regdate"));
						pd.setUpdate_date(rs.getDate("update_date"));
						
			            productList.add(pd);
			        }
			        
			        System.out.println(" DAO : 카테고리별 조회 성공!!" );
			        System.out.println(" DAO : " + pd);
			    } catch (Exception e) {
			        e.printStackTrace();
			    } finally {
			        CloseDB();
			    }

			    return productList;
			}
			// 카테고리별 상품 조회 메서드 - getProductList(String category)
			
			// 카테고리별 상품 조회 메서드 - getProductList(title,company)
			public ArrayList getProductList1(String title, String company) {
			    ArrayList productList = new ArrayList();
			    ProductDTO pd = null;
			    try {
			        con = getCon();
			        sql = "select * from product where title =? and company =?";
			        pstmt = con.prepareStatement(sql);
			        pstmt.setString(1, title);
			        pstmt.setString(2, company);
			        
			        rs = pstmt.executeQuery();

			        while (rs.next()) {
			            pd = new ProductDTO();
			            pd.setProduct_id(rs.getInt("product_id"));
						pd.setModel(rs.getString("model"));
						pd.setName(rs.getString("name"));
						pd.setColor(rs.getString("color"));
						pd.setColor2(rs.getString("color2"));
						pd.setColor3(rs.getString("color3"));
						pd.setCapacity(rs.getString("capacity"));
						pd.setCapacity2(rs.getString("capacity2"));
						pd.setCapacity3(rs.getString("capacity3"));
						pd.setOption1(rs.getInt("option1"));
						pd.setOption2(rs.getInt("option2"));
						pd.setOption3(rs.getInt("option3"));
						pd.setOption4(rs.getInt("option4"));
						pd.setOption5(rs.getInt("option5"));
						pd.setOption6(rs.getInt("option6"));
						pd.setImage(rs.getString("image"));
						pd.setPrice(rs.getInt("price"));
						pd.setCompany(rs.getString("company"));
						pd.setTitle(rs.getString("title"));
						pd.setCategory(rs.getString("category"));
						pd.setRegdate(rs.getDate("regdate"));
						pd.setUpdate_date(rs.getDate("update_date"));
						
			            productList.add(pd);
			        }
			        
			        System.out.println(" DAO : 카테고리별 조회 성공!!" );
			        System.out.println(" DAO : " + pd);
			    } catch (Exception e) {
			        e.printStackTrace();
			    } finally {
			        CloseDB();
			    }

			    return productList;
			}

			// 카테고리별 상품 조회 메서드 - getProductList(title,company)
			
	// 카테고리별 상품 조회 메서드 - getProductList(title,company)
			
			public String getProductName(int productId) {
			    String productName = null;

			    try {
			        // 1. DB 연결
			        con = getCon();
			        // 2. SQL 쿼리 & PreparedStatement 객체
			        sql = "SELECT name FROM product WHERE product_id = ?";
			        pstmt = con.prepareStatement(sql);
			        // 3. 상품 ID 설정
			        pstmt.setInt(1, productId);
			        // 4. SQL 실행
			        rs = pstmt.executeQuery();
			        // 5. 데이터 처리
			        if (rs.next()) {
			            productName = rs.getString("name"); // 상품 이름 가져오기
			        }

			        System.out.println("DAO: 특정 상품의 이름 조회 성공!");

			    } catch (Exception e) {
			        e.printStackTrace();
			    } finally {
			        CloseDB(); // DB 연결 닫기
			    }

			    return productName;
			}
			
			public List<String> getProductNamesByCategory(String category) {
			    List<String> productNames = new ArrayList<>();

			    try {
			        // DB 연결
			        con = getCon();
			        // SQL 쿼리 & PreparedStatement 객체
			        sql = "SELECT name FROM product WHERE category = ?";
			        pstmt = con.prepareStatement(sql);
			        pstmt.setString(1, category);
			        // SQL 실행
			        rs = pstmt.executeQuery();

			        while (rs.next()) {
			            productNames.add(rs.getString("name")); // 해당 카테고리에 속하는 상품 이름들을 리스트에 추가
			        }

			        System.out.println("DAO: 카테고리에 속하는 상품 이름들 조회 성공!");

			    } catch (Exception e) {
			        e.printStackTrace();
			    } finally {
			        CloseDB();
			    }

			    return productNames;
			}
}
