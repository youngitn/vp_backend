package com.vp.tw.repository.t100;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vp.tw.entity.t100.Sfaa_t;
import com.vp.tw.model.vo.t100.WorkOrderInfo;
import com.vp.tw.model.vo.t100.WorkOrderInfoWithLocation;

public interface WorkOrderDao extends JpaRepository<Sfaa_t, String> {

	@Query(value = "SELECT sfbaseq,sfba005,imaal003,sfba013 ,sfba014,sfba016" +

			" FROM dsdata.SFAA_T st"
			+

			" LEFT JOIN  dsdata.sfba_T sfba ON sfba.sfbadocno = st.sfaadocno "
			+

			" LEFT JOIN  dsdata.IMAAl_T imaal  ON imaal001 = sfba.sfba005 "
			+

			" WHERE  sfaaent= :SFAAENT AND sfaadocno= :SFAADOCNO " +
			" ORDER BY sfbaseq ASC  "

			, nativeQuery = true)
	List<WorkOrderInfo> getWorkOrderListWithDetail(@Param("SFAAENT") int sfaaent,@Param("SFAADOCNO") String sfaadocno);
	
	/**
	 * 
	* @Title: getWorkOrderListWithDetailByLocation 
	* @Description: 根據庫位取得工單備料清單 
	* @param @param sfaaent
	* @param @param sfaadocno
	* @param @return    設定檔案 
	* @return List<WorkOrderInfoWithLocation>    返回型別 
	* @throws
	 */
	@Query(value =  
			"SELECT DISTINCT sfaadocno,sfbaseq,sfba005,imaal003,sfba013 ,sfba014,sfba016,inag005,inag004,inaa007,inag008 "
			+ "FROM dsdata.SFAA_T st LEFT JOIN  dsdata.sfba_T sfba ON sfba.sfbadocno = st.sfaadocno "
					+ " LEFT JOIN  dsdata.IMAAl_T imaal  ON imaal001 = sfba.sfba005 "
					+ " LEFT JOIN  dsdata.inag_t inag ON inag.INAG001 = sfba.sfba005 "
					+ " LEFT JOIN dsdata.inaa_t inaa ON inaa.INAA001 = :INAG004 "
					+ " WHERE  sfaaent= :SFAAENT AND sfaadocno in (:SFAADOCNOS)  "
					+ " AND inag.INAG004 NOT IN ('5051','5001') "
					+ " AND inag.INAG004 = :INAG004 "
					+ " AND inag.inag008 > 0 "
					+ " AND ((inaa.inaa007 = '1' and LENGTH(inag.inag005) = 7) or (inaa.inaa007='5' and inag.inag005 = ' ')) "
			+ " ORDER BY sfbaseq ASC"  , nativeQuery = true) 
	List<WorkOrderInfoWithLocation> getWorkOrderListWithDetailByLocation(@Param("SFAAENT") int sfaaent,@Param("SFAADOCNOS") List<String> sfaadocnos,@Param("INAG004") String inag004);
}
