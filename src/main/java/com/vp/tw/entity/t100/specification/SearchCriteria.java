package com.vp.tw.entity.t100.specification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
	private String key;
	private String operation;
	private Object value;
	public boolean isOrPredicate(String isOr) {
		// TODO Auto-generated method stub
		if (isOr.equals("or")) {
			return true;
		}
		return false;
	}
}
