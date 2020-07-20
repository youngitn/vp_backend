package com.vp.tw.responsedto;

import java.util.List;

import com.vp.tw.model.vo.t100.PendingStorageInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PendingStorageInfoResponseDto {
	
	List<PendingStorageInfo> data;
	int page = 1;
	int per_page = 1;

}
