package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vp.tw.entity.t100.Isaf;
import com.vp.tw.model.vo.t100.TobePickedShippingInfo;

public interface TobePickedShippingListDao extends JpaRepository<Isaf, String> {

	String QUERY_STR = "SELECT DISTINCT " + "a.xmdhsite," // 營運據點
			+ "a.xmdhent, "// 企業編號
			+ "(select ooag011 from dsdata.ooag_t where ooag001 = c.xmdg002 AND ooagent = 100) xmdg002, "// 業務人員
			+ "a.xmdh001 xmdh001, "// 訂單單號
			+ "a.xmdhseq xmdhseq, " // 項次
			+ "c.xmdg005 xmdg005, " // 訂單客戶
			+ "c.xmdg028 xmdg028, " // 預計出貨日期
	 		+ "c.xmdgdocdt xmdgdocdt,"   //出通單日期
			+ "a.xmdh006 xmdh006, " // 料件編號
			+ "d.imaal003 imaal003, " // 品名
			+ "d.imaal004 imaal004, " // 規格
			+ "a.xmdh007 xmdh007, " // 產品特徵
			+ "a.xmdhdocno xmdhdocno," //出通單號
			+ "a.xmdh016 xmdh016, " // 申請出通數量
			+ "a.xmdh017 xmdh017," // 實際出通數量"
			+ "a.xmdh030 xmdh030," // 已轉出貨量"
			
			+ "a.xmdh015 xmdh015," // 出貨單位
			+ "X.sfec001 sfec001," // 工單單號
			+ "X.sfaa050, " // 已入庫合格量
			+ "X.sfec012, " // 庫位
			+ "X.sfec013, " // 儲位
			+ "X.sfec014, " // 批號
			+ "X.inag008, " // 帳面庫存數量
			+ "b.xmda033, " // 客戶訂購單號
			+ "e.pmaal004 " // 交易對象簡稱
			+ " FROM dsdata.xmdh_t a" 
			+ " LEFT JOIN dsdata.xmda_t b ON xmdaent = xmdhent"
			+ " AND xmdadocno = xmdh001" + " AND xmdasite = xmdhsite"
			+ " LEFT JOIN (SELECT DISTINCT sfecent,sfecsite,sfec001,inag008,sfec014,sfec013,sfec012,sfaa050,sfaa023,sfaa022,sfaa006,sfaa007,sfaadocno "
			+ " FROM dsdata.sfec_t, dsdata.inag_t,dsdata.sfaa_t "
			+ " WHERE sfecent = inagent  "
			+ " AND sfecsite = inagsite "
			+ " AND inag001 = sfec005 "
			+ " AND inag002 = sfec006 "
			+ " AND inag004 = sfec012 "
			+ " AND inag005 = sfec013 "
			+ " AND inag006 = sfec014"
			+ " AND sfaasite = sfecsite "
			+ " AND sfaaent = sfecent "
			+ " AND sfaadocno = sfec001) X "
			+ " ON xmdaent = X.sfecent  "
			+ " AND xmdhsite = X.sfecsite "
			/********20200619優化SQL*************/
			+ "AND ((X.sfaa006 = xmdh001 AND X.sfaa007 = xmdh002 ) OR (X.sfaa022 = xmdh001 AND X.sfaa023 = xmdh002))"
//			+ " AND EXISTS (SELECT sfaa007 FROM dsdata.sfaa_t WHERE sfaaent = xmdhent AND sfec001 = sfaadocno AND ((sfaa006 = xmdh001 AND sfaa007 = xmdh002 )"
//			+ " OR (sfaa022 = xmdh001 AND sfaa023 = xmdh002))) "
			/**********************************/
			+ " , dsdata.xmdg_t c, dsdata.imaal_t d, dsdata.pmaal_t e ";

	@Query(value = QUERY_STR + " WHERE xmdgdocno = xmdhdocno" + " AND xmdgent = xmdhent " // 企業編號
			+ " AND imaalent = xmdhent " // 企業編號
			+ " AND imaal001 = xmdh006 " // 料件編號
			+ " AND imaal002 = 'zh_TW' "
//			+ " AND xmdhent = 100 " 
			+ " AND pmaalent = xmdhent " // 企業編號
			+ " AND pmaal001 = xmdg005 " // 訂單客戶
			+ " AND pmaal002 = 'zh_TW' "  // 後續參數化
			+ " AND xmdhsite = 'TWVP' " 
//			+ " AND ROWNUM <= 550" // 數量限制要拿掉
			+ " AND xmdh056 > xmdh030 " 
			+ "	AND (xmdg028 >= to_date(:ESSD,'yyyy-mm-dd') AND xmdg028 <= to_date(:ESED,'yyyy-mm-dd'))" // 應該是要加上日期
			+ " ORDER BY xmdhdocno , xmdhseq ,xmdg028"
//			+ " OFFSET :startRowNum ROWS FETCH NEXT :perPage ROWS ONLY ", nativeQuery = true)
			, nativeQuery = true)
	List<TobePickedShippingInfo> getTobePickedShippingList(@Param("ESSD") String expShippingStartDate,
			@Param("ESED") String expShippingEndDate);
//			@Param("perPage") int perPage, 
//			@Param("startRowNum") int startRowNum);
	
	@Query(value = QUERY_STR + " WHERE xmdgdocno = xmdhdocno" + " AND xmdgent = 100 " // 企業編號
			+ " AND imaalent = xmdhent " // 企業編號
			+ " AND imaal001 = xmdh006 " // 料件編號
			+ " AND imaal002 = 'zh_TW' "
			+ " AND pmaalent = xmdhent " // 企業編號
			+ " AND pmaal001 = xmdg005 " // 訂單客戶
			+ " AND pmaal002 = 'zh_TW' "  // 後續參數化
			+ " AND xmdhsite = 'TWVP' " 
			+ " AND xmdh056 > xmdh030 "
			+ " AND xmdgdocno = :XMDGDOCNO "
			+ " ORDER BY xmdhdocno , xmdhseq ,xmdg028"
			, nativeQuery = true)
	List<TobePickedShippingInfo> getTobePickedShippingListByXmdgdocno(@Param("XMDGDOCNO") String expShippingStartDate);

}
