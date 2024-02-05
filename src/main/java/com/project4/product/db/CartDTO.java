package com.project4.product.db;


public class CartDTO {
	
	/* 장바구니 고유 번호*/ 
	private int c_id;
	
	/* 상품 고유 번호 */ 
	private int p_id;
	
	/* 사용자 아이디 */
	private String u_id;
	
	/* 가격 */
	private int price;

	/* 이미지 */
	private String image;
	
	/* 모델넘버 */
	private String model;
	
	/* 색상 */
	private String color;
	
	/* 용량 */
	private String capacity;

	
	/* 대여 구매 판매 여부 확인 */
	private String title;
	
	/* 모델 이름 */ 
	private String name;
	
	/*등급*/
	private String grade;
	
	/*대여 시작일*/
	private String start_date;
	
	/*대여 종료일*/
	private String end_date;
	
	/*총 대여일수 */
	private String total_date;

	
	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	


	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	public String getTotal_date() {
		return total_date;
	}

	public void setTotal_date(String total_date) {
		this.total_date = total_date;
	}

	@Override
	public String toString() {
		return "CartDTO [c_id=" + c_id + ", p_id=" + p_id + ", u_id=" + u_id + ", price=" + price + ", image=" + image
				+ ", model=" + model + ", color=" + color + ", capacity=" + capacity + ", title=" + title + ", name="
				+ name + ", grade=" + grade + ", start_date=" + start_date + ", end_date=" + end_date + ", total_date="
				+ total_date + "]";
	}

	

	
	
	
	
	
}