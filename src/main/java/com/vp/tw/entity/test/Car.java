package com.vp.tw.entity.test;

import java.math.BigDecimal;

/**
 *
 * @author thecodeexamples
 */
public class Car {

	private String brand;
	private BigDecimal price;
	private String master;

	public Car(String brand, BigDecimal price, String master) {
		this.brand = brand;
		this.price = price;
		this.master = master;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

}
