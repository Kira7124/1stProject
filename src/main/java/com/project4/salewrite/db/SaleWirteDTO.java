package com.project4.salewrite.db;

import java.sql.Date;

public class SaleWirteDTO {

	private int sale_id;
	private String sale_user_id;
	private String sale_model;
	private int sale_expected_price;
	private String sale_name;
	private String sale_phone_num;
	private String sale_bank_name;
	private String sale_account_num;
	private String sale_owner_name;
	private String sale_zip_code;
	private String sale_address;
	private String sale_address_detail;
	private String sale_reference;
	private Date sale_created_date;
	private String sale_visit_date;
	private String sale_delivery_date;
	
	public int getSale_id() {
		return sale_id;
	}
	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}
	public String getSale_user_id() {
		return sale_user_id;
	}
	public void setSale_user_id(String sale_user_id) {
		this.sale_user_id = sale_user_id;
	}
	public String getSale_model() {
		return sale_model;
	}
	public void setSale_model(String sale_model) {
		this.sale_model = sale_model;
	}
	public int getSale_expected_price() {
		return sale_expected_price;
	}
	public void setSale_expected_price(int sale_expected_price) {
		this.sale_expected_price = sale_expected_price;
	}
	public String getSale_name() {
		return sale_name;
	}
	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}
	public String getSale_phone_num() {
		return sale_phone_num;
	}
	public void setSale_phone_num(String sale_phone_num) {
		this.sale_phone_num = sale_phone_num;
	}
	public String getSale_bank_name() {
		return sale_bank_name;
	}
	public void setSale_bank_name(String sale_bank_name) {
		this.sale_bank_name = sale_bank_name;
	}
	public String getSale_account_num() {
		return sale_account_num;
	}
	public void setSale_account_num(String sale_account_num) {
		this.sale_account_num = sale_account_num;
	}
	public String getSale_owner_name() {
		return sale_owner_name;
	}
	public void setSale_owner_name(String sale_owner_name) {
		this.sale_owner_name = sale_owner_name;
	}
	public String getSale_zip_code() {
		return sale_zip_code;
	}
	public void setSale_zip_code(String sale_zip_code) {
		this.sale_zip_code = sale_zip_code;
	}
	public String getSale_address() {
		return sale_address;
	}
	public void setSale_address(String sale_address) {
		this.sale_address = sale_address;
	}
	public String getSale_address_detail() {
		return sale_address_detail;
	}
	public void setSale_address_detail(String sale_address_detail) {
		this.sale_address_detail = sale_address_detail;
	}
	public String getSale_reference() {
		return sale_reference;
	}
	public void setSale_reference(String sale_reference) {
		this.sale_reference = sale_reference;
	}
	public Date getSale_created_date() {
		return sale_created_date;
	}
	public void setSale_created_date(Date sale_created_date) {
		this.sale_created_date = sale_created_date;
	}
	public String getSale_visit_date() {
		return sale_visit_date;
	}
	public void setSale_visit_date(String sale_visit_date) {
		this.sale_visit_date = sale_visit_date;
	}
	public String getSale_delivery_date() {
		return sale_delivery_date;
	}
	public void setSale_delivery_date(String sale_delivery_date) {
		this.sale_delivery_date = sale_delivery_date;
	}
	
	@Override
	public String toString() {
		return "SaleWirteDTO [sale_id=" + sale_id + ", sale_user_id=" + sale_user_id + ", sale_model=" + sale_model
				+ ", sale_expected_price=" + sale_expected_price + ", sale_name=" + sale_name + ", sale_phone_num="
				+ sale_phone_num + ", sale_bank_name=" + sale_bank_name + ", sale_account_num=" + sale_account_num
				+ ", sale_owner_name=" + sale_owner_name + ", sale_zip_code=" + sale_zip_code + ", sale_address="
				+ sale_address + ", sale_address_detail=" + sale_address_detail + ", sale_reference=" + sale_reference
				+ ", sale_created_date=" + sale_created_date + ", sale_visit_date=" + sale_visit_date
				+ ", sale_delivery_date=" + sale_delivery_date + "]";
	}
	
}
