package com.vp.tw.iteratordemo;

import java.util.List;

public class IteratorPt <T> {

	List<T> list = null;

	

	public IteratorPt(List<T> list) {
		this.list = list;
	}

	int nextIndex = 0;

	public boolean hasNext() {
		return nextIndex < this.list.size();
	}
	
	public T next() {
		int nowIndex = nextIndex;
		nextIndex+=1;
//		if(nextIndex >= this.list.size() && nextIndex % 2 == 0) {
//	        nextIndex = 1;
//	      }
	      // 回傳目前索引值內容
	      return list.get(nowIndex);
		
	}
	
	
}
