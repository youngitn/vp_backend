package com.vp.tw.repository.t100;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vp.tw.entity.t100.Isaf;

@Repository
public interface IsafDao extends JpaRepository<Isaf, String> {

}
