package com.vp.tw.entity.t100;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.vp.tw.entity.t100.embeddedId.InaylPK;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
* @ClassName: Inayl 
* @Description: 集團庫位基本資料檔 多語言檔 
* @author ytc
* @date 2020年7月29日 上午9:33:02 
*
 */

@ApiModel(value="Inayl 集團庫位基本資料檔 多語言檔",description="集團庫位基本資料檔 多語言檔")
@Data
@Entity
@Table(name = "inayl_t", schema = "dsdata")
@NoArgsConstructor

//沒指定好FK的@ID資料會錯誤 
//例如:每個庫位名稱正常會有繁簡<兩筆>資料,
//如未指定更多能識別的FK,則將只抓<兩次>第一筆簡體資料.
@IdClass(InaylPK.class) 
public class Inayl implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "inaylent")
	private String inaylent;// 企業編號

	@Id
	@Column(name = "inayl001")
	private String inayl001;// 庫位名稱
	@Id
	@Column(name = "inayl002")
	private String inayl002;// 語言別

	@Column(name = "inayl003")
	private String inayl003;// 說明

	@Column(name = "Inayl004")
	private String inayl004;// 助記碼

	//@ManyToOne//(targetEntity=Inag.class,fetch=FetchType.EAGER)
	//@JoinColumn(name = "Inayl001", insertable = false, updatable = false) // 外鍵欄位名稱
	//private String inagObj;

}
