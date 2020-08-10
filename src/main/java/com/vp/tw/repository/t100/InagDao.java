package com.vp.tw.repository.t100;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vp.tw.entity.t100.Inag;

@Repository
public interface InagDao extends JpaRepository<Inag, String>, JpaSpecificationExecutor<Inag> {

}
