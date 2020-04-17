package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vp.tw.entity.t100.Isaf;
import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;

/**
 * 測試多資料來源,因為在設定檔 Db1Config.java有指定package所以spring知道那些是Repository or entity.
 * 所以@Repository可以省略.
 * @author USER
 *
 */
//@Repository
public interface DecathlonInvoiceInfoDao extends JpaRepository<Isaf, String> {

	
	/**
	 * 取得所有發票資料
	 * @return
	 */
	@Query(value = "select " 
			+ "a.ISAF014 as invoiceDate," 
			+ "c.xmda033 as orderNum," 
			+ "b.isag010 as productName, "
			+ "b.isag009 as productNum," 
			+ "b.isag004 as qty," 
			+ "b.isag101 as unitPrice," 
			+ "a.isaf035 as invoiceNum,"
			+ "a.isaf011 as receivableNum " 
			+ "from dsdata.isaf_t a, dsdata.isag_t b , dsdata.xmda_t c "
			+ "where a.isafdocno=b.isagdocno and c.xmdadocno=b.isag019", 
			nativeQuery = true)
	List<DecathlonInvoiceInfo> getAllInvoiceInfo();
	
//	/**
//	 * 取得所有分頁發票資料
//	 * @return
//	 */
//	@Query(value = "select " 
//			+ "a.ISAF014 as invoiceDate," 
//			+ "c.xmda033 as orderNum," 
//			+ "b.isag010 as productName, "
//			+ "b.isag009 as productNum," 
//			+ "b.isag004 as qty," 
//			+ "b.isag101 as unitPrice," 
//			+ "a.isaf035 as invoiceNum,"
//			+ "a.isaf011 as receivableNum " 
//			+ "from dsdata.isaf_t a, dsdata.isag_t b , dsdata.xmda_t c "
//			+ "where a.isafdocno=b.isagdocno and c.xmdadocno=b.isag019", 
//			nativeQuery = true)
//	Page<DecathlonInvoiceInfo> getAllInvoiceInfoPage();
	
	
	/**
	 * 取得所發票資料 BY 日期區間
	 * @return
	 */
	@Query(value = "select " 
			+ "to_char(b.ISAF014,'yyyy/mm/dd') as invoiceDate," 
			+ "c.xmda033 as orderNum," //訂單編號
			+ "a.isag010 as productName, " //品名
			+ "a.isag016 as cusProductNum," //客戶產編 
			+ "a.isag004 as qty," //數量
			+ "a.isag101 as unitPrice," //單價
			+ "b.isaf035 as receivableNum,"//帳款單號(應收單號)
			+ "b.isaf011 as invoiceNum " //發票號碼
			+ "from  dsdata.isag_t a "
			+ "left join  dsdata.isaf_t  b on a.isagdocno = b.isafdocno "
			+ "left join dsdata.xmda_t  c on a.isag019 = c.xmdadocno "
			+ "where b.isaf014 BETWEEN TO_DATE( :start , 'YYYY/MM/DD') and TO_DATE( :end , 'YYYY/MM/DD')"
			+ "and b.isafent='100' "
			+ "and b.isafsite='TWVP' "
			+ "and b.isafcomp='TWVP' "
			+ "and b.isaf003 = 'K0064'", 
			nativeQuery = true)
	List<DecathlonInvoiceInfo> getInvoiceInfoByDateRange(@Param("start") String start, @Param("end") String end);

}
