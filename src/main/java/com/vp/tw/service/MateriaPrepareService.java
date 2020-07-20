package com.vp.tw.service;

import java.util.List;

import com.vp.tw.model.vo.t100.MateriaPrepareInfo;
import com.vp.tw.requestdto.MateriaPrepareInfoRequestDto;

public interface MateriaPrepareService {

	List<MateriaPrepareInfo> getList(MateriaPrepareInfoRequestDto dto);

}
