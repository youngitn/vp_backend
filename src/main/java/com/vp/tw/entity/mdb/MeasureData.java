package com.vp.tw.entity.mdb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = " Upload_MeasureData ", description = "庫存資訊")
@Data
@Entity
@Table(name = "Upload_MeasureData")
@NoArgsConstructor
public class MeasureData implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	private String SerialNo;//流水號

	//桶號
	private String Box_Code;
	
	//量測人員代碼
	private String Emp_code;
	
	//生產機台編號
	private String ProMachine_Code;
	
	//TIMI系統_登入之使用者代號
	private String userid;
	
	
	//腳本名稱
	
	private String template;

	//腳本量測資訊ID
	
	private String measureUID;
	
//	//量測項次
//	@Column(unique=true)
//	private int Order;
	
	//腳本更新日期
	private String UpdateTime;
	
	//執行量測時間
	private String MeasureTime;
	
	//腳本量測時間
	private String TIMESTAMP;
	
	//ControlItem之量測項目
	private String Name;
	
	//量測上限
	private String USL;
	
	//量測下限
	private String LSL;
	
	//量測值
	private String Value;
	
	//量測結果
	private String Quality;
	
	//機台ID
	private String deviceID;
	
	//產品代碼
	private String Pro_Code;
	
	//工令
	private String Lot_Code;
	
	//製程代碼
	private String Poc_Code;
	
	//客戶自訂代碼
	private String Cust_Code;
	
	//其他代碼
	private String Other_Code;
	
	//資料建立日期
	private String CreateDateTime;

}
