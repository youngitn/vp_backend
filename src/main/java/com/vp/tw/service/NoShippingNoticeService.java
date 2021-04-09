package com.vp.tw.service;

import java.util.List;

import com.vp.tw.model.vo.t100.NoShippingNoticeInfo;
import com.vp.tw.requestdto.NoShippingNoticeInfoListRequestDto;

public interface NoShippingNoticeService {

	List<NoShippingNoticeInfo> getNoShippingNoticeInfoList(NoShippingNoticeInfoListRequestDto dto);
}
