package com.vp.tw.entity.t100;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = " xmdk 出貨單主檔", description = "出貨單主檔")
@Data
@Table(name = "xmdk_t", schema = "dsdata")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Xmdk implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "xmdkdocno")
	private String xmdkdocno;
	@Column(name = "xmdk005")
	private String xmdk005;
	@Column(name = "xmdk006")
	private String xmdk006;
	@Column(name = "xmdkstus")
	private String xmdkstus;
	@Column(name = "xmdkcrtdt")
	private String xmdkcrtdt;

}
