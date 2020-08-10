package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vp.tw.entity.t100.Isaf;
import com.vp.tw.model.vo.t100.PendingStorageInfo;
/**
 * 一般 待入庫
 * @author USER
 *
 */
@Repository
public interface PendingStorageListDao extends JpaRepository<Isaf, String> {

	String QUERY_STR = "SELECT pmdsent," + 
			"       pmdssite," + 
			"       pmds000," + 			//單據性質
			"       pmdsdocno," + 			//單據單號
			"       pmdsdocdt," + 			//單據日期
			"       pmds002," + 			//申請人員
			"       t1.ooag011 pmds002_desc," + //全名
			"       pmds003," + 				//申請部門
			"       t2.ooefl003 pmds003_desc," + //說明
			"       pmds006," + 			//來源單號
			"       pmds007," + 			//採購供應商
			"       t3.pmaal004 pmds007_desc," + //交易對象簡稱
			"       pmds008," + 			//帳款供應商
			"       t4.pmaal004 pmds008_desc," + //交易對象簡稱
			"       pmds009," + 			//送貨供應商
			"       t5.pmaal004 pmds009_desc," + //交易對象簡稱
			"       pmds011," +				//採購性質
			"		t11.gzcbl004 pmds011_desc," + //說明
			"       pmdtseq," + 			//項次
			"       pmdt001," + 			//採購單號
			"       pmdt002," + 			//採購項次
			"       pmdt003," + 			//採購項序
			"       pmdt004," + 			//採購分批序
			"       pmdt006," + 			//料件編號
			"       imaal003," + 			//品名
			"       imaal004," + 			//規格
			"       pmdt007," + 			//產品特徵
			"       inaml004 pmdt007_desc," + //說明
			"       pmdt019," + 			//收貨/入庫單位
			"       t9.oocal003 pmdt019_desc," + //說明
			"       pmdt020," + 			//收貨/入庫數量
			"       pmdt023," + 			//計價單位
			"       t10.oocal003 pmdt023_desc," + //說明
			"       pmdt024," + 			//計價數量		
			"       pmdt053," + 			//允收數量
			"       pmdt054," + 			//已入庫量
			"       (pmdt053-pmdt054) pmdt053_pmdt054," + //待入庫數量
			"       imaa009," + 			//產品分類
			"       t6.rtaxl003 imaa009_desc," + //說明
			"       imaf141," + 			//採購分群
			"       t7.oocql004 imaf141_desc," + //說明
			"       imaa127," + 			//系列
			"       t8.oocql004 imaa127_desc" + //說明
			"  FROM dsdata.pmds_t" + 
			"  LEFT JOIN dsdata.pmdt_t" + 
			"    ON pmdsent = pmdtent" + 
			"   AND pmdsdocno = pmdtdocno" + 
			"  LEFT JOIN dsdata.imaal_t" + 
			"    ON imaalent = pmdtent" + 
			"   AND imaal001 = pmdt006" + 
			"   AND imaal002 = :DLANG" + 
			"  LEFT JOIN dsdata.imaa_t" + 
			"    ON imaaent = pmdtent" + 
			"   AND imaa001 = pmdt006 " + 
			"  LEFT JOIN dsdata.imaf_t" + 
			"    ON imafent = pmdtent" + 
			"   AND imafsite =pmdssite" + 
			"   AND imaf001 = pmdt006" + 
			"  LEFT JOIN dsdata.inaml_t" + 
			"    ON inamlent = pmdtent" + 
			"   AND inaml001 = pmdt006" + 
			"   AND inaml002 = pmdt007" + 
			"   AND inaml003 = :DLANG" + 
			"  LEFT JOIN dsdata.ooag_t t1" + 
			"    ON t1.ooagent = pmdsent" + 
			"   AND t1.ooag001 = pmds002" + 
			"  LEFT JOIN dsdata.ooefl_t t2" + 
			"    ON t2.ooeflent = pmdsent" + 
			"   AND t2.ooefl001 = pmds003" + 
			"   AND t2.ooefl002 = :DLANG" + 
			"  LEFT JOIN dsdata.pmaal_t t3" + 
			"    ON t3.pmaalent = pmdsent" + 
			"   AND t3.pmaal001 = pmds007" + 
			"   AND t3.pmaal002 = :DLANG" + 
			"  LEFT JOIN dsdata.pmaal_t t4" + 
			"    ON t4.pmaalent = pmdsent" + 
			"   AND t4.pmaal001 = pmds008" + 
			"   AND t4.pmaal002 = :DLANG" + 
			"  LEFT JOIN dsdata.pmaal_t t5" + 
			"    ON t5.pmaalent = pmdsent" + 
			"   AND t5.pmaal001 = pmds009" + 
			"   AND t5.pmaal002 = :DLANG" + 
			"  LEFT JOIN dsdata.rtaxl_t t6" + 
			"    ON t6.rtaxlent = pmdsent" + 
			"   AND t6.rtaxl001 = imaa009" + 
			"   AND t6.rtaxl002 = :DLANG" + 
			"  LEFT JOIN dsdata.oocql_t t7" + 
			"    ON t7.oocqlent = pmdsent" + 
			"   AND t7.oocql002 = imaf141" + 
			"   AND t7.oocql001 = '203'" + 
			"   AND t7.oocql003 = :DLANG" + 
			"  LEFT JOIN dsdata.oocql_t t8" + 
			"    ON t8.oocqlent = pmdsent" + 
			"   AND t8.oocql002 = imaa127" + 
			"   AND t8.oocql001 = '2003'" + 
			"   AND t8.oocql003 = :DLANG" + 
			"  LEFT JOIN dsdata.oocal_t t9" + 
			"    ON t9.oocalent = pmdsent" + 
			"   AND t9.oocal001 = pmdt019" + 
			"   AND t9.oocal002 = :DLANG" + 
			"  LEFT JOIN dsdata.oocal_t t10" + 
			"    ON t10.oocalent = pmdsent" + 
			"   AND t10.oocal001 = pmdt023" + 
			"   AND t10.oocal002 = :DLANG" + 
			"   LEFT JOIN dsdata.gzcbl_t t11" + 
			"    ON gzcbl001 = '2052'" + 
			"   AND gzcbl002 = pmds011" + 
			"   AND gzcbl003 = :DLANG" + 
			" WHERE pmdsent = :ENT" + 
			"   AND pmdssite = :SITE" + 
			"   AND pmds000 = :pmds000" + 
			"   AND pmdsstus = 'Y'" + 
			"   AND (pmdt053 - pmdt054) > 0 " + 
			" ORDER BY pmdsent," + 
			"          pmdssite," + 
			"          pmds000," + 
			"          pmdsdocno," + 
			"          pmdt001," + 
			"          pmdtseq," + 
			"          pmdt002," + 
			"          pmdt003," + 
			"          pmdt004"  +
			"";
			//" OFFSET :startRowNum ROWS FETCH NEXT :perPage ROWS ONLY ";

	@Query(value = QUERY_STR , nativeQuery = true)
	List<PendingStorageInfo> getPendingStorageList(@Param("DLANG") String dlang,
			@Param("ENT") int ent,
			@Param("SITE") String site,
			@Param("pmds000") int pmds000);
	
	
//	List<PendingStorageInfo> getPendingStorageList(@Param("DLANG") String dlang,
//			@Param("ENT") int ent,
//			@Param("SITE") String site,
//			@Param("pmds000") int pmds000,
//			@Param("perPage") int perPage, 
//			@Param("startRowNum") int startRowNum); 
			


}
