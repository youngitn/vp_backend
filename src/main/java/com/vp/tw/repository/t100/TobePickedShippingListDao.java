package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vp.tw.entity.t100.Isaf;
import com.vp.tw.model.vo.t100.TobePickedShippingInfo;

public interface TobePickedShippingListDao extends JpaRepository<Isaf, String> {

	String QUERY_STR = "SELECT DISTINCT " + "xmdhsite," // 營運據點
			+ "xmdh_t.xmdhent xmdhent, "// 企業編號
			+ "xmdh_t.xmdhdocno xmdhdocno, "// 單據編號
			+ "xmdh_t.xmdhseq xmdhseq, " // 項次
			+ "xmdg_t.xmdg005 xmdg005, " // 訂單客戶
			+ "xmdg_t.xmdg028 xmdg028, " // 預計出貨日期
			+ "xmdh_t.xmdh006 xmdh006, " // 料件編號
			+ "imaal_t.imaal003 imaal003, " // 品名
			+ "imaal_t.imaal004 imaal004, " // 規格
			+ "xmdh_t.xmdh007 xmdh007, " // 產品特徵
			+ "xmdh_t.xmdh016 xmdh016, " // 申請出通數量
			+ "xmdh_t.xmdh015 xmdh015," // 出貨單位
			+ "X.sfec001 sfec001," // 工單單號
			+ "X.sfaa050 sfaa050, " // 已入庫合格量
			+ "X.sfec012 sfec012, " // 庫位
			+ "X.sfec013 sfec013, " // 儲位
			+ "X.sfec014 sfec014, " // 批號
			+ "X.inag008 inag008, " // 帳面庫存數量
			+ "xmda_t.xmda033 xmda033, " // 客戶訂購單號
			+ "pmaal004 pmaal004 " // 交易對象簡稱
			+ " FROM dsdata.xmdh_t xmdh_t" + " LEFT JOIN dsdata.xmda_t xmda_t ON xmdaent = xmdhent"
			+ " AND xmdadocno = xmdh001" + " AND xmdasite = xmdhsite"
			+ " LEFT JOIN (SELECT * FROM dsdata.sfec_t, dsdata.inag_t,dsdata.sfaa_t WHERE sfecent = inagent  AND sfecsite = inagsite AND inag001 = sfec005 AND inag002 = sfec006 AND inag004 = sfec012 AND inag005 = sfec013 AND inag006 = sfec014"
			+ " AND sfaasite = sfecsite AND sfaaent = sfecent AND sfaadocno = sfec001) X "
			+ " ON xmdaent = X.sfecent  AND xmdhsite = X.sfecsite AND EXISTS (SELECT *  FROM dsdata.sfaa_t WHERE sfaaent = xmdhent AND sfec001 = sfaadocno AND ((sfaa006 = xmdh001 AND sfaa007 = xmdh002 )"
			+ " OR (sfaa022 = xmdh001 AND sfaa023 = xmdh002))) "
			+ " , dsdata.xmdg_t xmdg_t, dsdata.imaal_t imaal_t, dsdata.pmaal_t pmaal_t ";

	@Query(value = QUERY_STR
			+ " WHERE xmdgdocno = xmdhdocno" + " AND xmdgent = xmdhent " // 企業編號
			+ " AND imaalent = xmdhent " // 企業編號
			+ " AND imaal001 = xmdh006 " // 料件編號
			+ " AND imaal002 = 'zh_TW' " 
//			+ " AND xmdhent = 100 " 
			+ " AND pmaalent = xmdhent " // 企業編號
			+ " AND pmaal001 = xmdg005 " // 訂單客戶
			+ " AND pmaal002 = 'zh_TW' " 
			+ " AND xmdhsite = xmdgsite " // 後續參數化
//			+ " AND xmdhsite = 'TWVP' " 
//			+ " AND ROWNUM <= 150" // 數量限制要拿掉
			+ " AND xmdh056 - xmdh030 > 0 " 
			+ "	AND xmdg028 = to_date(:ESD,'yyyy-mm-dd')" // 應該是要加上日期
			+ " ORDER BY xmdhdocno , xmdhseq", nativeQuery = true)
	List<TobePickedShippingInfo> getTobePickedShippingList(@Param("ESD") String expShippingDate);

//	@Query(value = aaa)
//	List<TobePickedShippingInfo> getTobePickedShippingList(@Param("ESD") String expShippingDate);

}
