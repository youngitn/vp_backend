package com.vp.tw.service;

import java.util.List;

import com.vp.tw.model.vo.t100.PendingStorageInfo;
import com.vp.tw.requestdto.PendingStorageInfoRequestDto;

public interface PendingStorageService {

	List<PendingStorageInfo> getList(PendingStorageInfoRequestDto dto);
}
