package com.vp.tw.entity.t100.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.vp.tw.entity.t100.Inag;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InagSpecification implements Specification<Inag> {

	private SearchCriteria criteria;

	@Override
	public Predicate toPredicate(Root<Inag> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		String op = "";
		op = criteria.getOperation();
		
		//大於
		if (op.equalsIgnoreCase(">")) {
			return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} 
		//小於
		else if (op.equalsIgnoreCase("<")) {
			return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} 
		//like&非PK欄位的等於
		else if (op.equalsIgnoreCase(":")) {
			
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}

		} 
		//@作為PK專用的=符號
		else if (op.equalsIgnoreCase("@")) {
			//因為有多個PK所以使用嵌入物件當作id
			//下面的"id" 來自entity的id屬性名稱 此時指向作為嵌入PK的物件,再指定為參數帶的key
			return builder.equal(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} 
		
		else if (op.equalsIgnoreCase("!=")||op.equalsIgnoreCase("<>") ) {
			return builder.notEqual(root.<String>get(criteria.getKey()), criteria.getValue().toString());
		}
		return null;
	}
}
