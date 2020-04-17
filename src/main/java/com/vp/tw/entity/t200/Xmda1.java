package com.vp.tw.entity.t200;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * T100 TABLE XMDA_T 訂單單頭檔
 * @author USER
 *
 */
@Setter
@Getter
@Entity
@Table(name = "xmda_t", schema = "dsdata")
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Xmda1 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "xmdadocno", unique = true, nullable = false)
	@NonNull
	private String id;
	
	/**
	 * 訂單編號
	 */
	@Column(name = "xmda033")
	private String orderNum;

}
