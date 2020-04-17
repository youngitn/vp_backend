package com.vp.tw.entity.t200;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * T100 TABLE ISAG_T 銷項發票來源明細檔
 * 
 * @author USER
 *
 */
@Data
@Entity
@Table(name = "isag_t", schema = "dsdata")
@NoArgsConstructor
public class Isag1 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "isagdocno")
	@NonNull
	private String id;

	/**
	 * 產品名稱
	 */
	@Column(name = "isag010")
	private String productName;

	/**
	 * 數量
	 */
	@Column(name = "isag004")
	private String qty;

	/**
	 * 產品編號
	 */
	@Column(name = "isag009")
	private String productNum;

	/**
	 * 參考單號
	 */
	@Column(name = "isag019")
	private String refNumber;

	/**
	 * 單價
	 */
	@Column(name = "isag101")
	private String unitPrice;

}
