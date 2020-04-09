package com.vp.tw.entity.test;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
	private String name;
	private int age;
	private Double payment;
	private Double bonus;
	private Date birthDate;
	private Employee superior;
}
