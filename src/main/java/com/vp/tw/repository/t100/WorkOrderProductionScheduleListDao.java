package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vp.tw.entity.t100.Isaf;
import com.vp.tw.model.vo.t100.WorkOrderProductionScheduleInfo;

/**
 * 工單生產進度表
 * 
 * @author USER
 *
 */
public interface WorkOrderProductionScheduleListDao extends JpaRepository<Isaf, String> {

	String fields = " " + "sfaastus," + "sfaadocno," + "sfaa002," + "ooag011," + "sfaa019," + "sfaa010," + "imaal003,"
			+ "sfaa012," + "sfaa013," + "sfaa049," + "sfaa050," + "sfaa051," + "sfaa071," + "sfaa056";
	String QUERY_STR = "SELECT DISTINCT " + fields + " FROM " + " DSDATA.SFAA_T sfaa "
			+ "LEFT JOIN DSDATA.IMAAL_T imaal " + "ON imaal.IMAAL001 = sfaa.SFAA010 AND imaal.IMAALENT = :ENT "
			+ "LEFT JOIN DSDATA.ooag_T ooag " + " ON ooag.ooag001 = sfaa.SFAA002 " + " AND ooag.ooagENT = :ENT "
			+ " WHERE " + " sfaa.SFAAENT = :ENT " + " AND sfaa.SFAASITE  = :SITE "
			+ " AND (sfaa020 >= to_date( :SFAA020START ,'yyyy-mm-dd') AND sfaa020 <= to_date( :SFAA020END ,'yyyy-mm-dd')) "
			+ " OFFSET :startRowNum ROWS FETCH NEXT :perPage ROWS ONLY ";

	@Query(value = QUERY_STR, nativeQuery = true)
	List<WorkOrderProductionScheduleInfo> getWorkOrderProductionScheduleList(@Param("ENT") int ent,
			@Param("SITE") String site, @Param("SFAA020START") String sfaa020Start, // 預計完工日 開始
			@Param("SFAA020END") String sfaa020End, // 預計完工日 結束
			@Param("perPage") int perPage, @Param("startRowNum") int startRowNum);

	@Query(value = "SELECT sfaadocno,oocq002,SUBSTR(oocq.oocq002,0,1) as oocqx,oocql004,sfaa020,sfaa012,sfaa013,sfaa019,sfaa050,gzcbl004,ooag011,imaal003,sfaa010,sfaa002,sfaastus,sfaa049,sfaa051,sfaa071  "

			+ " FROM dsdata.SFAA_T st "

			+ " LEFT JOIN  dsdata.OOCQ_T oocq ON oocq.oocq002 = st.SFAAUD003 AND  oocq.OOCQENT = :ENT AND oocq.OOCQ003 = 2006 "

			+ " LEFT JOIN  dsdata.OOCQl_T oocql ON oocql.oocql002 = oocq.oocq002 AND oocql.OOCQlENT = :ENT AND oocql.OOCQL001 =2006 "
			+ " LEFT JOIN  dsdata.gzcbl_t ON gzcbl002 LIKE :area%  AND gzcbl003= 'zh_TW' AND gzcbl001= '18009'"
			+ " LEFT JOIN DSDATA.IMAAL_T imaal " + " ON imaal.IMAAL001 = SFAA010 AND imaal.IMAALENT = :ENT "
			+ " LEFT JOIN DSDATA.ooag_T ooag " + " ON ooag.ooag001 = SFAA002 " + " AND ooag.ooagENT = :ENT "

			+ " WHERE sfaa019 >= to_date( :SFAA020START ,'yyyy-mm-dd') AND sfaa019 <= to_date( :SFAA020END ,'yyyy-mm-dd') AND sfaaent= :ENT  AND SFAASITE  = :SITE "
			+ " AND gzcbl002 = :area AND sfaa012 > sfaa050" + " AND sfaastus in ('A','C','F','Y') "
			+ " OFFSET :startRowNum ROWS FETCH NEXT :perPage ROWS ONLY ", nativeQuery = true)
	List<WorkOrderProductionScheduleInfo> getWorkOrderProductionScheduleListByArea(@Param("ENT") int ent,
			@Param("SITE") String site, @Param("SFAA020START") String sfaa020Start, // 預計完工日 開始
			@Param("SFAA020END") String sfaa020End, // 預計完工日 結束
			@Param("perPage") int perPage, @Param("startRowNum") int startRowNum, @Param("area") String area);

	@Query(value = "SELECT sfaadocno,oocq002,SUBSTR(oocq.oocq002,0,1) as oocqx,oocql004,sfaa020,sfaa012,sfaa013,sfaa019,sfaa050,gzcbl004,ooag011,imaal003,sfaa010,sfaa002,sfaastus,sfaa049,sfaa051,sfaa071  "

			+ " FROM dsdata.SFAA_T st "

			+ " LEFT JOIN  dsdata.OOCQ_T oocq ON oocq.oocq002 = st.SFAAUD003 AND  oocq.OOCQENT = :ENT AND oocq.OOCQ003 = 2006 "

			+ " LEFT JOIN  dsdata.OOCQl_T oocql ON oocql.oocql002 = oocq.oocq002 AND oocql.OOCQlENT = :ENT AND oocql.OOCQL001 =2006 "
			+ " LEFT JOIN  dsdata.gzcbl_t ON gzcbl002 LIKE :area%  AND gzcbl003= 'zh_TW' AND gzcbl001= '18009'"
			+ " LEFT JOIN DSDATA.IMAAL_T imaal " + " ON imaal.IMAAL001 = SFAA010 AND imaal.IMAALENT = :ENT "
			+ " LEFT JOIN DSDATA.ooag_T ooag " + " ON ooag.ooag001 = SFAA002 " + " AND ooag.ooagENT = :ENT "

			+ " WHERE sfaa019 >= to_date( :SFAA020START ,'yyyy-mm-dd') AND sfaa019 <= to_date( :SFAA020END ,'yyyy-mm-dd') AND sfaaent= :ENT  AND SFAASITE  = :SITE "
			+ " AND gzcbl002 = :area AND sfaa012 > sfaa050" + " AND sfaastus in ('A','C','F','Y') ", nativeQuery = true)

	List<WorkOrderProductionScheduleInfo> getWorkOrderProductionScheduleListByAreaTotalNumber(@Param("ENT") int ent,
			@Param("SITE") String site, @Param("SFAA020START") String sfaa020Start, // 預計完工日 開始
			@Param("SFAA020END") String sfaa020End, // 預計完工日 結束
			 @Param("area") String area);

}
