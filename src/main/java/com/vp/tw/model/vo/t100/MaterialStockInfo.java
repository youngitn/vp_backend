package com.vp.tw.model.vo.t100;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public interface MaterialStockInfo {

	String inagent(); // 企業編號

	String inagsite(); // 營運據點

	String inag001(); // 料件編號

	String inag002(); // 產品特徵

	String inag003(); // 庫存管理特徵

	String inag004(); // 庫位編號

	String inag005(); // 儲位編號

	String inag006(); // 批號

	String inag007(); // 庫存單位

	String inag008(); // 帳面庫存數量

	String inag009(); // 實際庫存數量

	String inag010(); // 庫存可用否

	String inag011(); // MRP可用否

	String inag012(); // 成本庫否

	String inag013(); // 揀貨優先序

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	Date inag014(); // 最近一次盤點日期

	String inag015(); // 最後異動日期

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	Date inag016(); // 呆滯日期

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	Date inag017(); // 第一次入庫日期

	String inag018(); // No Use

	String inag019(); // 留置否

	String inag020(); // 留置原因

	String inag021(); // 備置數量

	String inag022(); // No Use

	String inag023(); // Tag二進位碼

	String inag024(); // 參考單位

	String inag025(); // 參考數量

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	Date inag026(); // 最近一次檢驗日期

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	Date inag027(); // 下次檢驗日期

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	Date inag028(); // 留置日期

	String inag029(); // 留置人員

	String inag030(); // 留置部門

	String inag031(); // 留置單號

	String inag032(); // 基礎單位

	String inag033(); // 基礎單位數量

}
