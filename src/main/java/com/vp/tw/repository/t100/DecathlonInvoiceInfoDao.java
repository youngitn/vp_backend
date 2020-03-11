package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vp.tw.entity.t100.Isaf;
import com.vp.tw.model.vo.t100.DecathlonInvoiceInfo;


@Repository
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
	
	@Query(value = "select " 
			+ "to_char(a.ISAF014,'yyyy/mm/dd') as invoiceDate," 
			+ "c.xmda033 as orderNum," //訂單編號
			+ "b.isag010 as productName, " //品名
			+ "b.isag009 as cusProductNum," //客戶產編 
			+ "b.isag004 as qty," //數量
			+ "b.isag101 as unitPrice," //單價
			+ "a.isaf035 as receivableNum,"//帳款單號(應收單號)
			+ "a.isaf011 as invoiceNum " //發票號碼
			+ "from dsdata.isaf_t a, dsdata.isag_t b , dsdata.xmda_t c "
			+ "where "
			+ "a.isafdocno=b.isagdocno "
			+ "and c.xmdadocno=b.isag019 "
			+ "and a.ISAF014  BETWEEN TO_DATE( :start , 'YYYY/MM/DD') "
			+ "and TO_DATE( :end , 'YYYY/MM/DD') "
			+ "and a.isafent='100' "
			+ "and a.isafsite='TWVP' "
			+ "and a.isafcomp='TWVP'", 
			nativeQuery = true)
	List<DecathlonInvoiceInfo> getInvoiceInfoByDateRange(@Param("start") String start, @Param("end") String end);

}
