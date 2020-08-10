package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vp.tw.entity.t100.Isaf;
import com.vp.tw.model.vo.t100.MateriaPrepareInfo;

/**
 * 一般 待入庫
 * 
 * @author USER
 *
 */
@Repository
public interface MateriaPrepareListDao extends JpaRepository<Isaf, String> {

	
	String fields = 
			"DISTINCT "+
			"sfaadocno, " +
			"sfbaseq, " + // 項次
			"sfaa019, " + //預計開工日
			"imaal003, " + // 品名
			"imaal004, " + // 規格
			"imaf013, " + // 補給策略
			"sfba005, " + // BOM料號
			"sfba006, " + // 發料料號
			"sfba013, " + // 應發數量
			"sfba014, " + // 單位
			"sfba016, " + 
			"sfaastus "; // 已發數量
	String QUERY_STR = "SELECT " 
			+ fields
			+ " FROM dsdata.SFAA_T" 
			+ " LEFT JOIN dsdata.SFBA_T sfba ON sfaadocno = SFBADOCNO"
			+ " LEFT JOIN dsdata.IMAAL_T  ON imaal001 = sfba006 "
			+ " LEFT JOIN dsdata.IMAf_T  ON IMAf001 = sfba006 AND IMAFSITE = :SITE "
			+ " WHERE "
//			+ " sfaadocdt = TO_DATE('2020/06/17','YYYY/MM/DD') "
			+ " sfaa019 >= TO_DATE(:SFAA019,'YYYY/MM/DD') "
			+ " AND "
			+ " sfaastus = :SFAASTUS"
			+ " AND "
			+ " sfaasite = :SITE"
//			+ " GROUP BY sfaadocno,sfbaseq ,imaal003,imaal004,imaf013,sfba005,sfba006 ,sfba013 ,sfba014,sfba016,sfaastus" 
			+ " ORDER BY sfaa019,sfaadocno,sfbaseq"; 
			//+ " OFFSET :startRowNum ROWS FETCH NEXT :perPage ROWS ONLY ";

//	@Query(value = QUERY_STR, nativeQuery = true)
//	List<MateriaPrepareInfo> getMateriaList(
//			@Param("SFAASTUS") String stus,
//			@Param("SITE") String site,
//			@Param("SFAA019") String sfaa019,
//			@Param("perPage") int perPage,
//			@Param("startRowNum") int startRowNum);
	@Query(value = QUERY_STR, nativeQuery = true)
	List<MateriaPrepareInfo> getMateriaList(
			@Param("SFAASTUS") String stus,
			@Param("SITE") String site,
			@Param("SFAA019") String sfaa019
			//@Param("perPage") int perPage,
			//@Param("startRowNum") int startRowNum
			);

}
