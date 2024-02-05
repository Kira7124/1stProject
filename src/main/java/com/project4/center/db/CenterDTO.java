package com.project4.center.db;

import java.sql.Date;

public class CenterDTO {

	private int as_id;
	private String as_model;
	private String as_phone_num;
	private String as_name;
	private String as_comment;
	private String as_zip_code;
	private String as_address;
	private String as_address_detail;
	private String as_reference;
	private Date as_created_date;
	private Date as_edited_date;
	private String as_due_date;
	private String as_user_id;
	
	public int getAs_id() {
		return as_id;
	}
	public void setAs_id(int as_id) {
		this.as_id = as_id;
	}
	public String getAs_model() {
		return as_model;
	}
	public void setAs_model(String as_model) {
		this.as_model = as_model;
	}
	public String getAs_phone_num() {
		return as_phone_num;
	}
	public void setAs_phone_num(String as_phone_num) {
		this.as_phone_num = as_phone_num;
	}
	public String getAs_name() {
		return as_name;
	}
	public void setAs_name(String as_name) {
		this.as_name = as_name;
	}
	public String getAs_comment() {
		return as_comment;
	}
	public void setAs_comment(String as_comment) {
		this.as_comment = as_comment;
	}
	public String getAs_zip_code() {
		return as_zip_code;
	}
	public void setAs_zip_code(String as_zip_code) {
		this.as_zip_code = as_zip_code;
	}
	public String getAs_address() {
		return as_address;
	}
	public void setAs_address(String as_address) {
		this.as_address = as_address;
	}
	public String getAs_address_detail() {
		return as_address_detail;
	}
	public void setAs_address_detail(String as_address_detail) {
		this.as_address_detail = as_address_detail;
	}
	public String getAs_reference() {
		return as_reference;
	}
	public void setAs_reference(String as_reference) {
		this.as_reference = as_reference;
	}
	public Date getAs_created_date() {
		return as_created_date;
	}
	public void setAs_created_date(Date as_created_date) {
		this.as_created_date = as_created_date;
	}
	public Date getAs_edited_date() {
		return as_edited_date;
	}
	public void setAs_edited_date(Date as_edited_date) {
		this.as_edited_date = as_edited_date;
	}
	public String getAs_due_date() {
		return as_due_date;
	}
	public void setAs_due_date(String as_due_date) {
		this.as_due_date = as_due_date;
	}
	public String getAs_user_id() {
		return as_user_id;
	}
	public void setAs_user_id(String as_user_id) {
		this.as_user_id = as_user_id;
	}
	
	@Override
	public String toString() {
		return "CenterDTO [as_id=" + as_id + ", as_model=" + as_model + ", as_phone_num=" + as_phone_num + ", as_name="
				+ as_name + ", as_comment=" + as_comment + ", as_zip_code=" + as_zip_code + ", as_address=" + as_address
				+ ", as_address_detail=" + as_address_detail + ", as_reference=" + as_reference + ", as_created_date="
				+ as_created_date + ", as_edited_date=" + as_edited_date + ", as_due_date=" + as_due_date
				+ ", as_user_id=" + as_user_id + "]";
	}
	
}
