package com.vp.tw.repository.t100;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vp.tw.entity.t100.Inag;
import com.vp.tw.model.vo.t100.Gzcbl;

/**
 * 產線清單
 * 
 * @author USER
 *
 */

public interface GzcblDao extends JpaRepository<Inag, String> {
	
	@Query(value = "SELECT * FROM  dsdata.gzcbl_t WHERE  gzcbl001= :gzcbl001 ", nativeQuery = true)
	List<Gzcbl> findByGzcbl001(@Param("gzcbl001")int gzcbl001);

}
