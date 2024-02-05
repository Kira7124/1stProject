package com.project4.order.db;
import java.security.Timestamp;
import java.sql.Date;

import com.project4.board.db.BoardType;

public class OrderDTO {
	private int order_id;
	private String id;
	private String phone_num;
	private String zip_code;
	private String address;
	private String address_detail;
	private int price;
	private String status1;
	private String status2;
	private String status3;
	private String img;
	private String rentalStartDate;
	private String rentalEndDate;
	private String rentalDuration;
	private String name;
	private Date order_regdate;
	
	private String order_email;
	private OrderType order_category;
	
	


	public int getOrder_id() {
		return order_id;
	}





	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getPhone_num() {
		return phone_num;
	}





	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}





	public String getZip_code() {
		return zip_code;
	}





	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public String getAddress_detail() {
		return address_detail;
	}





	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}





	public int getPrice() {
		return price;
	}





	public void setPrice(int i) {
		this.price = i;
	}





	public String getStatus1() {
		return status1;
	}





	public void setStatus1(String status1) {
		this.status1 = status1;
	}





	public String getStatus2() {
		return status2;
	}





	public void setStatus2(String status2) {
		this.status2 = status2;
	}





	public String getStatus3() {
		return status3;
	}





	public void setStatus3(String status3) {
		this.status3 = status3;
	}





	public String getImg() {
		return img;
	}





	public void setImg(String img) {
		this.img = img;
	}





	public String getRentalStartDate() {
		return rentalStartDate;
	}





	public void setRentalStartDate(String rentalStartDate) {
		this.rentalStartDate = rentalStartDate;
	}





	public String getRentalEndDate() {
		return rentalEndDate;
	}





	public void setRentalEndDate(String rentalEndDate) {
		this.rentalEndDate = rentalEndDate;
	}





	public String getRentalDuration() {
		return rentalDuration;
	}





	public void setRentalDuration(String rentalDuration) {
		this.rentalDuration = rentalDuration;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	@Override
	public String toString() {
		return "OrderDTO [order_id=" + order_id + ", id=" + id + ", phone_num=" + phone_num + ", zip_code=" + zip_code
				+ ", address=" + address + ", address_detail=" + address_detail + ", price=" + price + ", status1="
				+ status1 + ", status2=" + status2 + ", status3=" + status3 + ", img=" + img + ", rentalStartDate="
				+ rentalStartDate + ", rentalEndDate=" + rentalEndDate + ", rentalDuration=" + rentalDuration
				+ ", name=" + name + ", order_regdate=" + order_regdate + ", order_email=" + order_email
				+ ", order_category=" + order_category + "]";
	}





	public Date getOrder_regdate() {
		return order_regdate;
	}





	public void setOrder_regdate(Date order_regdate) {
		this.order_regdate = order_regdate;
	}





	public OrderType getOrder_category() {
		return order_category;
	}
	public void setOrder_category(OrderType order_category) {
		this.order_category = order_category;
	}





	public String getOrder_email() {
		return order_email;
	}





	public void setOrder_email(String order_email) {
		this.order_email = order_email;
	}





	
	
	

}
