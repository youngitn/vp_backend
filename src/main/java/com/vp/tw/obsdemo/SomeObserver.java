package com.vp.tw.obsdemo;

public class SomeObserver implements Observer {

	
	@Override
	public void notifyx(String ytname) {
		System.out.println("我訂閱了"+ytname+"頻道");

	}

}
