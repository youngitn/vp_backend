package com.vp.tw.repository.t100;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vp.tw.entity.t100.Isaf;

/**
 * 測試多資料來源,因為在設定檔 Db1Config.java有指定package所以spring知道那些是Repository or entity.
 * 所以@Repository可以省略.
 * @author USER
 *
 */
//@Repository
public interface IsafDao extends JpaRepository<Isaf, String> {

}
