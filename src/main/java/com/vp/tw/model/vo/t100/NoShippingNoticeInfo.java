package com.vp.tw.model.vo.t100;

import io.swagger.annotations.ApiModel;

@ApiModel(description="已完工入庫、無出貨通知單清單資訊")
public interface NoShippingNoticeInfo {
	String getXmdaent();

	String getXmdddocno();

	String getXmddseq();

	String getXmddseq1();

	String getXmddseq2();

	String getXmda004();

	String getPmaal004();

	String getXmda033();

	String getXmdd011();

	String getXmdd001();

	String getImaal003();

	String getImaal004();

	String getXmdd002();

	String getInaml004();

	String getXmdd006();

	String getXmdd004();

	String getSfec001();

	String getSfaa050();

	String getInag004();

	String getInag005();

	String getInag006();

	String getInag008();
}
