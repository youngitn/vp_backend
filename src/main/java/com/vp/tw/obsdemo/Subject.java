package com.vp.tw.obsdemo;

public interface Subject {

	void notifyObserves(String ytname);
	void addObserves(Observer obs);
	void delObserves();
}
