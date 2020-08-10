package com.vp.tw.entity.t100.embeddedId;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
* @ClassName: InaylPK 
* @Description: 設定Inayl複合鍵的類別 
* @author ytc
* @date 2020年7月29日 上午9:28:49 
*
 */
@Data
@Embeddable
@NoArgsConstructor
public class InaylPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "inaylent")
	private String inaylent;// 企業編號

	@Column(name = "inayl001")
	private String inayl001;// 庫位名稱

	@Column(name = "inayl002")
	private String inayl002;// 語言別
}
