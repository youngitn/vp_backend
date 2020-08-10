package com.vp.tw.entity.t100.embeddedId;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
* @ClassName: InagPK 
* @Description: 設定Inag複合鍵的類別  
* @author ytc
* @date 2020年7月29日 上午9:30:16 
*
 */
@Data
@Embeddable
@NoArgsConstructor
public class InagPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 做關聯之後Id要很注意,關係到資料正確性
	 * 原DB table中沒用到的key可以先省略
	 */
//	@Column(name = "inagent")
//	private String inagent;// 企業編號


	@Column(name = "inag004")
	private String inag004;// 庫位編號

	

	

}
