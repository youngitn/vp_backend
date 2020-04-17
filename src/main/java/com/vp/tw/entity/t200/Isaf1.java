package com.vp.tw.entity.t200;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * T100 TABLE ISAF_T 銷項發票主檔
 * 
 * @author USER
 *
 */
@Table(name = "isaf_t", schema = "dsdata")
@NoArgsConstructor
@Entity
@Data
public class Isaf1 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "isafdocno", unique = true, nullable = false)
	@NonNull
	private String id;

	/**
	 * 發票日期
	 */
	@Column(name = "isaf014")
	private Date invoiceDate;

	/**
	 * 企業編碼
	 */
	@Column(name = "isafent")
	private String companyCode;

	/**
	 * 應收單號
	 */
	@Column(name = "isaf011")
	private String receivableNum;

	/**
	 * 發票號碼
	 */
	@Column(name = "isaf035")
	private String invoiceNum;

}
