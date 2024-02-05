package com.project4.board.db;

import java.security.Timestamp;
import java.sql.Date;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

public class BoardDTO {
		
	    private int board_bno;
		private String board_subject;
		private String board_content;
		private String board_file;
		private String board_image;
		private Date board_regdate;
		private Date board_update;
		private int board_readcount;
		private float board_rating;
		private int board_rec;
		private boolean board_re_ref_check;
		private String board_pass;
		private String webBoard_file; 
		private String productname;
		
		
		private int re_ref;
		private int re_lev;
		private int re_seq;
		private BoardType board_category;
		private String id;
		public int getBoard_bno() {
			return board_bno;
		}
		public void setBoard_bno(int board_bno) {
			this.board_bno = board_bno;
		}
		public String getBoard_subject() {
			return board_subject;
		}
		public void setBoard_subject(String board_subject) {
			this.board_subject = board_subject;
		}
		public String getBoard_content() {
			return board_content;
		}
		public void setBoard_content(String board_content) {
			this.board_content = board_content;
		}
		public String getBoard_file() {
			return board_file;
		}
		public void setBoard_file(String board_file) {
			this.board_file = board_file;
		}
		public String getBoard_image() {
			return board_image;
		}
		public void setBoard_image(String board_image) {
			this.board_image = board_image;
		}
		public Date getBoard_regdate() {
			return board_regdate;
		}
		public void setBoard_regdate(Date board_regdate) {
			this.board_regdate = board_regdate;
		}
		public Date getBoard_update() {
			return board_update;
		}
		public void setBoard_update(Date board_update) {
			this.board_update = board_update;
		}
		public int getBoard_readcount() {
			return board_readcount;
		}
		public void setBoard_readcount(int board_readcount) {
			this.board_readcount = board_readcount;
		}
		public float getBoard_rating() {
			return board_rating;
		}
		public void setBoard_rating(float board_rating) {
			this.board_rating = board_rating;
		}
		public int getBoard_rec() {
			return board_rec;
		}
		public void setBoard_rec(int board_rec) {
			this.board_rec = board_rec;
		}
		public boolean isBoard_re_ref_check() {
			return board_re_ref_check;
		}
		public void setBoard_re_ref_check(boolean board_re_ref_check) {
			this.board_re_ref_check = board_re_ref_check;
		}
		public int getRe_ref() {
			return re_ref;
		}
		public void setRe_ref(int re_ref) {
			this.re_ref = re_ref;
		}
		public int getRe_lev() {
			return re_lev;
		}
		public void setRe_lev(int re_lev) {
			this.re_lev = re_lev;
		}
		public int getRe_seq() {
			return re_seq;
		}
		public void setRe_seq(int re_seq) {
			this.re_seq = re_seq;
		}
		public BoardType getBoard_category() {
			return board_category;
		}
		public void setBoard_category(BoardType board_category) {
			this.board_category = board_category;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getBoard_pass() {
			return board_pass;
		}
		public void setBoard_pass(String board_pass) {
			this.board_pass = board_pass;
		}
		
		public String getProductname() {
			return productname;
		}
		public void setProductname(String productname) {
			this.productname = productname;
		}
		public String getWebBoard_file() {
			return webBoard_file;
		}
		public void setWebBoard_file(String webBoard_file) {
			this.webBoard_file = webBoard_file;
		}
		
		@Override
		public String toString() {
			return "BoardDTO [board_bno=" + board_bno + ", board_subject=" + board_subject + ", board_content="
					+ board_content + ", board_file=" + board_file + ", board_image=" + board_image + ", board_regdate="
					+ board_regdate + ", board_update=" + board_update + ", board_readcount=" + board_readcount
					+ ", board_rating=" + board_rating + ", board_rec=" + board_rec + ", board_re_ref_check="
					+ board_re_ref_check + ", board_pass=" + board_pass + ", webBoard_file=" + webBoard_file
					+ ", productname=" + productname + ", re_ref=" + re_ref + ", re_lev=" + re_lev + ", re_seq="
					+ re_seq + ", board_category=" + board_category + ", id=" + id + "]";
		}
		
		
		
}
		