package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vp.tw.entity.t100.Xmdk;

/**
 * 
 * @ClassName: ShipInfoDao
 * @Description: 出貨單資訊 條件設為取當日七點五十之後
 * @author ytc
 * @date 2020年12月18日 上午11:32:31
 *
 */
@Repository
public interface ShipInfoDao extends JpaRepository<Xmdk, String> {

	String SELECT = "SELECT XMDKDOCNO,XMDK005,XMDK006,XMDKSTUS,XMDKCRTDT "
			+ " FROM DSDATA.xmdk_t t "
			+ " WHERE instr(t.XMDKDOCNO,'VP8628') = 0 AND t.xmdk000 = 1 AND t.XMDKSTUS = 'S' AND  t.XMDKCRTDT >= TO_DATE(TO_CHAR(SYSDATE, 'YYYY-MM-DD') || ' 07:50','YYYY-MM-DD hh24:mi') AND t.XMDKENT = :ENT AND XMDKSITE = :SITE "
			+ " ORDER BY XMDKCRTDT DESC";

	@Query(value = SELECT , nativeQuery = true)
	List<Xmdk> getShipInfoList( @Param("ENT") String ent, @Param("SITE") String site);
}
