package com.vp.tw.repository.t200;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vp.tw.entity.t200.Isaf1;

/**
 * 測試多資料來源,因為在設定檔 Db2Config.java有指定package所以spring知道那些是Repository or entity.
 * 所以@Repository可以省略.
 * @author USER
 *
 */
//@Repository
public interface IsafDao1 extends JpaRepository<Isaf1, String> {

}
