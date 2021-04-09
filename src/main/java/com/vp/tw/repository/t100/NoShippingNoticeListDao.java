package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vp.tw.entity.t100.Isaf;
import com.vp.tw.model.vo.t100.NoShippingNoticeInfo;

public interface NoShippingNoticeListDao extends JpaRepository<Isaf, String>{

	String QUERY_STR = "select\r\n"
			+ "DISTINCT xmda_t.xmdaent xmdaent\r\n"
			+ "	, xmdd_t.xmdddocno xmdddocno\r\n"
			+ "	, xmdd_t.xmddseq xmddseq\r\n"
			+ "	, xmdd_t.xmddseq1 xmddseq1\r\n"
			+ "	, xmdd_t.xmddseq2 xmddseq2\r\n"
			+ "	, xmda_t.xmda004 xmda004\r\n"
			+ "	, pmaal004 pmaal004	\r\n"
			+ "	, xmda_t.xmda033 xmda033\r\n"
			+ "	, xmdd_t.xmdd011 xmdd011\r\n"
			+ "	, xmdd_t.xmdd001 xmdd001\r\n"
			+ "	, imaal_t.imaal003 imaal003\r\n"
			+ "	, imaal_t.imaal004 imaal004\r\n"
			+ "	, xmdd_t.xmdd002 xmdd002\r\n"
			+ "	,inaml_t.inaml004 inaml004\r\n"
			+ "	, xmdd_t.xmdd006 xmdd006\r\n"
			+ "	, xmdd_t.xmdd004 xmdd004\r\n"
			+ "	, X.sfec001 sfec001\r\n"
			+ "	, X.sfaa050 sfaa050\r\n"
			+ "	, inag_t.inag004 inag004\r\n"
			+ "	, inag_t.inag005 inag005\r\n"
			+ "	, inag_t.inag006 inag006\r\n"
			+ "	, inag_t.inag008 inag008\r\n"
			+ "from dsdata.xmdd_t\r\n"
			+ "LEFT JOIN (\r\n"
			+ "	SELECT *\r\n"
			+ "	FROM dsdata.sfec_t\r\n"
			+ "		, dsdata.sfaa_t\r\n"
			+ "	WHERE sfaasite = sfecsite\r\n"
			+ "		AND sfaaent = sfecent\r\n"
			+ "		AND sfaadocno = sfec001\r\n"
			+ "     AND sfaa050 > 0\r\n"
			+ "	) X ON xmddent = X.sfecent\r\n"
			+ "	AND xmddsite = X.sfecsite\r\n"
			+ "	AND (\r\n"
			+ "		(\r\n"
			+ "			X.sfaa006 = xmdddocno\r\n"
			+ "			AND X.sfaa007 = xmddseq\r\n"
			+ "			AND X.sfaa008 = xmddseq1\r\n"
			+ "			AND X.sfaa063 = xmddseq2\r\n"
			+ "			)\r\n"
			+ "		OR (\r\n"
			+ "			X.sfaa022 = xmdddocno\r\n"
			+ "			AND X.sfaa023 = xmddseq\r\n"
			+ "			AND X.sfaa024 = xmddseq1\r\n"
			+ "			AND X.sfaa064 = xmddseq2\r\n"
			+ "			)\r\n"
			+ "		)\r\n"
			+ "LEFT JOIN dsdata.inag_t ON inagent = xmddent\r\n"
			+ "	AND inag006 = xmdddocno\r\n"
			+ "	AND inag001 = xmdd001\r\n"
			+ "	AND inag002 = nvl(xmdd002, ' ')\r\n"
			+ "LEFT JOIN dsdata.inaml_t ON inamlent=xmddent\r\n"
			+ "AND inaml001 = xmdd001\r\n"
			+ "AND inaml002= xmdd002\r\n"
			+ "AND inaml003 = 'zh_TW' 	\r\n"
			+ "	, dsdata.xmda_t\r\n"
			+ "	, dsdata.imaal_t\r\n"
			+ "	, dsdata.pmaal_t		\r\n"
			+ "where xmdd031=0  \r\n"
			+ "	AND xmdadocno = xmdddocno\r\n"
			+ "	AND xmdaent = :ENT \r\n"
			+ "	AND imaalent = :ENT \r\n"
			+ "	AND imaal001 = xmdd001\r\n"
			+ "	AND imaal002 = 'zh_TW'\r\n"
			+ "	AND xmdaent = 100\r\n"
			+ "	AND pmaalent = :ENT \r\n"
			+ "	AND pmaal001 = xmda004\r\n"
			+ "	AND pmaal002 = 'zh_TW'\r\n"
			+ "	AND xmdasite = :SITE \r\n"
			+ "	AND xmdasite = :SITE \r\n"
			+ "	AND xmdastus <> 'X'\r\n"
			+ "	and inag008 > 0\r\n"
			+ "	and xmdd006 > 0\r\n"
			+ "	AND sfaa050 > 0\r\n" 
			+ "	AND xmdd011 > TO_DATE( :XMDD011START ,'yyyy-mm-dd') \r\n"
			+ "	AND xmdd011 < TO_DATE( :XMDD011END ,'yyyy-mm-dd') \r\n"
			+ "	ORDER BY  XMDD011 DESC  ";
	
	
	@Query(value = QUERY_STR, nativeQuery = true)
	List<NoShippingNoticeInfo> getNoShippingNoticeInfoList(
			@Param("ENT") int ent,
			@Param("SITE") String site,
			@Param("XMDD011START") String xmdd011Start, // 約定交貨日期 開始
			@Param("XMDD011END") String xmdd011End // 約定交貨日期 結束
			);
}
