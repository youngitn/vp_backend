package com.vp.tw.repository.mdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vp.tw.entity.mdb.MeasureData;
@Repository
public interface MeasureDataDao extends JpaRepository<MeasureData, String>, JpaSpecificationExecutor<MeasureData> {

}


