package com.project4.product.db;

import java.sql.Date;

public class ProductDTO {
	/* 상품 주문 번호 */
	private int product_id;

	/* 모델명 */
	private String model;

	/* 주문자명 */
	private String name;

	/* 카테고리 스마트폰, 태블릿, 웨어러블 */
	private ProductType category;

	/* 색상1 */
	private String color;

	/* 색상2 */
	private String color2;

	/* 색상3 */
	private String color3;

	/* 용량 */
	private String capacity;

	/* 용량2 */
	private String capacity2;

	/* 용량3 */
	private String capacity3;
	
	/*옵션1*/
	private int option1;
	
	/*옵션2*/
	private int option2;
	
	/*옵션3*/
	private int option3;
	
	/*옵션4*/
	private int option4;
	
	/*옵션5*/
	private int option5;
	
	/*옵션6*/
	private int option6;
	
	/* 사진 이미지 */
	private String image;

	/* 가격 */
	private int price;

	/* 제조사 삼성, 애플, 엘지 */
	private String company;
	
	/* 구매, 대여, 판매*/
	private String title;
	
	/*등록 일자*/
	private Date regdate;
	
	/*수정 일자*/
	private Date update_date;


	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ProductType getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = ProductType.valueOf(category);
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getColor2() {
		return color2;
	}


	public void setColor2(String color2) {
		this.color2 = color2;
	}


	public String getColor3() {
		return color3;
	}


	public void setColor3(String color3) {
		this.color3 = color3;
	}


	public String getCapacity() {
		return capacity;
	}


	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	public String getCapacity2() {
		return capacity2;
	}


	public void setCapacity2(String capacity2) {
		this.capacity2 = capacity2;
	}


	public String getCapacity3() {
		return capacity3;
	}


	public void setCapacity3(String capacity3) {
		this.capacity3 = capacity3;
	}




	public int getOption1() {
		return option1;
	}


	public void setOption1(int option1) {
		this.option1 = option1;
	}


	public int getOption2() {
		return option2;
	}


	public void setOption2(int option2) {
		this.option2 = option2;
	}


	public int getOption3() {
		return option3;
	}


	public void setOption3(int option3) {
		this.option3 = option3;
	}


	public int getOption4() {
		return option4;
	}


	public void setOption4(int option4) {
		this.option4 = option4;
	}


	public int getOption5() {
		return option5;
	}


	public void setOption5(int option5) {
		this.option5 = option5;
	}


	public int getOption6() {
		return option6;
	}


	public void setOption6(int option6) {
		this.option6 = option6;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}


	public Date getUpdate_date() {
		return update_date;
	}


	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}


	@Override
	public String toString() {
		return "ProductDTO [product_id=" + product_id + ", model=" + model + ", name=" + name + ", category=" + category
				+ ", color=" + color + ", color2=" + color2 + ", color3=" + color3 + ", capacity=" + capacity
				+ ", capacity2=" + capacity2 + ", capacity3=" + capacity3 + ", option1=" + option1 + ", option2="
				+ option2 + ", option3=" + option3 + ", option4=" + option4 + ", option5=" + option5 + ", option6="
				+ option6 + ", image=" + image + ", price=" + price + ", company=" + company + ", title=" + title
				+ ", regdate=" + regdate + ", update_date=" + update_date + "]";
	}


	

	


	
}
