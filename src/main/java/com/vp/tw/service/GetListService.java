package com.vp.tw.service;

import java.util.List;



public interface GetListService<T> {
	List<T> getList(T dto);
}
