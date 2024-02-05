package com.project4.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale.Category;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.project4.member.db.MemberDTO;

public class BoardDAO {
	
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";

		
		private Connection getCon() throws Exception {

			Context initCTX = new InitialContext();
			DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/mvc");
			con = ds.getConnection();
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

		
		public int getBoardCount(String search) {
			int result = 0;

			try {
				
				con = getCon();

				
				sql = "select count(*) from board where board_subject like ? and board_category in ('NOTICE','중요')";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");

				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					result = rs.getInt(1);
					
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return result;
		}
		public int getBoardCount() {
			int result = 0;

			try {
				
				con = getCon();

				
				sql = "select count(*) from board where board_category in ('NOTICE','중요')";
				pstmt = con.prepareStatement(sql);
			
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					result = rs.getInt(1);
					
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return result;
		}
		public int getQNABoardCount() {
			int result = 0;

			try {
				
				con = getCon();

				
				sql = "select count(*) from board where board_category in ('QNA','QNA답변')";
				pstmt = con.prepareStatement(sql);
			
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					result = rs.getInt(1);
					
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return result;
		}
		public int getQNABoardCount(String search) {
			int result = 0;

			try {
				
				con = getCon();

				
				sql = "select count(*) from board where board_subject like ? and board_category in ('QNA','QNA답변')";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");

				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					result = rs.getInt(1);
					
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return result;
		}
		public int getReviewBoardCount() {
			int result = 0;

			try {
				
				con = getCon();

				
				sql = "select count(*) from board where board_category = 'REVIEW'";
				pstmt = con.prepareStatement(sql);
			
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					result = rs.getInt(1);
					
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return result;
		}
		public int getReviewBoardCount(String search) {
			int result = 0;

			try {
				
				con = getCon();

				
				sql = "select count(*) from board where board_subject like ? and board_category ='REVIEW'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");

				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					result = rs.getInt(1);
					
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return result;
		}
		public BoardDTO getBoard(int board_bno) {
			BoardDTO dto = null;
			
			
			
			try {
				con =getCon();
				sql="select*from board where board_bno=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, board_bno);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dto = new BoardDTO();
					
					
					dto.setBoard_bno(rs.getInt("board_bno"));
					dto.setBoard_subject(rs.getString("board_subject"));
					dto.setBoard_content(rs.getString("board_content"));
					dto.setBoard_file(rs.getString("board_file"));
					dto.setBoard_image(rs.getString("board_image"));
					dto.setBoard_regdate(rs.getDate("board_regdate"));
					dto.setBoard_update(rs.getDate("board_update"));
					dto.setBoard_readcount(rs.getInt("board_readcount"));
					dto.setBoard_rating(rs.getFloat("board_rating"));
					dto.setBoard_rec(rs.getInt("board_rec"));
					dto.setBoard_re_ref_check(rs.getBoolean("board_re_ref_check"));
					dto.setRe_ref(rs.getInt("re_ref"));
					dto.setRe_lev(rs.getInt("re_lev"));
					dto.setRe_seq(rs.getInt("re_seq"));
					dto.setBoard_category(BoardType.NOTICE);
					dto.setBoard_category(BoardType.QNA);
					dto.setBoard_category(BoardType.REVIEW);
					dto.setBoard_category(BoardType.중요);
					dto.setBoard_category(BoardType.QNA답변);
					dto.setId(rs.getString("id"));
					dto.setBoard_pass(rs.getString("board_pass"));
					dto.setProductname(rs.getString("productname"));
					
				}
				
			} catch (Exception e) {			
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return dto;
			
		}
		
		public ArrayList getAnnouncementBoardList(int startRow, int pageSize) {
			
			ArrayList Announcement = new ArrayList();
			try {
				
				con = getCon();				
				sql = "select * from board where board_category = 'NOTICE' order by re_ref desc,re_seq asc limit ?,?";
				pstmt = con.prepareStatement(sql);				
				pstmt.setInt(1, startRow - 1); // 시작행번호-1
				pstmt.setInt(2, pageSize); // 개수				
				rs = pstmt.executeQuery();				
				while (rs.next()) {			
					BoardDTO bb = new BoardDTO();

					bb.setBoard_bno(rs.getInt("board_bno"));
					bb.setBoard_subject(rs.getString("board_subject"));
					bb.setBoard_content(rs.getString("board_content"));
					bb.setBoard_file(rs.getString("board_file"));
					bb.setBoard_image(rs.getString("board_image"));
					bb.setBoard_regdate(rs.getDate("board_regdate"));
					bb.setBoard_update(rs.getDate("board_update"));
					bb.setBoard_readcount(rs.getInt("board_readcount"));
					bb.setBoard_rating(rs.getFloat("board_rating"));
					bb.setBoard_rec(rs.getInt("board_rec"));
					bb.setBoard_re_ref_check(rs.getBoolean("board_re_ref_check"));
					bb.setRe_ref(rs.getInt("re_ref"));
					bb.setRe_lev(rs.getInt("re_lev"));
					bb.setRe_seq(rs.getInt("re_seq"));
					bb.setBoard_category(BoardType.NOTICE);
					bb.setBoard_category(BoardType.QNA);
					bb.setBoard_category(BoardType.REVIEW);
					bb.setBoard_category(BoardType.중요);
					bb.setId(rs.getString("id"));
					bb.setBoard_pass(rs.getString("board_pass"));
					
					Announcement.add(bb);

				} 				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return Announcement;
		}
		public ArrayList getiAnnouncementBoardList(int startRow, int pageSize) {			
			ArrayList iAnnouncement = new ArrayList();
			try {
				
				con = getCon();
				
				sql = "select * from board where board_category = '중요' order by re_ref desc,re_seq asc limit ?,?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, startRow - 1); // 시작행번호-1
				pstmt.setInt(2, pageSize); // 개수
				
				rs = pstmt.executeQuery();				
				while (rs.next()) {					
					BoardDTO bb = new BoardDTO();

					bb.setBoard_bno(rs.getInt("board_bno"));
					bb.setBoard_subject(rs.getString("board_subject"));
					bb.setBoard_content(rs.getString("board_content"));
					bb.setBoard_file(rs.getString("board_file"));
					bb.setBoard_image(rs.getString("board_image"));
					bb.setBoard_regdate(rs.getDate("board_regdate"));
					bb.setBoard_update(rs.getDate("board_update"));
					bb.setBoard_readcount(rs.getInt("board_readcount"));
					bb.setBoard_rating(rs.getFloat("board_rating"));
					bb.setBoard_rec(rs.getInt("board_rec"));
					bb.setBoard_re_ref_check(rs.getBoolean("board_re_ref_check"));
					bb.setRe_ref(rs.getInt("re_ref"));
					bb.setRe_lev(rs.getInt("re_lev"));
					bb.setRe_seq(rs.getInt("re_seq"));
					bb.setBoard_category(BoardType.NOTICE);
					bb.setBoard_category(BoardType.중요);
					
					bb.setId(rs.getString("id"));
					bb.setBoard_pass(rs.getString("board_pass"));
					
					iAnnouncement.add(bb);

				} 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return iAnnouncement;
		}
		public ArrayList getAnnouncementBoardList(int startRow,int pageSize,String search) {
			ArrayList Announcement = new ArrayList();
			
			try {
				con = getCon();
				sql = "select * from board "
						+ "where board_subject like ? AND board_category = 'NOTICE' "
						+ "order by re_ref desc,re_seq asc "
						+ "limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow -1);
				pstmt.setInt(3, pageSize);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					// 글 하나의 정보 => BoardBean저장
					BoardDTO bb = new BoardDTO();

					bb.setBoard_bno(rs.getInt("board_bno"));
					bb.setBoard_subject(rs.getString("board_subject"));
					bb.setBoard_content(rs.getString("board_content"));
					bb.setBoard_file(rs.getString("board_file"));
					bb.setBoard_image(rs.getString("board_image"));
					bb.setBoard_regdate(rs.getDate("board_regdate"));
					bb.setBoard_update(rs.getDate("board_update"));
					bb.setBoard_readcount(rs.getInt("board_readcount"));
					bb.setBoard_rating(rs.getFloat("board_rating"));
					bb.setBoard_rec(rs.getInt("board_rec"));
					bb.setBoard_re_ref_check(rs.getBoolean("board_re_ref_check"));
					bb.setRe_ref(rs.getInt("re_ref"));
					bb.setRe_lev(rs.getInt("re_lev"));
					bb.setRe_seq(rs.getInt("re_seq"));
					
					bb.setBoard_category(BoardType.NOTICE);
					
					bb.setId(rs.getString("id"));
					bb.setBoard_pass(rs.getString("board_pass"));

					// 글 하나의 정보를 배열의 한칸에 저장
					Announcement.add(bb);
			}
				} catch (Exception e) {				
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return Announcement;
		}
		
		
		public void insertBoard(BoardDTO dto,MemberDTO mdto) {
			int board_bno = 0;
			
			try {
				con = getCon();
				sql = "select max(board_bno) from board";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					board_bno = rs.getInt(1)+1;
					
				}
				
				sql="insert into board(board_bno,id,board_subject,board_content,board_readcount,re_ref,re_lev,re_seq,board_regdate,board_file,board_image,board_pass,board_category,productname)"
						+ "values(?,?,?,?,?,?,?,?,now(),?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, board_bno);
				pstmt.setString(2, mdto.getId());
				pstmt.setString(3, dto.getBoard_subject());
				pstmt.setString(4, dto.getBoard_content());
				pstmt.setInt(5, 0);
				pstmt.setInt(6, board_bno); // 그룹번호는 글번호와 동일(일반글)
				pstmt.setInt(7, 0); // re_lev 0
				pstmt.setInt(8, 0); // re_seq 0
				pstmt.setString(9, dto.getBoard_file());
				pstmt.setString(10, dto.getBoard_image());
				pstmt.setString(11, dto.getBoard_pass());
				pstmt.setString(12, dto.getBoard_category().name());
				pstmt.setString(13, dto.getProductname());
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {			
				e.printStackTrace();
			}finally {
				CloseDB();
			}
		}
		
		public void updateReadcount(int board_bno) {
			try {
				con = getCon();
				sql = "update board set board_readcount = board_readcount+1 where board_bno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, board_bno);
				pstmt.executeUpdate();
		
			} catch (Exception e) {			
				e.printStackTrace();
			}finally {
				CloseDB();
			}
		}
		public int updateBoard(BoardDTO bdto) {
		    int result = -1; // -1 0 1

		    try {
		        // 1.2. 디비연결
		        con = getCon();

		        // 카테고리 확인
		        String category = getCategoryById(con, bdto.getBoard_bno()); // 해당하는 글의 카테고리를 가져옴

		        if (category.equals("NOTICE") || category.equals("중요") || category.equals("REVIEW")) {
		            String sql;
		            if (bdto.getBoard_file() != null) {
		                // 새로운 파일이 업로드되었을 때, 파일 경로를 업데이트합니다.
		                sql = "UPDATE board SET board_subject=?, board_content=?, board_file=? WHERE board_bno=?";
		                pstmt = con.prepareStatement(sql);
		                pstmt.setString(1, bdto.getBoard_subject());
		                pstmt.setString(2, bdto.getBoard_content());
		                pstmt.setString(3, bdto.getBoard_file());
		                pstmt.setInt(4, bdto.getBoard_bno());
		            } else {
		                // 이미지를 업로드하지 않은 경우, 기존 이미지 경로를 유지합니다.
		                sql = "UPDATE board SET board_subject=?, board_content=? WHERE board_bno=?";
		                pstmt = con.prepareStatement(sql);
		                pstmt.setString(1, bdto.getBoard_subject());
		                pstmt.setString(2, bdto.getBoard_content());
		                pstmt.setInt(3, bdto.getBoard_bno());
		            }

		            result = pstmt.executeUpdate();

		            System.out.println("DAO: 게시판 글 수정 완료!");
		            System.out.println("DAO: result: " + result);
		        } else if (category.equals("QNA") || category.equals("QNA답변")) {
		            // 카테고리가 QNA인 경우에만 비밀번호 검증 후 수정
		            // 여기에 비밀번호 검증 로직 추가
		            // 비밀번호가 맞으면 아래 코드 실행
		        	
		        	sql = "select board_pass from board where board_bno=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, bdto.getBoard_bno());
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						// 게시판 글있음
						if(bdto.getBoard_pass().equals(rs.getString("board_pass"))) {
					
							
							sql = "update board set board_subject=?,board_content=?, board_file=? where board_bno=?";
							pstmt = con.prepareStatement(sql);
							
							//???						
							pstmt.setString(1, bdto.getBoard_subject());
							pstmt.setString(2, bdto.getBoard_content());
							pstmt.setString(3, bdto.getBoard_file());
							pstmt.setInt(4, bdto.getBoard_bno());
							
							
							result = pstmt.executeUpdate();
							//=> SQL구문(insert,update,delete)이 몇줄 영향을 미치는가를 정수형태로 리턴
							System.out.println(" DAO : 게시판 글 수정 완료! ");
							System.out.println(" DAO : result : "+result);
							
							// 수정완료값 저장
							//result = 1;
							
						}else {
							//  게시판 글있음 - 비밀번호 다름 0
							result = 0;
						}
						
					}else {
						// 게시판 글없음  -1
						result = -1;
					}	}

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        CloseDB(); // 여기서는 연결을 닫지 않고, 메서드 호출 전에 이미 CloseDB()를 호출하는 부분에서 닫도록 수정되어야 합니다.
		    }
		    return result;
		}
		
		
		// 해당하는 글의 카테고리를 가져오는 메서드
		private String getCategoryById(Connection con, int board_bno) throws SQLException {
		    String category = "";
		    try {
		        sql = "select board_category from board where board_bno=?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, board_bno);

		        rs = pstmt.executeQuery();

		        if (rs.next()) {
		            category = rs.getString("board_category");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return category;
		}
		
		public String getImageFileName(int board_bno) {
		    String fileName = null;
		    try {
		       
		      // 해당하는 게시글 번호(board_bno)를 이용해 이미지 파일명을 가져오는 쿼리를 실행합니다.
		        // 이 부분은 실제 데이터베이스에 맞게끔 수정되어야 합니다.
		        con = getCon();
		        String sql = "SELECT board_file FROM board WHERE board_bno=?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, board_bno);
		        rs = pstmt.executeQuery();
		        if (rs.next()) {
		            fileName = rs.getString("board_file");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        CloseDB();
		    }
		    return fileName;
		}		
		
		public void deleteBoard(BoardDTO bdto) {
			
			try {
				con = getCon();
				sql = "delete from board where board_bno = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bdto.getBoard_bno());
				// 4. sql 실행
				pstmt.executeUpdate();
							
				 System.out.println(" DAO : 장바구니 삭제 완료! " + bdto);
				} catch (Exception e) {
					e.printStackTrace();
				}finally { 
					CloseDB();
				}
				
			
				}
		public ArrayList getQnaBoardList(int startRow, int pageSize) {
			// 글정보를 저장하는 배열
			ArrayList Qna = new ArrayList();
			try {				
				con = getCon();				
				sql = "select * from board where board_category IN ('QNA', 'QNA답변') order by re_ref desc,re_seq asc limit ?,?";
				pstmt = con.prepareStatement(sql);			
				pstmt.setInt(1, startRow - 1); // 시작행번호-1
				pstmt.setInt(2, pageSize); // 개수				
				rs = pstmt.executeQuery();			
				while (rs.next()) {
					// 글 하나의 정보 => BoardBean저장
					BoardDTO qbb = new BoardDTO();

					qbb.setBoard_bno(rs.getInt("board_bno"));
					qbb.setBoard_subject(rs.getString("board_subject"));
					qbb.setBoard_content(rs.getString("board_content"));
					qbb.setBoard_file(rs.getString("board_file"));
					qbb.setBoard_image(rs.getString("board_image"));
					qbb.setBoard_regdate(rs.getDate("board_regdate"));
					qbb.setBoard_update(rs.getDate("board_update"));
					qbb.setBoard_readcount(rs.getInt("board_readcount"));
					qbb.setBoard_rating(rs.getFloat("board_rating"));
					qbb.setBoard_rec(rs.getInt("board_rec"));
					qbb.setBoard_re_ref_check(rs.getBoolean("board_re_ref_check"));
					qbb.setRe_ref(rs.getInt("re_ref"));
					qbb.setRe_lev(rs.getInt("re_lev"));
					qbb.setRe_seq(rs.getInt("re_seq"));
					qbb.setProductname(rs.getString("productname"));
					
					qbb.setBoard_category(BoardType.QNA);
					
					qbb.setBoard_category(BoardType.QNA답변);
					qbb.setId(rs.getString("id"));
					qbb.setBoard_pass(rs.getString("board_pass"));

					// 글 하나의 정보를 배열의 한칸에 저장
					Qna.add(qbb);

				} // while
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return Qna;
		}
		public ArrayList getQnaBoardList(int startRow,int pageSize,String search) {
			ArrayList Qna = new ArrayList();
			
			try {
				con = getCon();
				sql = "select * from board "
						+ "where board_subject like ? AND board_category IN ('QNA', 'QNA답변') "
						+ "order by re_ref desc,re_seq asc "
						+ "limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow -1);
				pstmt.setInt(3, pageSize);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					// 글 하나의 정보 => BoardBean저장
					BoardDTO qbb = new BoardDTO();

					qbb.setBoard_bno(rs.getInt("board_bno"));
					qbb.setBoard_subject(rs.getString("board_subject"));
					qbb.setBoard_content(rs.getString("board_content"));
					qbb.setBoard_file(rs.getString("board_file"));
					qbb.setBoard_image(rs.getString("board_image"));
					qbb.setBoard_regdate(rs.getDate("board_regdate"));
					qbb.setBoard_update(rs.getDate("board_update"));
					qbb.setBoard_readcount(rs.getInt("board_readcount"));
					qbb.setBoard_rating(rs.getFloat("board_rating"));
					qbb.setBoard_rec(rs.getInt("board_rec"));
					qbb.setBoard_re_ref_check(rs.getBoolean("board_re_ref_check"));
					qbb.setRe_ref(rs.getInt("re_ref"));
					qbb.setRe_lev(rs.getInt("re_lev"));
					qbb.setRe_seq(rs.getInt("re_seq"));
					qbb.setProductname(rs.getString("productname"));
					
					
					qbb.setBoard_category(BoardType.QNA);
					
					qbb.setBoard_category(BoardType.QNA답변);
					
					qbb.setId(rs.getString("id"));
					qbb.setBoard_pass(rs.getString("board_pass"));

					// 글 하나의 정보를 배열의 한칸에 저장
					Qna.add(qbb);
			}
				} catch (Exception e) {				
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return Qna;
		}
		public ArrayList getReviewBoardList(int startRow, int pageSize) {
			// 글정보를 저장하는 배열
			ArrayList review = new ArrayList();
			try {				
				con = getCon();				
				sql = "select * from board where board_category = 'REVIEW' order by re_ref desc,re_seq asc limit ?,?";
				pstmt = con.prepareStatement(sql);				
				pstmt.setInt(1, startRow - 1); // 시작행번호-1
				pstmt.setInt(2, pageSize); // 개수				
				rs = pstmt.executeQuery();				
				while (rs.next()) {					
					BoardDTO rbb = new BoardDTO();

					rbb.setBoard_bno(rs.getInt("board_bno"));
					rbb.setBoard_subject(rs.getString("board_subject"));
					rbb.setBoard_content(rs.getString("board_content"));
					rbb.setBoard_file(rs.getString("board_file"));
					rbb.setBoard_image(rs.getString("board_image"));
					rbb.setBoard_regdate(rs.getDate("board_regdate"));
					rbb.setBoard_update(rs.getDate("board_update"));
					rbb.setBoard_readcount(rs.getInt("board_readcount"));
					rbb.setBoard_rating(rs.getFloat("board_rating"));
					rbb.setBoard_rec(rs.getInt("board_rec"));
					rbb.setBoard_re_ref_check(rs.getBoolean("board_re_ref_check"));
					rbb.setRe_ref(rs.getInt("re_ref"));
					rbb.setRe_lev(rs.getInt("re_lev"));
					rbb.setRe_seq(rs.getInt("re_seq"));
					rbb.setBoard_category(BoardType.NOTICE);
					rbb.setBoard_category(BoardType.QNA);
					rbb.setBoard_category(BoardType.REVIEW);
					rbb.setId(rs.getString("id"));
					rbb.setBoard_pass(rs.getString("board_pass"));

					// 글 하나의 정보를 배열의 한칸에 저장
					review.add(rbb);

				} // while

				System.out.println(" DAO : 게시판 글 목록 조회성공! ");
				System.out.println(" DAO : " + review.size());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}

			return review;
		}
		public ArrayList getReviewBoardList(int startRow,int pageSize,String search) {
			ArrayList review = new ArrayList();
			
			try {
				con = getCon();
				sql = "select * from board "
						+ "where board_subject like ? AND board_category = 'REVIEW' "
						+ "order by re_ref desc,re_seq asc "
						+ "limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow -1);
				pstmt.setInt(3, pageSize);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					// 글 하나의 정보 => BoardBean저장
					BoardDTO rbb = new BoardDTO();

					rbb.setBoard_bno(rs.getInt("board_bno"));
					rbb.setBoard_subject(rs.getString("board_subject"));
					rbb.setBoard_content(rs.getString("board_content"));
					rbb.setBoard_file(rs.getString("board_file"));
					rbb.setBoard_image(rs.getString("board_image"));
					rbb.setBoard_regdate(rs.getDate("board_regdate"));
					rbb.setBoard_update(rs.getDate("board_update"));
					rbb.setBoard_readcount(rs.getInt("board_readcount"));
					rbb.setBoard_rating(rs.getFloat("board_rating"));
					rbb.setBoard_rec(rs.getInt("board_rec"));
					rbb.setBoard_re_ref_check(rs.getBoolean("board_re_ref_check"));
					rbb.setRe_ref(rs.getInt("re_ref"));
					rbb.setRe_lev(rs.getInt("re_lev"));
					rbb.setRe_seq(rs.getInt("re_seq"));
					
					rbb.setBoard_category(BoardType.NOTICE);
					rbb.setBoard_category(BoardType.QNA);
					rbb.setBoard_category(BoardType.REVIEW);
					
					rbb.setId(rs.getString("id"));
					rbb.setBoard_pass(rs.getString("board_pass"));

					// 글 하나의 정보를 배열의 한칸에 저장
					review.add(rbb);
			}
				} catch (Exception e) {				
				e.printStackTrace();
			}finally {
				CloseDB();
			}
			
			return review;
		}
		
		public void reInsertBoard(BoardDTO dto,MemberDTO mdto) {
			int board_bno = 0;
			try {				
				con = getCon();				
				sql = "select max(board_bno) from board";
				pstmt = con.prepareStatement(sql);				
				rs = pstmt.executeQuery();				
				if(rs.next()) {
					//bno = rs.getInt(1) + 1;
					board_bno = rs.getInt("max(board_bno)") + 1;
				}
				
				System.out.println(" DAO : 답글번호 : "+board_bno);								
				sql = "update board set re_seq = re_seq + 1 "
						+ "where re_ref = ? and re_seq > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getRe_ref());
				pstmt.setInt(2, dto.getRe_seq());
				
				
				int tmp = pstmt.executeUpdate();
				
				if(tmp != 0) {
					
				}
				
				sql = "insert into board "
						+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
				pstmt = con.prepareStatement(sql);
				
				sql="insert into board(board_bno,id,board_subject,board_content,board_readcount,re_ref,re_lev,re_seq,board_regdate,board_file,board_image,board_pass,board_category)"
						+ "values(?,?,?,?,?,?,?,?,now(),?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, board_bno);
				pstmt.setString(2, mdto.getId());
				pstmt.setString(3, dto.getBoard_subject());
				pstmt.setString(4, dto.getBoard_content());
				pstmt.setInt(5, 0);				
				pstmt.setInt(6, dto.getRe_ref());  // 원글ref == 답글ref
				pstmt.setInt(7, dto.getRe_lev()+1);  // 원글lev + 1  
				pstmt.setInt(8, dto.getRe_seq()+1);  // 원글seq + 
				pstmt.setString(9, dto.getBoard_file());
				pstmt.setString(10, dto.getBoard_image());
				pstmt.setString(11, dto.getBoard_pass());
				pstmt.setString(12, dto.getBoard_category().name());
				
				
				//4. sql 실행
				pstmt.executeUpdate();		
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseDB();
			}
		}

	    
}